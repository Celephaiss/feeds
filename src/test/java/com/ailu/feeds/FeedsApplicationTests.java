package com.ailu.feeds;

import com.ailu.feeds.demos.web.controller.FeedsController;
import com.ailu.feeds.demos.web.vo.FeedRequest;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class FeedsApplicationTests {

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Resource
    FeedsController controller;

    @Test
    void contextLoads() {
    }

    @Test
    void testController() {

        for (int i = 0; i < 1000; i++) {

            Random random = new Random();


            int numTopic = random.nextInt(4);

            List<String> topicNames = new ArrayList<>();
            for (int j = 0; j < numTopic; j++) {
                int i1 = random.nextInt();
                topicNames.add("topic" + i1);
            }

            int uid = random.nextInt();

            FeedRequest feedRequest = FeedRequest.builder()
                    .uid(uid)
                    .content("hello")
                    .topicNames(topicNames)

                    .build();

            controller.publish(feedRequest);
        }


    }

}
