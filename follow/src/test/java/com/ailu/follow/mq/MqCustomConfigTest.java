package com.ailu.follow.mq;

import com.mysql.cj.protocol.MessageSender;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


@SpringBootTest
class MqCustomConfigTest {

    @Autowired
    MqCustomConfig mqCustomConfig;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void getFollowTopic() {
        System.out.println(mqCustomConfig.getFollowTopic());
    }

    @Test
    void sendMsg(){

        Message<String> msg = MessageBuilder.withPayload("Hello, World!").build();

        rocketMQTemplate.send(mqCustomConfig.getFollowTopic(), msg);
    }
}
