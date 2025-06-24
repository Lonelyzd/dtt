package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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


}

