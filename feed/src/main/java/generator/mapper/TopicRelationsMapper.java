package generator.mapper;

import generator.domain.TopicRelations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ailu
 * @description 针对表【topic_relations(话题关联表)】的数据库操作Mapper
 * @createDate 2024-02-17 00:23:46
 * @Entity generator.domain.TopicRelations
 */
public interface TopicRelationsMapper extends BaseMapper<TopicRelations> {
    List<Long> getSubjectIdByTid(@Param("tid") Integer tid, @Param("lastSubjectId") Integer lastSubjectId, @Param("PageSize") Integer PageSize);
}




