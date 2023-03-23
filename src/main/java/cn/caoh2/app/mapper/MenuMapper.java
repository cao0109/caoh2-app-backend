package cn.caoh2.app.mapper;

import cn.caoh2.app.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 91523
 * @description 针对表【sys_menu(菜单管理)】的数据库操作Mapper
 * @createDate 2023-03-19 19:52:14
 * @Entity cn.caoh2.app.entity.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long userId);
}




