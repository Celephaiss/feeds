package com.ailu.feeds.demos.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class FeedTest {

    @Resource
    FeedService feed;

    @Test
    void list() {
    }

    @Test
    void testList() {
        System.out.println(feed.list(13, 10));
    }
}
