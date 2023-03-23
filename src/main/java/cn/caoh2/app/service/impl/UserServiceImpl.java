package cn.caoh2.app.service.impl;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.entity.User;
import cn.caoh2.app.enums.ResultCode;
import cn.caoh2.app.exception.ServiceException;
import cn.caoh2.app.mapper.UserMapper;
import cn.caoh2.app.mapper.UserRoleMapper;
import cn.caoh2.app.service.UserService;
import cn.caoh2.app.utils.JwtTokenUtil;
import cn.caoh2.app.utils.Md5Utils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 91523
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-03-19 19:56:01
 */

@SuppressWarnings("all")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, Object> login(User user) {
        // 调用AuthenticationManager authentication进行身份验证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }
        // 将用户信息封装到UserDetails中
        UserDto userDto = (UserDto) authentication.getPrincipal();
        // 生成token
        String token = jwtTokenUtil.generateToken(userDto);
        // 将userDto 存入redis
        userDto.setToken(token);
        ObjectMapper objectMapper = new ObjectMapper();
        redisTemplate.opsForValue().set("token:" + userDto.getUsername(), userDto,
                3600000, TimeUnit.SECONDS);
        // 将token返回给controller
        Map<String, Object> result = new HashMap<>(2);
        result.put("token", token);
        return result;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class, timeout = 30000)
    public User register(User user) {
        try {
            String password = user.getPassword();
            String encryptPassword = Md5Utils.encryptPassword(password);
            user.setPassword(encryptPassword);
            userMapper.insert(user);
            userRoleMapper.insertUserRole(user.getUserId(), 2);
            return user;
        } catch (ServiceException e) {
            throw new ServiceException(ResultCode.USER_REGISTER_ERROR);
        }
    }

    @Override
    public void logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            UserDto userDto = (UserDto) authentication.getPrincipal();
            redisTemplate.delete("token:" + userDto.getUsername());
        }

    }
}




