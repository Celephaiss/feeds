package com.ailu.feeds.demos.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TopicServiceTest {

    @Resource
    private TopicService topicService;

    @Test
    void publish() {
        Long hello = topicService.publish("hello2");
        System.out.println(hello);
    }
}
