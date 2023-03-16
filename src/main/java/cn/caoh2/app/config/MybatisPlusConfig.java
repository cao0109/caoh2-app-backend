package cn.caoh2.app.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @Author caoh2
 * @Date 2023/3/16 10:34
 * @Description: MybatisPlus配置
 */
@Configuration
@MapperScan("cn.caoh2.app.mapper.**")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * insert、update自动填充
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Object createTime = getFieldValByName("createTime", metaObject);
                if (null == createTime) {
                    setFieldValByName("createTime", LocalDateTime.now(), metaObject);
                    this.updateFill(metaObject);
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                Object updateTime = getFieldValByName("updateTime", metaObject);
                if (null == updateTime) {
                    setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
                }
            }
        };

    }
}
