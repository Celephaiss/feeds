package com.ailu.feeds.demos.web.converter;

import com.ailu.feeds.demos.web.vo.FeedRequest;
import com.ailu.feeds.demos.web.vo.FeedVo;
import com.google.common.base.Joiner;
import generator.domain.Feeds;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Converter {

    public static Feeds ToFeeds(FeedVo feed) {

        Feeds feeds = new Feeds();
//        feeds.setId(1L);
//        feeds.setBiz(1);
        feeds.setContent(feed.getContent());
        feeds.setImages(String.join(",", feed.getImages()));

        List<String> topicList = feed.getTopicNames().stream().map(String::valueOf).toList();
        feeds.setTopicIds(String.join(",", topicList));
        feeds.setUid(feed.getUid());
        feeds.setCreateTime(new Date());
        feeds.setUpdateTime(new Date());
        return feeds;
    }

    public static Feeds ToFeeds2(FeedRequest feed) {

        Feeds feeds = new Feeds();

        feeds.setContent(feed.getContent());

        if (feed.getImages() != null && !feed.getImages().isEmpty()) {
            String images = Joiner.on(",").skipNulls().join(feed.getImages());
            feeds.setImages(images);
        }


        feeds.setUid(feed.getUid());
        feeds.setCreateTime(new Date());
        feeds.setUpdateTime(new Date());
        return feeds;
    }

    public static FeedVo ToFeed(Feeds feeds) {
        FeedVo feed = new FeedVo();
        feed.setContent(feeds.getContent());
        feed.setImages(Stream.of(feeds.getImages().split(",")).filter(r -> !r.isEmpty()).toList());
//        feed.setTopicIds(Stream.of(feeds.getTopicIds().split(",")).filter(r -> !r.isEmpty()).map(Integer::parseInt).toList());
        feed.setUid(feeds.getUid());
        return feed;
    }

}
