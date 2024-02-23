package com.ailu.follow;

import com.ailu.follow.mq.MqCustomConfig;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan("generator.mapper")
public class FollowApplication {

    @Autowired
    private MqCustomConfig mqCustomConfig;


    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public static void main(String[] args) {
        SpringApplication.run(FollowApplication.class, args);
    }


}
