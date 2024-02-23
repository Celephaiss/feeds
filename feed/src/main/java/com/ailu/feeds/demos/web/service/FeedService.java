package com.ailu.feeds.demos.web.service;

import com.ailu.feeds.demos.web.converter.Converter;
import com.ailu.feeds.demos.web.model.FeedRedisModel;
import com.ailu.feeds.demos.web.model.LikeModel;
import com.ailu.feeds.demos.web.vo.CommentVo;
import com.ailu.feeds.demos.web.vo.FeedRequest;
import com.ailu.feeds.demos.web.vo.FeedVo;
import com.ailu.feeds.demos.web.vo.TopicVo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import generator.domain.Feeds;
import generator.service.FeedsService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class FeedService {

    @Resource
    FeedsService feedsService;

    @Resource
    TopicService topicService;

    @Resource
    CommentService commentService;

    @Resource
    LikeModel feedLikeModel;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private FeedRedisModel feedRedisModel;


    /**
     * 删除动态
     *
     * @param uid 用户id
     * @param id  动态id
     * @return 是否删除成功
     */
    public Boolean delete(Integer uid, String id) {
        return true;
    }

    public static String lockKey(Long uid) {
        return "feed:lock:" + uid;
    }

    /**
     * 点赞动态
     *
     * @param uid 用户id
     * @param id  动态id
     * @return 是否点赞成功
     */
    public Boolean like(Long uid, Long id) {

        String key = lockKey(uid);
        RLock lock = redissonClient.getLock(key);

        if (lock.tryLock()) {
            try {
                if (feedLikeModel.isLike(uid, id)) {
                    log.info("用户{}已经点赞过{}", uid, id);
                    return false;
                }
                // 点赞
                feedLikeModel.like(uid, id);
                return true;
            } finally {
                lock.unlock();
            }
        } else {
            log.info("用户{}点赞失败", uid);
            return false;
        }
    }

    /**
     * 取消点赞动态
     *
     * @param uid 用户id
     * @param id  动态id
     * @return 是否取消点赞成功
     */
    public Boolean unlike(Long uid, Long id) {
        String key = lockKey(uid);
        RLock lock = redissonClient.getLock(key);

        if (lock.tryLock()) {
            try {
                if (!feedLikeModel.isLike(uid, id)) {
                    log.info("用户{}没有点赞过{}", uid, id);
                    return false;
                }
                // 取消点赞
                feedLikeModel.unlike(uid, id);
                return true;
            } finally {
                lock.unlock();
            }
        } else {
            log.info("用户{}取消点赞失败", uid);
            return false;
        }
    }

    /**
     * 获取动态
     *
     * @param feedId 动态id
     * @return 动态详情页
     */
    public FeedVo getFeedDetail(Long uid, Long feedId) {

        Feeds feed = feedsService.getById(feedId);
        FeedVo feedVo = Converter.ToFeed(feed);


        // 评论列表
        List<CommentVo> comments = commentService.getCommentsByFeedId(feedId, 0L, 10);


        feedVo.setCommentVoList(comments);

        String[] split = feed.getTopicIds().split(",");
        List<Long> topicIds = Arrays.stream(split).filter(r -> !r.isEmpty()).map(Long::parseLong).toList();

        List<TopicVo> topicVOs = topicService.getTopicVOs(topicIds);

        Boolean like = feedLikeModel.isLike(uid, feedId);
        feedVo.setLikeStatus(like);
        Integer l = feedLikeModel.likeCount(feedId);
        feedVo.setLikeCount(l);

        feedVo.setTopics(topicVOs);

        return feedVo;

    }


    /**
     * 更新动态
     *
     * @param uid     用户id
     * @param id      动态id
     * @param content 动态内容
     * @return 是否更新成功
     */
    public Boolean update(Integer uid, Integer id, String content) {
        return true;
    }

    // 个人主页动态列表
    public List<Feeds> list(Integer uid, Integer lastId, Integer pageSize) {
        LambdaQueryChainWrapper<Feeds> lambdaQuery = feedsService.lambdaQuery();

        if (lastId == null) {
            lastId = 0;
        }

        return lambdaQuery.eq(Feeds::getUid, uid).gt(Feeds::getId, lastId).orderByAsc(Feeds::getId).last("limit " + pageSize).list();
    }

    // 动态广场列表
    // TODO 缓存
    public List<FeedVo> list(Integer lastId, Integer pageSize) {
        // zset 有序集合 缓存前1000条动态详情 json
        List<String> list = feedRedisModel.getSquareFeedIds(lastId, pageSize);
        List<Feeds> feeds = feedRedisModel.mGetFeedDetail(list);

        return feeds.stream().map(Converter::ToFeed).toList();
    }

    // 发布动态
    public void publish(FeedRequest feed) {
        Feeds feeds = Converter.ToFeeds2(feed);

        Long feedId = genFeedId();
        feeds.setId(feedId);


        List<Long> topicIds = topicService.addTopic(feed.getTopicNames());

        feeds.setTopicIds(String.join(",", topicIds.stream().map(String::valueOf).toList()));

        feedsService.save(feeds);

        topicService.saveRelations(feedId, topicIds);


    }

    // TODO 改成snowflake id
    private Long genFeedId() {
        return stringRedisTemplate.opsForValue().increment("feeds:count", 1);
    }


}
