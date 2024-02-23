package com.ailu.follow.mq;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Data
@Component
@ConfigurationProperties(prefix = "rocketmq.custom")
public class MqCustomConfig {

    private String followTopic;

}
