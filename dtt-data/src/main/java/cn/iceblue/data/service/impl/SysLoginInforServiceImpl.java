package cn.iceblue.data.service.impl;

import cn.iceblue.core.pojo.entity.SysLoginInforEntity;
import cn.iceblue.data.dao.SysLoginInforDao;
import cn.iceblue.data.service.SysLoginInforService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("sysLoginInforService")
public class SysLoginInforServiceImpl extends ServiceImpl<SysLoginInforDao, SysLoginInforEntity> implements SysLoginInforService {

    /**
     * 分页查询
     *
     * @param sysLoginInfor 筛选条件
     * @param current       当前页码
     * @param size          每页大小
     * @return
     */
    @Override
    public Page<SysLoginInforEntity> pagingQuery(SysLoginInforEntity sysLoginInfor, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysLoginInforEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysLoginInfor.getId())) {
            queryWrapper.eq(SysLoginInforEntity::getId, sysLoginInfor.getId());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getUserId())) {
            queryWrapper.eq(SysLoginInforEntity::getUserId, sysLoginInfor.getUserId());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getIpaddr())) {
            queryWrapper.eq(SysLoginInforEntity::getIpaddr, sysLoginInfor.getIpaddr());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getLoginLocation())) {
            queryWrapper.eq(SysLoginInforEntity::getLoginLocation, sysLoginInfor.getLoginLocation());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getBrowser())) {
            queryWrapper.eq(SysLoginInforEntity::getBrowser, sysLoginInfor.getBrowser());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getOs())) {
            queryWrapper.eq(SysLoginInforEntity::getOs, sysLoginInfor.getOs());
        }
        if (!Objects.isNull(sysLoginInfor.getStatus())) {
            queryWrapper.eq(SysLoginInforEntity::getStatus, sysLoginInfor.getStatus());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getMsg())) {
            queryWrapper.eq(SysLoginInforEntity::getMsg, sysLoginInfor.getMsg());
        }
        if (!Objects.isNull(sysLoginInfor.getLoginTime())) {
            queryWrapper.eq(SysLoginInforEntity::getLoginTime, sysLoginInfor.getLoginTime());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getTenantId())) {
            queryWrapper.eq(SysLoginInforEntity::getTenantId, sysLoginInfor.getTenantId());
        }
        if (!Objects.isNull(sysLoginInfor.getRevision())) {
            queryWrapper.eq(SysLoginInforEntity::getRevision, sysLoginInfor.getRevision());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getCreatedBy())) {
            queryWrapper.eq(SysLoginInforEntity::getCreatedBy, sysLoginInfor.getCreatedBy());
        }
        if (!Objects.isNull(sysLoginInfor.getCreatedTime())) {
            queryWrapper.eq(SysLoginInforEntity::getCreatedTime, sysLoginInfor.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysLoginInfor.getUpdatedBy())) {
            queryWrapper.eq(SysLoginInforEntity::getUpdatedBy, sysLoginInfor.getUpdatedBy());
        }
        if (!Objects.isNull(sysLoginInfor.getUpdatedTime())) {
            queryWrapper.eq(SysLoginInforEntity::getUpdatedTime, sysLoginInfor.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysLoginInforEntity> pagin = new Page<>(current, size, true);
        IPage<SysLoginInforEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

}