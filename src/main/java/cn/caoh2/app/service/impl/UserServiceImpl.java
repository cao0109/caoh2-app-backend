package cn.caoh2.app.service.impl;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.entity.User;
import cn.caoh2.app.enums.ResultCode;
import cn.caoh2.app.exception.ServiceException;
import cn.caoh2.app.mapper.RoleMapper;
import cn.caoh2.app.mapper.UserMapper;
import cn.caoh2.app.mapper.UserRoleMapper;
import cn.caoh2.app.service.UserService;
import cn.caoh2.app.util.Md5Utils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 91523
 * @description 针对表【tb_user(用户表)】的数据库操作Service实现
 * @createDate 2023-03-16 12:27:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public UserDto getUserByUsername(String username) {
        try {
            return userMapper.selectUserDtoByUsername(username);
        } catch (RuntimeException e) {
            throw new ServiceException(ResultCode.DATA_SELECT_ERROR);
        }
    }

    @Override
    public User login(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        try {
            User user = userMapper.selectOne(queryWrapper);
            if (user == null) {
                throw new ServiceException(ResultCode.USER_NOT_EXIST);
            }
            if (!user.getPassword().equals(Md5Utils.encryptPassword(password))) {
                throw new ServiceException(ResultCode.USER_PASSWORD_ERROR);
            }
            return user;
        } catch (Exception e) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class, timeout = 30000)
    public User register(User user) {
        try {
            String password = user.getPassword();
            String encryptPassword = Md5Utils.encryptPassword(password);
            user.setPassword(encryptPassword);
            userMapper.insert(user);
            userRoleMapper.insertUserRole(user.getId(), 2);
            return user;
        } catch (ServiceException e) {
            throw new ServiceException(ResultCode.USER_REGISTER_ERROR);
        }
    }
}




