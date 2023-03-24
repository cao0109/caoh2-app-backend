package cn.caoh2.app.security;

import cn.caoh2.app.entity.JwtUser;
import cn.caoh2.app.enums.ResultCode;
import cn.caoh2.app.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:04
 * @Description: JWT登录授权过滤器
 * @Version 2.0
 */

@SuppressWarnings("all")
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取请求头中的token
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            // 从token中获取用户名
            String username = jwtUtils.getUsernameFromToken(authToken);
            log.info("checking authentication for user " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 从redis中获取token
                JwtUser jwtUser = (JwtUser) redisTemplate.opsForValue().get("userDetails:" + username);
                if (Objects.isNull(jwtUser)) {
                    log.info("token is null");
                    throw new RuntimeException(ResultCode.USER_NOT_LOGGED_IN.getMessage());
                }

                if (jwtUtils.validateToken(authToken, jwtUser)) {
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        // 放行
        chain.doFilter(request, response);
    }
}

