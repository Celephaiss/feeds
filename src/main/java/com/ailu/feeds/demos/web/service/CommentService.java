package com.ailu.feeds.demos.web.service;


import com.ailu.feeds.demos.web.model.LikeModel;
import com.ailu.feeds.demos.web.vo.CommentVo;
import com.ailu.feeds.demos.web.vo.ReplyVo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
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
     * @param id      评论id
     * @param content 评论内容
     * @return 是否发布成功
     */
    public Boolean publish(Integer uid, String id, String content) {
        return true;
    }

    /**
     * 删除评论
     *
     * @param uid 用户id
     * @param id  评论id
     * @return 是否删除成功
     */
    public Boolean delete(Integer uid, String id) {
        return true;
    }

    /**
     * 回复评论
     *
     * @param uid       用户id
     * @param commentId 评论id
     * @param content   回复内容
     * @return 是否回复成功
     */
    public Boolean reply(Integer uid, Long commentId, String content) {
        return true;
    }

    /**
     * 删除回复
     *
     * @param uid 用户id
     * @param id  回复id
     * @return 是否删除成功
     */
    public Boolean deleteReply(Integer uid, String id) {
        return true;
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

    // commentListSortedByHot
    // TODO
    public List<CommentVo> commentListSortedByHot(Long uid, String id, Integer page, Integer pageSize) {
        return null;
    }

    public Map<Long, List<ReplyVo>> getRepliesByCommentIds(List<Long> commentIds) {

        List<Replies> topNReplies = repliesMapper.getTopNReplies(commentIds, 3);

        if (topNReplies == null || topNReplies.isEmpty()) {
            return Map.of();
        }

        Map<Long, List<ReplyVo>> replies = topNReplies.stream().map(reply ->
                ReplyVo.builder().replyId(reply.getId())
                        .feedId(reply.getSubjectId())
                        .content(reply.getContent())
                        .username("username")
                        .avatar("avatar")
                        .replyUsername("replyUsername")
                        .replyContent(reply.getContent())
                        .commentId(reply.getCommentId())
                        .build()).collect(groupingBy(ReplyVo::getCommentId));

        replies.forEach((k, v) -> v.sort((o1, o2) -> o2.getId().compareTo(o1.getId())));
        return replies;
    }


    public List<CommentVo> getCommentsByFeedId(Long feedId) {
        LambdaQueryChainWrapper<Comments> query = comments.lambdaQuery();
        List<Comments> list = query.eq(Comments::getSubjectId, feedId).list();

        List<CommentVo> commentVoList = new ArrayList<>();
        list.stream().map(comment -> CommentVo.builder().id(comment.getId())
                .feedId(comment.getSubjectId())
                .uid(comment.getUid())
                .content(comment.getContent())
//                .publishTime(comment.getCreateTime().getTime())
                .likeStatus(commentLikeModel.isLike(1L, comment.getId()))
                .build()).forEach(commentVoList::add);

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
