package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * 系统菜单表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-03 15:32:23
 */
public interface SysMenuService extends IService<SysMenuEntity> {
    /**
     * 分页查询
     *
     * @param sysMenu 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    Page<SysMenuEntity> pagingQuery(SysMenuEntity sysMenu, long current, long size);

    /**
     * 根据条件查询所有菜单
     *
     * @param sysMenuEntity:
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/6/20 下午1:17
     **/
    List<SysMenuEntity> list(SysMenuEntity sysMenuEntity);


    /**
     * 根据用户ID查询用户所有菜单
     *
     * @param userId:
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/7/9 下午3:46
     **/
    List<SysMenuEntity> getSysMenuByUserId(String userId);

    /**
     * 根据用户ID查询用户所有权限
     *
     * @param userId:
     * @return List<String>
     * @author IceBlue
     * @date 2025/7/11 下午3:50
     **/
    Set<String> getMenuPermsByUserId(String userId);
}

