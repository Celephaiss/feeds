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
public class CommentVo {
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
    private Integer replyId;
    private String replyUsername;
    private String replyContent;
    private Long replyPublishTime;

    // 回复列表, 默认取最新的3条
    private List<ReplyVo> replyVoList;

}
