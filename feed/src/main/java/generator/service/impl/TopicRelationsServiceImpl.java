package generator.service.impl;

import generator.domain.TopicRelations;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.TopicRelationsService;
import generator.mapper.TopicRelationsMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【topic_relations(话题关联表)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class TopicRelationsServiceImpl extends ServiceImpl<TopicRelationsMapper, TopicRelations>
    implements TopicRelationsService{

}




