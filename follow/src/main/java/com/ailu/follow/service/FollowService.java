package com.ailu.follow.service;

import com.ailu.follow.model.redis.FollowCache;
import com.ailu.follow.mq.FollowProducer;
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
    FollowProducer followProducer;


    @Autowired
    FollowCache followCache;

    private boolean getFollowStatus(Integer fromUid, Integer toUid) {
        return false;
    }


    // 关注
    private void follow(Integer fromUid, Integer toUid) {

        boolean followStatus = getFollowStatus(fromUid, toUid);
        if (followStatus) {
            return;
        }


        followProducer.sendFollowMessage(fromUid, toUid);

        followCache.follow(fromUid, toUid);

    }

    // 取消关注
    private void unFollow(Integer fromUid, Integer toUid) {

    }

    // 获取关注者列表
    private List<Integer> getFollowers(Integer uid) {
        return null;
    }

    // 获取粉丝列表
    private List<Integer> getFans(Integer uid) {
        return null;
    }

    // 获取关注数
    private Integer getFollowCount(Integer uid) {
        return 0;
    }

    // 获取粉丝数
    private Integer getFansCount(Integer uid) {
        return 0;
    }

    // 获取朋友数
    private @NotNull Integer getFriendsCount(Integer uid) {
        return 0;
    }

    // 判断是否关注
    private @NotNull Boolean isFollow(Integer fromUid, Integer toUid) {
        return false;
    }

    // 判断是否关注, 批量
    private List<Boolean> isFollow(Integer fromUid, List<Integer> toUids) {
        return null;
    }

    // 获取计数FollowCntVo
    private FollowCntVo getFollowCntVo(Integer uid) {
        return FollowCntVo.builder().build();
    }


}
