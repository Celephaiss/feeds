package com.ailu.feeds.demos.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FeedServiceTest {

    @Resource
    FeedService feedService;

    @Test
    void getFeedDetail() {
        System.out.println(feedService.getFeedDetail(30010585L, 5L));
    }
}
