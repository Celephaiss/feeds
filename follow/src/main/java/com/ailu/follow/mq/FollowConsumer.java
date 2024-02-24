package com.ailu.follow.mq;

import com.google.gson.Gson;
import generator.domain.Followee;
import generator.domain.Follower;
import generator.service.FolloweeService;
import generator.service.FollowerService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@RocketMQMessageListener(
        topic = "${rocketmq.custom.follow-topic}",                      // 1.topic：消息的发送者使用同一个topic
        consumerGroup = "test-group",               // 2.group：不用和生产者group相同 ( 在RocketMQ中消费者和发送者组没有关系 )
        selectorExpression = "*",                   // 3.tag：设置为 * 时，表示全部。
        messageModel = MessageModel.CLUSTERING,    // 4.消费模式：默认 CLUSTERING （ CLUSTERING：负载均衡 ）（ BROADCASTING：广播机制 ）
        consumeMode = ConsumeMode.ORDERLY
)
public class FollowConsumer implements RocketMQListener<MessageExt> {

    @Resource
    FollowerService followerService;

    @Resource
    FolloweeService followeeService;

    @Override
    public void onMessage(MessageExt message) {

        // 1.获取消息内容
        byte[] body = message.getBody();
        String msg = new String(body);
        System.out.println("接收到消息：" + msg);

        // 2.处理消息
        // 反序列化消息 gson
        Gson gson = new Gson();
        FollowMessage followMessage = gson.fromJson(msg, FollowMessage.class);
        switch (followMessage.getOpType()) {
            case 1:
                System.out.println("关注消息：" + followMessage);

                Follower follower = new Follower();
                follower.setFromUid(followMessage.getFromUid());
                follower.setToUid(followMessage.getToUid());
                follower.setStatus(1);

                Followee followee = new Followee();

                BeanUtils.copyProperties(follower, followee);

                // 查询toUid是否关注了fromUid
                Follower one = followerService.lambdaQuery().eq(Follower::getFromUid, followMessage.getToUid()).eq(Follower::getToUid, followMessage.getFromUid()).one();

                // 互相关注
                if (one.getStatus() == 1) {
                    follower.setStatus(2);
                    followee.setStatus(2);
                    followerService.lambdaUpdate().eq(Follower::getFromUid, followMessage.getToUid()).eq(Follower::getToUid, followMessage.getFromUid()).set(Follower::getStatus, 2).update();
                    followeeService.lambdaUpdate().eq(Followee::getFromUid, followMessage.getToUid()).eq(Followee::getToUid, followMessage.getFromUid()).set(Followee::getStatus, 2).update();
                }
                followerService.save(follower);
                followeeService.save(followee);


                break;
            case 2:
                System.out.println("取消关注消息：" + followMessage);
                break;
            default:
                System.out.println("未知消息：" + followMessage);
        }
    }
}
