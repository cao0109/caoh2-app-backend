package cn.caoh2.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.caoh2.app.entity.UserRole;
import cn.caoh2.app.service.UserRoleService;
import cn.caoh2.app.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 91523
* @description 针对表【tb_user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2023-03-16 12:28:04
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




