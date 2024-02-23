package com.ailu.feeds.demos.web.controller;


import com.ailu.feeds.demos.web.service.CommentService;
import com.ailu.feeds.demos.web.service.FeedService;
import com.ailu.feeds.demos.web.vo.*;
import generator.domain.Feeds;
import generator.service.FeedsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import com.ailu.feeds.demos.web.converter.Converter;

@RestController
public class FeedsController {

    @Resource
    FeedsService feedsService;

    @Resource
    CommentService commentService;

    @Resource
    FeedService feedService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    FeedService feed;

    @PostMapping("/publish")
    public Response<Boolean> publish(@RequestBody FeedRequest feed) {

        feedService.publish(feed);
        return new Response<>(200, "success", true);
    }

    // 删除动态
    @DeleteMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return feedsService.removeById(id);
    }

    // 更新动态
    @PutMapping("/update")
    public Boolean update(@RequestBody FeedVo feed) {
        Feeds feeds = Converter.ToFeeds(feed);
        return feedsService.updateById(feeds);
    }

    // 通过id查询动态
    @GetMapping("/get/{id}")
    public FeedVo get(@PathVariable String id) {
        Feeds feeds = feedsService.getById(id);
        return Converter.ToFeed(feeds);
    }

    // 点赞动态
    @PutMapping("/like/{id}")
    public Boolean like(@PathVariable String id) {
        Feeds feeds = feedsService.getById(id);


        return feedsService.updateById(feeds);
    }

    // 取消点赞动态
    @PutMapping("/unlike/{id}")
    public Boolean unlike(@PathVariable String id) {
        Feeds feeds = feedsService.getById(id);

        return feedsService.updateById(feeds);
    }

    // 获取动态详情
    @GetMapping("/detail/{uid}/{feedId}")
    public FeedVo detail(@PathVariable Long uid, @PathVariable Long feedId) {
        return feed.getFeedDetail(uid, feedId);
    }

    // 评论动态
    @PostMapping("/comment")
    public Boolean comment(@RequestBody CommentRequest request) {
        return commentService.comment(request.getUid(), request.getBiz(), request.getFeedId(), request.getContent());
    }

    // 回复
    @PostMapping("/reply")
    public Boolean reply(@RequestBody ReplyRequest request) {
        return commentService.reply(request.getFromUid(), request.getToUid(), request.getBiz(), request.getCommentId(), request.getReplyId(), request.getContent());
    }


}
