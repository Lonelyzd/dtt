package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysRoleEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统角色表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 15:32:23
 */
public interface SysRoleService extends IService<SysRoleEntity> {
    /**
     * 分页查询
     *
     * @param sysRole 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<SysRoleEntity> pagingQuery(SysRoleEntity sysRole, long current, long size) ;
}

