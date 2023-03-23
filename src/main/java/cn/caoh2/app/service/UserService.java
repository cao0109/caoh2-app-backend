package cn.caoh2.app.service;

import cn.caoh2.app.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author 91523
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2023-03-19 19:56:01
 */
public interface UserService extends IService<User> {

    Map<String, Object> login(User user);

    User register(User user);

    void logout();
}
