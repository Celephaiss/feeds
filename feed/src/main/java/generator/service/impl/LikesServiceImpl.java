package generator.service.impl;

import generator.domain.Likes;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.LikesService;
import generator.mapper.LikesMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【likes(点赞表)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes>
    implements LikesService{

}




