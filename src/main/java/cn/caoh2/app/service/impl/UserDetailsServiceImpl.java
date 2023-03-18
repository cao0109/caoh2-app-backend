package cn.caoh2.app.service.impl;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:14
 * @Description: 用户认证信息服务
 * @Version 1.0
 */

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名获取用户信息
        UserDto userDto = userMapper.selectUserDtoByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 构建用户授权信息
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDto.getRole()));
        return new org.springframework.security.core.userdetails.User(userDto.getUsername(),
                userDto.getPassword(), authorities);
    }


}

