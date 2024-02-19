package com.ailu.feeds.demos.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class TopicServiceTest {

    @Resource
    private TopicService topicService;

    @Test
    void publish() {
        Long hello = topicService.addTopic("hello2");
        System.out.println(hello);
    }
}
