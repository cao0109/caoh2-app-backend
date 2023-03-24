package cn.caoh2.app.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @Author caoh2
 * @Date 2023/3/24 10:31
 * @Description: 自定义SpEL表达式
 * @Version 1.0
 */

@Slf4j
@Component("customExpression")
public class CustomExpression {
    public boolean hasAuthority(String authority) {
        log.info("authority: {}", authority);
        // 获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        // 判断当前用户是否有指定权限
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(authority));
    }
}
