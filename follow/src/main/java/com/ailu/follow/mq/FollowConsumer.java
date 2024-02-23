package com.ailu.follow.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


@Component
@RocketMQMessageListener(
        topic = "${rocketmq.custom.follow-topic}",                      // 1.topic：消息的发送者使用同一个topic
        consumerGroup = "test-group",               // 2.group：不用和生产者group相同 ( 在RocketMQ中消费者和发送者组没有关系 )
        selectorExpression = "*",                   // 3.tag：设置为 * 时，表示全部。
        messageModel = MessageModel.CLUSTERING    // 4.消费模式：默认 CLUSTERING （ CLUSTERING：负载均衡 ）（ BROADCASTING：广播机制 ）
)
public class FollowConsumer implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt message) {
        System.out.println("Received message: " + new String(message.getBody()));
    }
}
