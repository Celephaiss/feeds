package com.ailu.feeds.demos.web.service;


import com.ailu.feeds.demos.web.model.LikeModel;
import com.ailu.feeds.demos.web.vo.CommentVo;
import com.ailu.feeds.demos.web.vo.ReplyVo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import generator.domain.Comments;
import generator.domain.Replies;
import generator.mapper.RepliesMapper;
import generator.service.CommentsService;
import generator.service.RepliesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CommentService {

    @Resource
    CommentsService comments;
    @Resource
    RepliesService replies;

    @Resource
    RepliesMapper repliesMapper;

    @Resource
    LikeModel commentLikeModel;


    /**
     * 发布评论
     *
     * @param uid     用户id
     * @param feedId  动态id
     * @param content 评论内容
     * @return 是否发布成功
     */
    public Boolean comment(Integer uid, Integer biz, Long feedId, String content) {

        Comments comment = new Comments();
        comment.setUid(uid);
        comment.setBiz(biz);
        comment.setSubjectId(feedId);
        comment.setContent(content);
        return comments.save(comment);
    }

    /**
     * 删除评论
     *
     * @param uid       用户id
     * @param commentId 评论id
     * @return 是否删除成功
     */
    public Boolean deleteComment(Integer uid, Integer biz, Long commentId) {
        return comments.lambdaUpdate().
                eq(Comments::getUid, uid).
                eq(Comments::getBiz, biz).
                eq(Comments::getId, commentId).remove();
    }


    /**
     * 回复评论
     *
     * @param fromUid   用户id
     * @param toUid     被回复用户id
     * @param commentId 评论id
     * @param content   回复内容
     * @param replyId   回复id
     * @return 是否回复成功
     */
    public Boolean reply(Integer fromUid, Integer toUid, Integer biz, Long commentId, Long replyId, String content) {

        Replies reply = new Replies();
        reply.setFromUid(fromUid);
        reply.setToUid(toUid);
        reply.setBiz(biz);
        reply.setCommentId(commentId);
        reply.setContent(content);
        reply.setReplyId(replyId);
        return replies.save(reply);

    }

    /**
     * 回复列表
     *
     * @param commentId   评论id
     * @param lastReplyId 最后一条回复的id
     * @param pageSize    页大小
     */
    public List<ReplyVo> replyList(Long commentId, Long lastReplyId, Integer pageSize) {

        List<Replies> list = replies.lambdaQuery()
                .eq(Replies::getCommentId, commentId)
                .gt(Replies::getId, lastReplyId)
                .orderByAsc(Replies::getId)
                .last("limit " + pageSize).list();

        return List.of();
    }


    /**
     * 删除回复
     *
     * @param uid     用户id
     * @param replyId 回复id
     * @return 是否删除成功
     */
    public Boolean deleteReply(Integer uid, Integer biz, Long replyId) {
        return replies.lambdaUpdate().
                eq(Replies::getFromUid, uid).
                eq(Replies::getBiz, biz).
                eq(Replies::getId, replyId).remove();
    }


    // commentListSortedByTime
    public List<CommentVo> commentListSortedByTime(Long uid, Long feedId, Long lastId, Integer pageSize) {

        LambdaQueryChainWrapper<Comments> query = comments.lambdaQuery();
        List<Comments> list = query.eq(Comments::getBiz, 1).eq(Comments::getSubjectId, feedId).gt(Comments::getId, lastId).last("limit " + pageSize).list();

        List<CommentVo> commentVoList = new ArrayList<>();
        list.stream().map(comment -> {
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comment.getId());
            commentVo.setFeedId(comment.getSubjectId());
            commentVo.setUid(comment.getUid());
            commentVo.setContent(comment.getContent());
            commentVo.setPublishTime(comment.getCreateTime().getTime());
            commentVo.setLikeStatus(commentLikeModel.isLike(uid, comment.getId()));
            return commentVo;
        }).forEach(commentVoList::add);

        return getCommentVoList(list, commentVoList);

    }

    // commentListSortedByHot
    // TODO
    public List<CommentVo> commentListSortedByHot(Long uid, String id, Integer page, Integer pageSize) {
        return null;
    }

    public Map<Long, List<ReplyVo>> getRepliesByCommentIds(List<Long> commentIds) {

        if (commentIds == null || commentIds.isEmpty()) {
            return Map.of();
        }


        List<Replies> topNReplies = repliesMapper.getTopNReplies(commentIds, 4);

        if (topNReplies == null || topNReplies.isEmpty()) {
            return Map.of();
        }

        Map<Long, List<ReplyVo>> replies = topNReplies.stream().map(reply ->
                ReplyVo.builder()
                        .id(reply.getId())
                        .replyId(reply.getReplyId())
                        .feedId(reply.getSubjectId())
                        .content(reply.getContent())
                        .username("username")
                        .avatar("avatar")
                        .replyUsername("replyUsername")
                        .commentId(reply.getCommentId())
                        .build()
        ).collect(groupingBy(ReplyVo::getCommentId));


        replies.forEach((k, v) -> v.sort((o1, o2) -> o2.getId().compareTo(o1.getId())));
        return replies;
    }


    /**
     * 获取动态的评论及top3回复, 并且按时间排序
     *
     * @param feedId   动态id
     * @param lastId   最后一条评论id
     * @param pageSize 页大小
     */
    public List<CommentVo> getCommentsByFeedId(Long feedId, Long lastId, Integer pageSize) {
        LambdaQueryChainWrapper<Comments> query = comments.lambdaQuery();
        List<Comments> list = query.eq(Comments::getSubjectId, feedId)
                .gt(Comments::getId, lastId)
                .orderByAsc(Comments::getId)
                .last("limit " + pageSize)
                .list();

        List<CommentVo> commentVoList = new ArrayList<>();
        list.stream().map(comment -> CommentVo.builder().id(comment.getId())
                .feedId(comment.getSubjectId())
                .uid(comment.getUid())
                .content(comment.getContent())
                .build()).forEach(commentVoList::add);

        return getCommentVoList(list, commentVoList);
    }

    private List<CommentVo> getCommentVoList(List<Comments> list, List<CommentVo> commentVoList) {
        List<Long> commentIds = list.stream().map(Comments::getId).toList();

        Map<Long, List<ReplyVo>> repliesByCommentIds = getRepliesByCommentIds(commentIds);

        commentVoList.forEach(commentVo -> {
            List<ReplyVo> replies = repliesByCommentIds.get(commentVo.getId());
            if (replies != null) {
                commentVo.setReplyVoList(replies);
            }
        });

        return commentVoList;
    }
}
