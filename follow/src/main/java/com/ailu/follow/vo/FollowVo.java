package com.ailu.follow.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowVo {
    // 关注者
    private Integer fromUid;
    // 被关注者
    private Integer toUid;
    // 操作类型
    private Integer type; // 1: 关注 2: 取消关注
}
