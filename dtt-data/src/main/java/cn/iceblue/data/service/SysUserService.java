package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统用户表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 15:32:23
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<SysUserEntity> pagingQuery(SysUserEntity sysUser, long current, long size) ;
}

