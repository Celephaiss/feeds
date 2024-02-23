package com.ailu.follow.service;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FollowCntVo {
    private Integer followCnt;
    private Integer fansCnt;
    private Integer friendCnt;
}
