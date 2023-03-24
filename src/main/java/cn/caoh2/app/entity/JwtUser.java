package cn.caoh2.app.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author caoh2
 * @Date 2023/3/23 12:26
 * @Description: JwtUser 实现了 UserDetails 接口，用于封装用户信息，方便 Spring Security 进行权限校验
 * @Version 1.0
 */
@Data
public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -8091879091924046844L;
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;

    public JwtUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities, Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public static JwtUser createJwtUser(User user, List<String> perms) {
        LocalDateTime updateTime = user.getUpdateTime();
        if (updateTime == null) {
            updateTime = user.getCreateTime() == null ? LocalDateTime.now() : user.getCreateTime();
        }
        Date lastPasswordResetDate = Date.from(updateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("lastPasswordResetDate = " + lastPasswordResetDate);
        return new JwtUser(user.getUserId(), user.getUsername(), user.getPassword(), mapToGrantedAuthorities(perms), lastPasswordResetDate);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JSONField(name = "isAccountNonExpired")
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JSONField(name = "isAccountNonLocked")
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JSONField(name = "isCredentialsNonExpired")
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(name = "isEnabled")
    public boolean isEnabled() {
        return true;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> perms) {
        return perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
