package generator.mapper;

import generator.domain.Topics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ailu
 * @description 针对表【topics(话题表)】的数据库操作Mapper
 * @createDate 2024-02-17 00:23:46
 * @Entity generator.domain.Topics
 */
public interface TopicsMapper extends BaseMapper<Topics> {

    Topics getByName(@Param("name") String name);

    List<Topics> getByNames(@Param("names") List<String> names);
}




