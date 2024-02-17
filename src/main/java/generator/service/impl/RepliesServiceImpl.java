package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Replies;
import generator.service.RepliesService;
import generator.mapper.RepliesMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【replies(回复表)】的数据库操作Service实现
* @createDate 2024-02-18 00:24:48
*/
@Service
public class RepliesServiceImpl extends ServiceImpl<RepliesMapper, Replies>
    implements RepliesService{

}




