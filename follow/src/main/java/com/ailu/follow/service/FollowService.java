package com.ailu.follow.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.jetbrains.annotations.NotNull;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FollowService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private FollowService followService;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    // 关注
    private void Follow(Integer fromUid, Integer toUid) {

    }

    // 取消关注
    private void UnFollow(Integer fromUid, Integer toUid) {

    }

    // 获取关注者列表
    private List<Integer> GetFollowers(Integer uid) {
        return null;
    }

    // 获取粉丝列表
    private List<Integer> GetFans(Integer uid) {
        return null;
    }

    // 获取关注数
    private Integer GetFollowCount(Integer uid) {
        return 0;
    }

    // 获取粉丝数
    private Integer GetFansCount(Integer uid) {
        return 0;
    }

    // 获取朋友数
    private @NotNull Integer GetFriendsCount(Integer uid) {
        return 0;
    }

    // 判断是否关注
    private @NotNull Boolean IsFollow(Integer fromUid, Integer toUid) {
        return false;
    }

    // 判断是否关注, 批量
    private List<Boolean> IsFollow(Integer fromUid, List<Integer> toUids) {
        return null;
    }

    // 获取计数FollowCntVo
    private FollowCntVo GetFollowCntVo(Integer uid) {
        return FollowCntVo.builder().build();
    }


}
