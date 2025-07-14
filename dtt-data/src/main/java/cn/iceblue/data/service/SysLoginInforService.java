package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysLoginInforEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统访问记录
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-30 10:25:59
 */
public interface SysLoginInforService extends IService<SysLoginInforEntity> {
    /**
     * 分页查询
     *
     * @param sysLoginInfor 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<SysLoginInforEntity> pagingQuery(SysLoginInforEntity sysLoginInfor, long current, long size) ;
}

