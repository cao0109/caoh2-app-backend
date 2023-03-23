package cn.caoh2.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.caoh2.app.entity.Menu;
import cn.caoh2.app.service.MenuService;
import cn.caoh2.app.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author 91523
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
* @createDate 2023-03-19 19:52:14
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




