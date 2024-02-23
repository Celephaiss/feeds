package generator.service.impl;

import generator.domain.Comments;
import generator.mapper.CommentsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.CommentsService;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【comments(评论表)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments>
    implements CommentsService{

}




