package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysOperLogEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 操作日志记录
 *
 * @author IceBlue
 * @email
 * @date 2025-07-02 15:27:29
 */
public interface SysOperLogService extends IService<SysOperLogEntity> {
    /**
     * 分页查询
     *
     * @param sysOperLog 筛选条件
     * @param current    当前页码
     * @param size       每页大小
     * @return
     */
    Page<SysOperLogEntity> pagingQuery(SysOperLogEntity sysOperLog, long current, long size);
}

