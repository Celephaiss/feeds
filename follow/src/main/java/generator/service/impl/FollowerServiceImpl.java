package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Follower;
import generator.service.FollowerService;
import generator.mapper.FollowerMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【follower】的数据库操作Service实现
* @createDate 2024-02-23 20:05:22
*/
@Service
public class FollowerServiceImpl extends ServiceImpl<FollowerMapper, Follower>
    implements FollowerService{

}




