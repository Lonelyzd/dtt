package cn.iceblue.data.service.impl;

import cn.iceblue.core.pojo.entity.SysUserRoleEntity;
import cn.iceblue.data.dao.SysUserRoleDao;
import cn.iceblue.data.service.SysUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    /**
     * 分页查询
     *
     * @param sysUserRole 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    @Override
    public Page<SysUserRoleEntity> pagingQuery(SysUserRoleEntity sysUserRole, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();

                    if (StringUtils.isNotBlank(sysUserRole.getId())){
                queryWrapper.eq(SysUserRoleEntity::getId, sysUserRole.getId());
            }
                                            if (StringUtils.isNotBlank(sysUserRole.getUserId())){
                queryWrapper.eq(SysUserRoleEntity::getUserId, sysUserRole.getUserId());
            }
                                            if (StringUtils.isNotBlank(sysUserRole.getRoleId())){
                queryWrapper.eq(SysUserRoleEntity::getRoleId, sysUserRole.getRoleId());
            }
                                            if (StringUtils.isNotBlank(sysUserRole.getTenantId())){
                queryWrapper.eq(SysUserRoleEntity::getTenantId, sysUserRole.getTenantId());
            }
                                                        if (!Objects.isNull(sysUserRole.getRevision())){
                queryWrapper.eq(SysUserRoleEntity::getRevision, sysUserRole.getRevision());
            }
                                if (StringUtils.isNotBlank(sysUserRole.getCreatedBy())){
                queryWrapper.eq(SysUserRoleEntity::getCreatedBy, sysUserRole.getCreatedBy());
            }
                                                        if (!Objects.isNull(sysUserRole.getCreatedTime())){
                queryWrapper.eq(SysUserRoleEntity::getCreatedTime, sysUserRole.getCreatedTime());
            }
                                if (StringUtils.isNotBlank(sysUserRole.getUpdatedBy())){
                queryWrapper.eq(SysUserRoleEntity::getUpdatedBy, sysUserRole.getUpdatedBy());
            }
                                                        if (!Objects.isNull(sysUserRole.getUpdatedTime())){
                queryWrapper.eq(SysUserRoleEntity::getUpdatedTime, sysUserRole.getUpdatedTime());
            }
                    
        //2. 执行分页查询
        Page<SysUserRoleEntity> pagin = new Page<>(current, size, true);
        IPage<SysUserRoleEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

}