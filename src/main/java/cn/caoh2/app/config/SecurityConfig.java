package cn.caoh2.app.config;

import cn.caoh2.app.security.JwtAuthenticationTokenFilter;
import cn.caoh2.app.util.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author caoh2
 * @Date 2023/3/16 11:50
 * @Description: Spring Security 配置
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 配置用户认证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置安全策略
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许静态资源访问
                .antMatchers("/static/**").permitAll().regexMatchers("/api/test/.*").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll()
                // 允许注册
                .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                // 允许登录
                .antMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                // 其他请求必须经过身份认证
                .anyRequest().authenticated().and()
                // 配置登录页和登录接口
                .formLogin().loginProcessingUrl("/api/user/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler).permitAll().and()
                // 配置登出接口
                .logout().logoutUrl("/api/user/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll().and()
                // 禁用 csrf // 基于token，所以不需要session
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 配置JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 配置PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return Md5Utils.encryptPassword(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(Md5Utils.encryptPassword(charSequence.toString()));
            }
        };
    }

}

