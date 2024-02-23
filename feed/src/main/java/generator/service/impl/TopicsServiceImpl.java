package generator.service.impl;

import generator.domain.Topics;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.TopicsService;
import generator.mapper.TopicsMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【topics(话题表)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class TopicsServiceImpl extends ServiceImpl<TopicsMapper, Topics>
    implements TopicsService{

}




