package com.ailu.feeds;


import com.ailu.feeds.demos.web.model.LikeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "generator")
public class LikeModelConfig {

    @Bean
    public LikeModel commentLikeModel() {
        return new LikeModel("comment");
    }

    @Bean
    public LikeModel feedLikeModel() {
        return new LikeModel("feed");
    }

}
