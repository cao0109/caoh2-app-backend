package cn.caoh2.app.mapper;

import cn.caoh2.app.dto.UserDto;
import cn.caoh2.app.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 91523
 * @description 针对表【tb_user(用户表)】的数据库操作Mapper
 * @createDate 2023-03-16 12:27:08
 * @Entity cn.caoh2.app.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    UserDto selectUserDtoByUsername(String username);

}




