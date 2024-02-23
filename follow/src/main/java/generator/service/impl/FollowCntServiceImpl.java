package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.FollowCnt;
import generator.service.FollowCntService;
import generator.mapper.FollowCntMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【follow_cnt(关注计数表)】的数据库操作Service实现
* @createDate 2024-02-23 20:05:22
*/
@Service
public class FollowCntServiceImpl extends ServiceImpl<FollowCntMapper, FollowCnt>
    implements FollowCntService{

}




