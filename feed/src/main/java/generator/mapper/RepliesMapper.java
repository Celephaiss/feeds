package generator.mapper;

import generator.domain.Replies;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ailu
* @description 针对表【replies(回复表)】的数据库操作Mapper
* @createDate 2024-02-18 15:03:38
* @Entity generator.domain.Replies
*/
public interface RepliesMapper extends BaseMapper<Replies> {

    List<Replies> getTopNReplies(@Param("commentIds") List<Long> commentIds, @Param("topN") Integer topN);


}




