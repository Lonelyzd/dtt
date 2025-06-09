package cn.iceblue.data.service.impl;

import cn.iceblue.core.pojo.entity.SysRoleEntity;
import cn.iceblue.data.dao.SysRoleDao;
import cn.iceblue.data.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    /**
     * 分页查询
     *
     * @param sysRole 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    @Override
    public Page<SysRoleEntity> pagingQuery(SysRoleEntity sysRole, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();

                    if (StringUtils.isNotBlank(sysRole.getId())){
                queryWrapper.eq(SysRoleEntity::getId, sysRole.getId());
            }
                                            if (StringUtils.isNotBlank(sysRole.getRoleName())){
                queryWrapper.eq(SysRoleEntity::getRoleName, sysRole.getRoleName());
            }
                                            if (StringUtils.isNotBlank(sysRole.getRoleDesc())){
                queryWrapper.eq(SysRoleEntity::getRoleDesc, sysRole.getRoleDesc());
            }
                                                        if (!Objects.isNull(sysRole.getStatus())){
                queryWrapper.eq(SysRoleEntity::getStatus, sysRole.getStatus());
            }
                                if (StringUtils.isNotBlank(sysRole.getTenantId())){
                queryWrapper.eq(SysRoleEntity::getTenantId, sysRole.getTenantId());
            }
                                                        if (!Objects.isNull(sysRole.getRevision())){
                queryWrapper.eq(SysRoleEntity::getRevision, sysRole.getRevision());
            }
                                if (StringUtils.isNotBlank(sysRole.getCreatedBy())){
                queryWrapper.eq(SysRoleEntity::getCreatedBy, sysRole.getCreatedBy());
            }
                                                        if (!Objects.isNull(sysRole.getCreatedTime())){
                queryWrapper.eq(SysRoleEntity::getCreatedTime, sysRole.getCreatedTime());
            }
                                if (StringUtils.isNotBlank(sysRole.getUpdatedBy())){
                queryWrapper.eq(SysRoleEntity::getUpdatedBy, sysRole.getUpdatedBy());
            }
                                                        if (!Objects.isNull(sysRole.getUpdatedTime())){
                queryWrapper.eq(SysRoleEntity::getUpdatedTime, sysRole.getUpdatedTime());
            }
                    
        //2. 执行分页查询
        Page<SysRoleEntity> pagin = new Page<>(current, size, true);
        IPage<SysRoleEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

}