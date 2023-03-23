package cn.caoh2.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author caoh2
 * @Date 2023/3/16 12:04
 * @Description: JWT工具类
 * @Version 1.0
 */

@Slf4j
@Component
public class JwtTokenUtil {

    // 用户名的key
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_USER = "sub";
    // jwt创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    // jwt过期时间
    private static final String JWT_CLAIMS_EXPIRATION = "exp";
    // 密钥
    private static final String SECRET_KEY = "your-256-bit-secret";
    // 过期时间
    private static final long VALIDITY_TIME_MS = 3600000; // 1h

    /**
     * 生成token
     *
     * @param userDetails 用户信息
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(JWT_CLAIMS_EXPIRATION, VALIDITY_TIME_MS);
        return createToken(claims, userDetails.getUsername());
    }

    public String generateToken(String useId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER, useId);
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(JWT_CLAIMS_EXPIRATION, VALIDITY_TIME_MS);
        return createToken(claims, useId);
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDITY_TIME_MS);
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(now).setExpiration(validity).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public String getUserInfoFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }
}

