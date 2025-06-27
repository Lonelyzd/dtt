package cn.iceblue.data.service.impl;

import cn.iceblue.core.domain.constant.CacheConstants;
import cn.iceblue.core.pojo.entity.SysConfigEntity;
import cn.iceblue.core.redis.RedisCache;
import cn.iceblue.core.util.text.Convert;
import cn.iceblue.data.dao.SysConfigDao;
import cn.iceblue.data.service.SysConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

    @Autowired
    private RedisCache redisCache;

    /**
     * 分页查询
     *
     * @param sysConfig 筛选条件
     * @param current   当前页码
     * @param size      每页大小
     * @return
     */
    @Override
    public Page<SysConfigEntity> pagingQuery(SysConfigEntity sysConfig, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysConfigEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysConfig.getId())) {
            queryWrapper.eq(SysConfigEntity::getId, sysConfig.getId());
        }
        if (StringUtils.isNotBlank(sysConfig.getConfigName())) {
            queryWrapper.eq(SysConfigEntity::getConfigName, sysConfig.getConfigName());
        }
        if (StringUtils.isNotBlank(sysConfig.getConfigKey())) {
            queryWrapper.eq(SysConfigEntity::getConfigKey, sysConfig.getConfigKey());
        }
        if (StringUtils.isNotBlank(sysConfig.getConfigValue())) {
            queryWrapper.eq(SysConfigEntity::getConfigValue, sysConfig.getConfigValue());
        }
        if (!Objects.isNull(sysConfig.getReadOnly())) {
            queryWrapper.eq(SysConfigEntity::getReadOnly, sysConfig.getReadOnly());
        }
        if (StringUtils.isNotBlank(sysConfig.getTenantId())) {
            queryWrapper.eq(SysConfigEntity::getTenantId, sysConfig.getTenantId());
        }
        if (!Objects.isNull(sysConfig.getRevision())) {
            queryWrapper.eq(SysConfigEntity::getRevision, sysConfig.getRevision());
        }
        if (StringUtils.isNotBlank(sysConfig.getCreatedBy())) {
            queryWrapper.eq(SysConfigEntity::getCreatedBy, sysConfig.getCreatedBy());
        }
        if (!Objects.isNull(sysConfig.getCreatedTime())) {
            queryWrapper.eq(SysConfigEntity::getCreatedTime, sysConfig.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysConfig.getUpdatedBy())) {
            queryWrapper.eq(SysConfigEntity::getUpdatedBy, sysConfig.getUpdatedBy());
        }
        if (!Objects.isNull(sysConfig.getUpdatedTime())) {
            queryWrapper.eq(SysConfigEntity::getUpdatedTime, sysConfig.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysConfigEntity> pagin = new Page<>(current, size, true);
        IPage<SysConfigEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
        if (StringUtils.isNotEmpty(configValue)) {
            return configValue;
        }
        SysConfigEntity retConfig = this.getOne(Wrappers
                .<SysConfigEntity>lambdaQuery()
                .eq(SysConfigEntity::getConfigKey, configKey), false);
        if (!Objects.isNull(retConfig)) {
            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getConfigValue());
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }

}