package cn.caoh2.app.utils;

import cn.caoh2.app.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.management.RuntimeMBeanException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:04
 * @Description: JWT工具类
 * @Version 1.0
 */

@SuppressWarnings("all")
@Slf4j
@Component
public class JwtUtils {

    // 用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    // 用户头像的key
    private static final String CLAIM_KEY_AVATAR = "avatar";
    // jwt创建时间的key
    private static final String CLAIM_KEY_CREATED = "created";
    // 用户权限的key
    private static final String CLAIM_KEY_AUTHORITIES = "authorities";
    // jwt过期时间的key
    private static final String JWT_CLAIMS_EXPIRATION = "exp";

    // 密钥
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    // 过期时间
    @Value("${jwt.expiration}")
    private Long EXPIRATION_TIME;

    /**
     * 生成token
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, generateCurrentDate());
        claims.put(CLAIM_KEY_AUTHORITIES, userDetails.getAuthorities());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token) && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(generateCurrentDate());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    /**
     * 刷新token
     *
     * @param token 原token
     * @return 新token
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, generateCurrentDate());
            refreshedToken = generateToken(claims);
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return refreshedToken;
    }

    /**
     * 从token中获取登录用户名
     *
     * @param token 客户端传入的token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get(CLAIM_KEY_USERNAME).toString();
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return username;
    }

    /**
     * 从token中获取JWT发布时间
     *
     * @param token 客户端传入的token
     * @return 发布时间
     */
    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return created;
    }

    /**
     * 从token中获取JWT过期时间
     *
     * @param token 客户端传入的token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return expiration;
    }

    /**
     * 从token中获取JWT中的权限
     *
     * @param token 客户端传入的token
     * @return 权限
     */
    public Collection<? extends GrantedAuthority> getAudienceFromToken(String token) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            final Claims claims = getClaimsFromToken(token);
            authorities = (Collection<? extends GrantedAuthority>) claims.get(CLAIM_KEY_AUTHORITIES);
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return authorities;
    }

    /**
     * 从token中获取JWT中的负载
     *
     * @param token 客户端传入的token
     * @return 负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (RuntimeException e) {
            throw new RuntimeMBeanException(e);
        }
        return claims;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1000);
    }
}

