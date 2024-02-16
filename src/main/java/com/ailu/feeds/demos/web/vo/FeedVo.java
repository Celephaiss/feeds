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
    private String content;
    private List<String> images;
    private List<String> topicNames;
    private Integer uid;
    private Boolean likeStatus;
    private Integer likeCount;
    private Long publishTime;
    private Long AuditStatus;
    private String username;
    private String avatar;
    private Integer commentCount;

    // 评论列表, 默认取最新的3条
    private List<CommentVo> commentVoList;
}


