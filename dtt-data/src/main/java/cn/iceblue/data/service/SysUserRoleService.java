package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户拥有的角色
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 15:32:23
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {
    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<SysUserRoleEntity> pagingQuery(SysUserRoleEntity sysUserRole, long current, long size) ;
}

