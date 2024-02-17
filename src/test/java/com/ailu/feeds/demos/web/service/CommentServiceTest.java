package com.ailu.feeds.demos.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class CommentServiceTest {

    @Resource
    CommentService commentService;

    @Test
    void getCommentsByFeedId() {
        System.out.println(commentService.getCommentsByFeedId(4L));
    }

    @Test
    void getRepliesByCommentIds() {
        System.out.println(commentService.getRepliesByCommentIds(List.of(1L)));
    }
}
