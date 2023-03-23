package cn.caoh2.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author caoh2
 * @Date 2023/3/19 21:29
 * @Description: 跨域配置
 * @Version 1.0
 */
@Slf4j
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径
        registry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOriginPatterns("*")
                // 允许请求的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的头部设置
                .allowedHeaders("*")
                // 是否发送cookie
                .allowCredentials(true)
                // 预检间隔时间
                .maxAge(3600);
    }
}
