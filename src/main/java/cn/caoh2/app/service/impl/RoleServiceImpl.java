package cn.caoh2.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.caoh2.app.entity.Role;
import cn.caoh2.app.service.RoleService;
import cn.caoh2.app.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 91523
* @description 针对表【sys_role(角色表)】的数据库操作Service实现
* @createDate 2023-03-19 19:51:55
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




