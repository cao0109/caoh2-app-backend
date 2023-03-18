package cn.caoh2.app.security;

import cn.caoh2.app.entity.User;
import cn.caoh2.app.service.UserService;
import cn.caoh2.app.util.JwtTokenUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author caoh2
 * @Date 2023/3/16 21:19
 * @Description: 登录成功处理器
 * @Version 1.0
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从 Authentication 对象中获取用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 生成 JWT Token
        String jwtToken = jwtUtils.generateToken(userDetails);
        // 将 JWT Token 添加到响应头中
        response.addHeader("Authorization", "Bearer " + jwtToken);

        // 设置响应内容类型为 JSON
        response.setContentType("application/json");

        Map<String, Object> responseBody = new HashMap<>(4);
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userDetails.getUsername()));
        responseBody.put("user", user);
        responseBody.put("organization", userDetails);
        responseBody.put("token", jwtToken);

        JSONObject jsonObject = new JSONObject(responseBody);
        String jsonString = jsonObject.toJSONString();

        // 将响应数据写入响应体中
        PrintWriter out = response.getWriter();
        out.print(jsonString);
        out.flush();
        out.close();
    }
}


