package cn.caoh2.app.service.impl;

import cn.caoh2.app.entity.User;
import cn.caoh2.app.mapper.MenuMapper;
import cn.caoh2.app.mapper.RoleMapper;
import cn.caoh2.app.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static cn.caoh2.app.entity.JwtUser.createJwtUser;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:14
 * @Description: 用户认证信息服务
 * @Version 1.0
 */

@SuppressWarnings("all")
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名获取用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 通过用户id获取用户权限信息
        List<String> perms = menuMapper.selectPermsByUserId(user.getUserId());
        // 将用户信息和权限信息封装到UserDetails中
        return createJwtUser(user, perms);
    }


}

