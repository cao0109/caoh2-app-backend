package cn.caoh2.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author caoh2
 * @Date 2023/3/24 13:44
 * @Description: fastjson序列化
 * @Version 1.0
 */


public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    static {
        ParserConfig.getGlobalInstance().addAccept("cn.caoh2.app.entity.JwtUser");
        ParserConfig.getGlobalInstance().addAccept("org.springframework.security.core.authority.SimpleGrantedAuthority");
    }

    private final Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }
}

