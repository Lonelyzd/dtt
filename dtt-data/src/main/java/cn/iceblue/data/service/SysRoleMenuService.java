package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysRoleMenuEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色的菜单权限表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 15:32:23
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {
    /**
     * 分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<SysRoleMenuEntity> pagingQuery(SysRoleMenuEntity sysRoleMenu, long current, long size) ;
}

