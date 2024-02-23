package generator.service.impl;

import generator.domain.User;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.UserService;
import generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author ailu
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-02-17 22:59:45
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




