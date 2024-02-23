package com.ailu.feeds.demos.web.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentVo {
    private Long id; // 评论id
    private Long feedId; // 动态id

    private Integer uid; // 发布评论的用户uid
    private String username; // 用户名
    private String avatar; // 用户头像

    private String content;  // 评论内容

    private Boolean likeStatus; // 用户是否点赞评论
    private Integer likeCount; // 评论的点赞数


    private Long publishTime; // 评论的发布时间


    // 回复列表, 默认取最新的3条
    private List<ReplyVo> replyVoList;

}


