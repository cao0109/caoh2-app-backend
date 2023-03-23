package cn.caoh2.app.mapper;

import cn.caoh2.app.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 91523
 * @description 针对表【sys_user(用户表)】的数据库操作Mapper
 * @createDate 2023-03-19 19:56:01
 * @Entity cn.caoh2.app.entity.SysUser
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}




