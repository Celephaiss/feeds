package com.ailu.feeds.demos.web.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class LikeModel {
    @Getter
    @Setter
    private String biz;


    @Resource
    StringRedisTemplate stringRedisTemplate;

    public LikeModel(String biz) {
        this.biz = biz;
    }

    public static final String Prefix = "like";

    public String LikeSetKey(Integer uid) {
        return String.format("%s:%s:{%s}", Prefix, biz, uid);
    }

    public String LikeCountKey(String id) {
        return String.format("%s:%s:{%s}:cnt", Prefix, biz, id);
    }

    // TODO 改成lua脚本
    public void like(Integer uid, String id) {
        stringRedisTemplate.opsForSet().add(LikeSetKey(uid), id);
        stringRedisTemplate.opsForValue().increment(LikeCountKey(id), 1);
    }

    // TODO 改成lua脚本
    public void unlike(Integer uid, String id) {
        stringRedisTemplate.opsForSet().remove(LikeSetKey(uid), id);
        stringRedisTemplate.opsForValue().increment(LikeCountKey(id), -1);
    }

    public Long likeCount(String id) {
        String count = stringRedisTemplate.opsForValue().get(LikeCountKey(id));

        if (count == null) {
            return 0L;
        }
        return Long.parseLong(count);
    }

    public Boolean isLike(Integer uid, String id) {
        return stringRedisTemplate.opsForSet().isMember(LikeSetKey(uid), id);
    }

}
