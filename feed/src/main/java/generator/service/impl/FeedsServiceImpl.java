package generator.service.impl;

import generator.domain.Feeds;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.FeedsService;
import generator.mapper.FeedsMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【feeds(动态表 按照uid分区)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class FeedsServiceImpl extends ServiceImpl<FeedsMapper, Feeds>
    implements FeedsService{

}




