package com.ailu.feeds.demos.web.service;


import generator.domain.TopicRelations;
import generator.domain.Topics;
import generator.mapper.TopicRelationsMapper;
import generator.mapper.TopicsMapper;
import generator.service.TopicRelationsService;
import generator.service.TopicsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Resource
    private TopicsService topicsService;

    @Resource
    private TopicRelationsService topicRelationsService;

    @Resource
    private TopicsMapper topicsMapper;

    @Resource
    private TopicRelationsMapper topicRelationsMapper;

    // 发布一个话题
    public Long publish(String name) {

        Topics topic = topicsMapper.getByName(name);

        if (topic != null) {
            return topic.getId();
        }

        Topics topics = new Topics();
        topics.setName(name);
        topicsService.save(topics);

        return topics.getId();

    }

    // 发布多个话题
    public List<Long> publish(List<String> names) {
        return names.stream().map(this::publish).toList();
    }

    // 保存话题关系
    public boolean saveRelations(Long feedId, List<Long> topicIds) {
        List<TopicRelations> topicRelations = new ArrayList<>();
        for (Long topicId : topicIds) {
            TopicRelations relation = new TopicRelations();
            // TODO biz 硬编码
            relation.setBiz(1);
            relation.setSubjectId(feedId);
            relation.setTid(topicId);
            topicRelations.add(relation);
        }
        return topicRelationsService.saveBatch(topicRelations);
    }

    // 根据话题名获取动态id
    public List<Long> getFeedIdsByTopicName(String name, Long lastFeedId, Integer pageSize) {
        Topics topic = topicsMapper.getByName(name);
        if (topic == null) {
            return new ArrayList<>();
        }
        return topicRelationsMapper.getSubjectIdByTid(Math.toIntExact(topic.getId()), Math.toIntExact(lastFeedId), pageSize);
    }

}
