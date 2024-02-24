package com.ailu.follow.mq;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowMessage {
    String uuid;
    Integer fromUid;
    Integer toUid;
    Integer opType;
    Long opTime;
}

