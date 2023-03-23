package cn.caoh2.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.caoh2.app.entity.RoleMenu;
import cn.caoh2.app.service.RoleMenuService;
import cn.caoh2.app.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 91523
* @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
* @createDate 2023-03-19 19:52:09
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

}




