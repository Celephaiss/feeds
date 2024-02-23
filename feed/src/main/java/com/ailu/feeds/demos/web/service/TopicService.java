package com.ailu.feeds.demos.web.service;


import com.ailu.feeds.demos.web.vo.TopicVo;
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
    public Long addTopic(String name) {

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
    public List<Long> addTopic(List<String> names) {
        return names.stream().map(this::addTopic).toList();
    }

    // 保存话题关系
    public void saveRelations(Long feedId, List<Long> topicIds) {
        List<TopicRelations> topicRelations = new ArrayList<>();
        for (Long topicId : topicIds) {
            TopicRelations relation = new TopicRelations();
            // TODO biz 硬编码
            relation.setBiz(1);
            relation.setSubjectId(feedId);
            relation.setTid(topicId);
            topicRelations.add(relation);
        }
        topicRelationsService.saveBatch(topicRelations);
    }

    // 根据话题名获取动态id
    public List<Long> getFeedIdsByTopicName(String name, Long lastFeedId, Integer pageSize) {
        Topics topic = topicsMapper.getByName(name);
        if (topic == null) {
            return new ArrayList<>();
        }
        return topicRelationsMapper.getSubjectIdByTid(Math.toIntExact(topic.getId()), Math.toIntExact(lastFeedId), pageSize);
    }

    public List<TopicVo> getTopicVOs(List<Long> topicIds) {

        if (topicIds.isEmpty()) {
            return List.of();
        }

        List<Topics> list = topicsService.lambdaQuery().in(Topics::getId, topicIds).list();
        return list.stream().map(topic -> TopicVo.builder().id(topic.getId()).name(topic.getName()).build()).toList();
    }

}
