package com.ailu.follow.model.redis;


import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FollowCache {


    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    // 关注数key
    private String followCntKey(Integer uid) {
        return String.format("cnt:follow:{%d}", uid);
    }

    // 粉丝数key
    private String fansCntKey(Integer uid) {
        return String.format("cnt:fans:{%d}", uid);
    }

    // 朋友数key
    private String friendCntKey(Integer uid) {
        return String.format("cnt:friend:{%d}", uid);
    }

    // 关注列表key
    private String followListKey(Integer uid) {
        return String.format("follow:list:{%d}", uid);
    }

    // 粉丝列表key
    private String fansListKey(Integer uid) {
        return String.format("fans:list:{%d}", uid);
    }

    // 朋友列表key
    private String friendListKey(Integer uid) {
        return String.format("friend:list:{%d}", uid);
    }

    // 关注状态key
    private String followRelationKey(Integer fromUid, Integer toUid) {
        return String.format("rel:follow:{%d}_%d", fromUid, toUid);
    }

    // 朋友状态key
    private String friendRelationKey(Integer fromUid, Integer toUid) {
        return String.format("rel:friend:{%d}_%d", fromUid, toUid);
    }

    // 关注关系bloomfilter key
    private String followBloomFilterKey(Integer uid) {
        return String.format("rel:follow:bf:{%d}", uid);
    }


    // 获取关注数
    public Integer GetFollowCount(Integer uid) {
        String key = followCntKey(uid);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    // 获取粉丝数
    public Integer GetFansCount(Integer uid) {
        String key = fansCntKey(uid);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    // 获取朋友数
    public Integer GetFriendsCount(Integer uid) {
        String key = friendCntKey(uid);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return 0;
        }
        return Integer.parseInt(value);
    }


    public void follow(Integer fromUid, Integer toUid) {
        redisTemplate.opsForValue().set(followRelationKey(fromUid, toUid), "1");
        redissonClient.getBloomFilter(followBloomFilterKey(fromUid)).add(toUid);
    }

    public void unFollow(Integer fromUid, Integer toUid) {
        redisTemplate.opsForValue().set(followRelationKey(fromUid, toUid), "0");
    }

    /*
     获取关注状态
     @return 1: 关注 0: 未关注 2: 未知
     */
    public Integer getFollowStatus(Integer fromUid, Integer toUid) {

        boolean contains = redissonClient.getBloomFilter(followBloomFilterKey(fromUid)).contains(toUid);
        if (!contains) {
            return 0;
        }

        String key = followRelationKey(fromUid, toUid);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return 2;
        }

        return Integer.parseInt(value);
    }

    /*
     获取朋友状态
        @return 1: 朋友 0: 非朋友 2:未知
     */
    public Integer getFriendStatus(Integer fromUid, Integer toUid) {
        String key = friendRelationKey(fromUid, toUid);
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return 2;
        }
        return Integer.parseInt(value);
    }

    /*
    获取关注或朋友状态
    @return 0: 没关系 1: 关注 2:朋友 3:未知
     */
    public Integer getRelationStatus(Integer fromUid, Integer toUid) {
        Integer followStatus = getFollowStatus(fromUid, toUid);

        if (followStatus == 0) {
            return 0;
        }

        if (followStatus == 1) {
            Integer friendStatus = getFriendStatus(toUid, fromUid);
            if (friendStatus == 1) {
                return 2;
            }
            return 1;
        }

        return 3;
    }


}
