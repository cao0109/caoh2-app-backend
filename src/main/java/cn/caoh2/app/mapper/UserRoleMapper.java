package cn.caoh2.app.mapper;

import cn.caoh2.app.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author 91523
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Mapper
 * @createDate 2023-03-19 19:53:31
 * @Entity cn.caoh2.app.entity.UserRole
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Insert("insert into sys_user_role(user_id, role_id) values(#{userId}, #{roleId})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    void insertUserRole(Long userId, Integer roleId);
}




