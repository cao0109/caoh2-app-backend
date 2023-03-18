package cn.caoh2.app.mapper;

import cn.caoh2.app.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 91523
 * @description 针对表【tb_role(角色表)】的数据库操作Mapper
 * @createDate 2023-03-16 12:27:58
 * @Entity cn.caoh2.app.entity.Role
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




