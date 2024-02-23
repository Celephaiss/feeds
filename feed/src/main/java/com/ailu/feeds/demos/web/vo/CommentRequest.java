package com.ailu.feeds.demos.web.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Integer uid;
    private Integer biz;
    private Long feedId;
    private String content;
}
