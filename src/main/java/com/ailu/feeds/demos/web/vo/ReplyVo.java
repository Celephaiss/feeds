package com.ailu.feeds.demos.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ReplyVo {
    private Long id;
    private Long feedId;
    private Integer uid;
    private String content;
    private Long publishTime;
    private Boolean likeStatus;
    private Integer likeCount;
    private String username;
    private String avatar;
    private Integer replyCount;
    private Long replyId;
    private String replyUsername;
    private String replyContent;
    private Long replyPublishTime;
}
