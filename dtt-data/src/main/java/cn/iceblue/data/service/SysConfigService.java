package cn.iceblue.data.service;

import cn.iceblue.core.pojo.entity.SysConfigEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 参数配置表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-26 16:56:50
 */
public interface SysConfigService extends IService<SysConfigEntity> {
    /**
     * 分页查询
     *
     * @param sysConfig 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    Page<SysConfigEntity> pagingQuery(SysConfigEntity sysConfig, long current, long size);

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    boolean selectCaptchaEnabled();

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);
}

