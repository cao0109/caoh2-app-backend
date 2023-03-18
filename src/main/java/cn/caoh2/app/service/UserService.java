package cn.caoh2.app.service;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 91523
 * @description 针对表【tb_user(用户表)】的数据库操作Service
 * @createDate 2023-03-16 12:27:08
 */
public interface UserService extends IService<User> {

    UserDto getUserByUsername(String username);

    User login(String username, String password);

    User register(User user);
}
