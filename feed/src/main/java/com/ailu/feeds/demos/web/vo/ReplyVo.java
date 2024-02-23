package com.ailu.feeds.demos.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ReplyVo {
    private Long id; // 这条回复的id
    private Long feedId; // 动态的id
    private Long commentId; // 评论的id
    private String content; // 回复内容

    private Boolean likeStatus; // 用户是否点赞回复
    private Integer likeCount; // 回复点赞数量

    private Integer uid; // 发布回复的用户uid
    private String username;  // 用户名
    private String avatar; // 用户头像

    private Long replyId; // 被回复的回复id
    private String replyUsername; // 被回复的用户名
    private String replyUid; // 被回复的用户uid

    private Long publishTime; // 回复时间

}
