package com.ailu.follow.service;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FollowService {

    @Autowired
    private RedissonClient redissonClient;

    private void Follow(Integer fromUid, Integer toUid) {

    }

    private void UnFollow(Integer fromUid, Integer toUid) {

    }
}
