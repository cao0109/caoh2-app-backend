package cn.caoh2.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author caoh2
 * @Date 2023/3/16 11:48
 * @Description: 测试控制器
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:index')")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello2")
    public String helloPost(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        String password = params.get("password");
        log.info("name: {}, password: {}", name, password);
        return "hello post";
    }
}
