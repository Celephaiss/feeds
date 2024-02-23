package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.FollowLog;
import generator.service.FollowLogService;
import generator.mapper.FollowLogMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【follow_log(关注流水表)】的数据库操作Service实现
* @createDate 2024-02-23 20:05:22
*/
@Service
public class FollowLogServiceImpl extends ServiceImpl<FollowLogMapper, FollowLog>
    implements FollowLogService{

}




