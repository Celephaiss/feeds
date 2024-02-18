package com.ailu.feeds.demos.web.vo;

import lombok.Data;

@Data
public class ReplyRequest {
    private Integer uid;
    private Integer biz;
    private Long feedId;
    private Long commentId;
    private String content;
}
