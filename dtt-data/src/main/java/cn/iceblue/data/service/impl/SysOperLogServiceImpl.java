package cn.iceblue.data.service.impl;

import cn.iceblue.core.pojo.entity.SysOperLogEntity;
import cn.iceblue.data.dao.SysOperLogDao;
import cn.iceblue.data.service.SysOperLogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("sysOperLogService")
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogDao, SysOperLogEntity> implements SysOperLogService {

    /**
     * 分页查询
     *
     * @param sysOperLog 筛选条件
     * @param current    当前页码
     * @param size       每页大小
     * @return
     */
    @Override
    public Page<SysOperLogEntity> pagingQuery(SysOperLogEntity sysOperLog, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysOperLogEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysOperLog.getId())) {
            queryWrapper.eq(SysOperLogEntity::getId, sysOperLog.getId());
        }
        if (StringUtils.isNotBlank(sysOperLog.getTitle())) {
            queryWrapper.eq(SysOperLogEntity::getTitle, sysOperLog.getTitle());
        }
        if (!Objects.isNull(sysOperLog.getBusinessType())) {
            queryWrapper.eq(SysOperLogEntity::getBusinessType, sysOperLog.getBusinessType());
        }
        if (StringUtils.isNotBlank(sysOperLog.getMethod())) {
            queryWrapper.eq(SysOperLogEntity::getMethod, sysOperLog.getMethod());
        }
        if (StringUtils.isNotBlank(sysOperLog.getRequestMethod())) {
            queryWrapper.eq(SysOperLogEntity::getRequestMethod, sysOperLog.getRequestMethod());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperatorType())) {
            queryWrapper.eq(SysOperLogEntity::getOperatorType, sysOperLog.getOperatorType());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperUserId())) {
            queryWrapper.eq(SysOperLogEntity::getOperUserId, sysOperLog.getOperUserId());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperUrl())) {
            queryWrapper.eq(SysOperLogEntity::getOperUrl, sysOperLog.getOperUrl());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperIp())) {
            queryWrapper.eq(SysOperLogEntity::getOperIp, sysOperLog.getOperIp());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperLocation())) {
            queryWrapper.eq(SysOperLogEntity::getOperLocation, sysOperLog.getOperLocation());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperParam())) {
            queryWrapper.eq(SysOperLogEntity::getOperParam, sysOperLog.getOperParam());
        }
        if (StringUtils.isNotBlank(sysOperLog.getJsonResult())) {
            queryWrapper.eq(SysOperLogEntity::getJsonResult, sysOperLog.getJsonResult());
        }
        if (!Objects.isNull(sysOperLog.getStatus())) {
            queryWrapper.eq(SysOperLogEntity::getStatus, sysOperLog.getStatus());
        }
        if (StringUtils.isNotBlank(sysOperLog.getErrorMsg())) {
            queryWrapper.eq(SysOperLogEntity::getErrorMsg, sysOperLog.getErrorMsg());
        }
        if (StringUtils.isNotBlank(sysOperLog.getOperTime())) {
            queryWrapper.eq(SysOperLogEntity::getOperTime, sysOperLog.getOperTime());
        }
        if (!Objects.isNull(sysOperLog.getCostTime())) {
            queryWrapper.eq(SysOperLogEntity::getCostTime, sysOperLog.getCostTime());
        }
        if (StringUtils.isNotBlank(sysOperLog.getTenantId())) {
            queryWrapper.eq(SysOperLogEntity::getTenantId, sysOperLog.getTenantId());
        }
        if (!Objects.isNull(sysOperLog.getRevision())) {
            queryWrapper.eq(SysOperLogEntity::getRevision, sysOperLog.getRevision());
        }
        if (StringUtils.isNotBlank(sysOperLog.getCreatedBy())) {
            queryWrapper.eq(SysOperLogEntity::getCreatedBy, sysOperLog.getCreatedBy());
        }
        if (!Objects.isNull(sysOperLog.getCreatedTime())) {
            queryWrapper.eq(SysOperLogEntity::getCreatedTime, sysOperLog.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysOperLog.getUpdatedBy())) {
            queryWrapper.eq(SysOperLogEntity::getUpdatedBy, sysOperLog.getUpdatedBy());
        }
        if (!Objects.isNull(sysOperLog.getUpdatedTime())) {
            queryWrapper.eq(SysOperLogEntity::getUpdatedTime, sysOperLog.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysOperLogEntity> pagin = new Page<>(current, size, true);
        IPage<SysOperLogEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

}