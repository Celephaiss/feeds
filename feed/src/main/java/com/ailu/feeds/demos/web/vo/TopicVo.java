package com.ailu.feeds.demos.web.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopicVo {
    Long id; // 话题id
    String name; // 话题名
}
