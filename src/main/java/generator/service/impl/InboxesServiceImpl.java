package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Inboxes;
import generator.service.InboxesService;
import generator.mapper.InboxesMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【inboxes(用户收件箱)】的数据库操作Service实现
* @createDate 2024-02-17 21:58:22
*/
@Service
public class InboxesServiceImpl extends ServiceImpl<InboxesMapper, Inboxes>
    implements InboxesService{

}




