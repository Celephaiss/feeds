package com.ailu.feeds.demos.web.vo;

import lombok.Data;

@Data
public class ReplyRequest {
    private Integer fromUid;
    private Integer toUid;
    private Integer biz;
    private Long feedId;
    private Long commentId;
    private Long replyId; // replyId存在时, 用户回复"回复", 否则用户回复"评论"
    private String content;
}
