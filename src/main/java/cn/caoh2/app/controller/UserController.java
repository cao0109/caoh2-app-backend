package cn.caoh2.app.controller;

import cn.caoh2.app.entity.User;
import cn.caoh2.app.exception.ServiceException;
import cn.caoh2.app.service.UserService;
import cn.caoh2.app.utils.JwtUtils;
import cn.caoh2.app.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static cn.caoh2.app.enums.ResultCode.USER_REGISTER_ERROR;

/**
 * @Author caoh2
 * @Date 2023/3/16 13:11
 * @Description: 用户控制器
 * @Version 1.0
 */

@SuppressWarnings("all")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        log.info("user: {}", user);
        User data = null;
        try {
            data = userService.register(user);
        } catch (ServiceException e) {
            return Result.failure(USER_REGISTER_ERROR, e.getMessage());
        }
        return Result.success(data);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        log.info("user: {}", user);
        Map<String, Object> data = null;
        try {
            data = userService.login(user);
        } catch (ServiceException e) {
            return Result.failure(e.getResultCode(), e.getMessage());
        }
        return Result.success(data);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public Result logout() {
        try {
            userService.logout();
        } catch (ServiceException e) {
            return Result.failure(e.getResultCode(), e.getMessage());
        }
        return Result.success();
    }

    @GetMapping("/info")
    public Result<User> info() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, "admin");
        User user = userService.getOne(queryWrapper);
        log.info("user: {}", user);
        return Result.success(user);
    }
}
