package com.ailu.follow.mq;


import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Timer;
import java.util.UUID;

@Component
public class FollowProducer {
    @Value("${rocketmq.custom.follow-topic}")
    private String followTopic;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 发送关注消息
    public void sendFollowMessage(Integer fromUid, Integer toUid) {

        String uuid = UUID.randomUUID().toString();

        long opTime = Instant.now().toEpochMilli();

        FollowMessage followMessage = FollowMessage.builder()
                .uuid(uuid)
                .opTime(opTime)
                .opType(FollowOpType.FOLLOW_OP_TYPE_FOLLOW.ordinal())
                .fromUid(fromUid)
                .toUid(toUid)
                .build();


        int min = Math.min(fromUid, toUid);

        rocketMQTemplate.syncSendOrderly(followTopic, followMessage, String.valueOf(min));

    }

    // 发送取消关注消息
    public void sendUnFollowMessage(Integer fromUid, Integer toUid) {

        String uuid = UUID.randomUUID().toString();

        long opTime = Instant.now().toEpochMilli();

        FollowMessage followMessage = FollowMessage.builder()
                .uuid(uuid)
                .opTime(opTime)
                .opType(FollowOpType.FOLLOW_OP_TYPE_UNFOLLOW.ordinal())
                .fromUid(fromUid)
                .toUid(toUid)
                .build();

        int min = Math.min(fromUid, toUid);

        rocketMQTemplate.syncSendOrderly(followTopic, followMessage, String.valueOf(min));

    }



}
