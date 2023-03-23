package cn.caoh2.app.dto;

import cn.caoh2.app.entity.User;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:40
 * @Description: 用户数据传输对象
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements UserDetails {
    private User user;
    private List<String> permissions;
    @JSONField(serialize = false) // 不序列化
    private List<GrantedAuthority> authorities;
    private String token;

    public UserDto(User user) {
        this.user = user;
    }

    public UserDto(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
/*        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
            grantedAuthorities.add(simpleGrantedAuthority);
        }*/
        if (Objects.isNull(authorities)) {
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
}
