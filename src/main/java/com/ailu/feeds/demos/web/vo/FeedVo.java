package com.ailu.feeds.demos.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedVo {
    private Long id; // 动态id

    private Integer uid; // 用户uid
    private String username;  // 用户名
    private String avatar; // 用户头像

    private String content; // 动态内容
    private List<String> images; // 动态图片

    @Deprecated
    private List<String> topicNames;

    private List<TopicVo> topics;

    private Boolean likeStatus;
    private Integer likeCount;

    private Long publishTime; // 发布时间

//    private Long AuditStatus;

    private Integer commentCount; // 总评论数
    private List<CommentVo> commentVoList; // 评论列表, 默认取最新的3条


}


