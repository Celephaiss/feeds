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

    public String LikeSetKey(Long uid) {
        return String.format("%s:%s:{%d}", Prefix, biz, uid);
    }

    public String LikeCountKey(Long id) {
        return String.format("%s:%s:{%d}:cnt", Prefix, biz, id);
    }

    // TODO 改成lua脚本
    public void like(Long uid, Long id) {
        stringRedisTemplate.opsForSet().add(LikeSetKey(uid), String.valueOf(id));
        stringRedisTemplate.opsForValue().increment(LikeCountKey(id), 1);
    }

    // TODO 改成lua脚本
    public void unlike(Long uid, Long id) {
        stringRedisTemplate.opsForSet().remove(LikeSetKey(uid), id);
        stringRedisTemplate.opsForValue().increment(LikeCountKey(id), -1);
    }

    public Integer likeCount(Long id) {
        String count = stringRedisTemplate.opsForValue().get(LikeCountKey(id));

        if (count == null) {
            return 0;
        }
        return Integer.parseInt(count);
    }

    public Boolean isLike(Long uid, Long id) {
        return stringRedisTemplate.opsForSet().isMember(LikeSetKey(uid), id.toString());
    }

}
