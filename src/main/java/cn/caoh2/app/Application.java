package cn.caoh2.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching // 开启缓存
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application start success");
    }

}
