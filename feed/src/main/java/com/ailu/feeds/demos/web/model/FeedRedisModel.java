package com.ailu.feeds.demos.web.model;

import com.google.gson.Gson;
import generator.domain.Feeds;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;


@Repository
public class FeedRedisModel {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public static final String Prefix = "feed";


    // 获取广场动态id
    public List<String> getSquareFeedIds(Integer lastId, Integer pageSize) {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeByScoreWithScores("zs1", 0, lastId, 0, pageSize);

        if (typedTuples == null || typedTuples.isEmpty()) {
            return List.of();
        }

        return typedTuples.stream().map(ZSetOperations.TypedTuple::getValue).toList();
    }


    // 批量获取动态详情
    public List<Feeds> mGetFeedDetail(List<String> ids) {
        List<String> strings = stringRedisTemplate.opsForValue().multiGet(ids);

        if (strings == null || strings.isEmpty()) {
            return List.of();
        }

        Gson gson = new Gson();
        return strings.stream().map(s -> gson.fromJson(s, Feeds.class)).toList();
    }


    // 获取动态详情
    public Feeds getFeedDetail(String id) {
        String key = feedSetKey(Integer.parseInt(id));
        String feed = stringRedisTemplate.opsForValue().get(key);
        if (feed != null) {
            return new Gson().fromJson(feed, Feeds.class);
        }

        return null;
    }


    private static String feedSetKey(Object feedId) {
        return String.format("%s:detail:{%s}", Prefix, feedId);
    }

    private static List<String> feedDetailKeys(List<Integer> ids) {
        return ids.stream().map(FeedRedisModel::feedSetKey).toList();
    }


}
