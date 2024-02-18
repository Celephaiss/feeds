package com.ailu.feeds.demos.web.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class FeedRequest {

    private Integer uid; // 用户uid


    private String content; // 动态内容
    private List<String> images; // 动态图片

    private List<String> topicNames;
}
