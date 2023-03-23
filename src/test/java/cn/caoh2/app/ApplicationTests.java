package cn.caoh2.app;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.entity.User;
import cn.caoh2.app.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MenuMapper menuMapper;

    @Test
    void contextLoads() {
        UserDto userDto = new UserDto();
        User user = new User();
        user.setUserId(1L);
        user.setUsername("caoh3");
        userDto.setUser(user);
        redisTemplate.opsForValue().set("user2", userDto);
    }

    @Test
    public void testSelectMenuPermsByUserId() {
        List<String> list = menuMapper.selectPermsByUserId(1L);
        System.out.println(list);
    }
}
