package cn.iceblue.data.service.impl;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import cn.iceblue.core.pojo.entity.SysRoleMenuEntity;
import cn.iceblue.data.dao.SysRoleMenuDao;
import cn.iceblue.data.service.SysRoleMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    /**
     * 分页查询
     *
     * @param sysRoleMenu 筛选条件
     * @param current     当前页码
     * @param size        每页大小
     * @return
     */
    @Override
    public Page<SysRoleMenuEntity> pagingQuery(SysRoleMenuEntity sysRoleMenu, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysRoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysRoleMenu.getId())) {
            queryWrapper.eq(SysRoleMenuEntity::getId, sysRoleMenu.getId());
        }
        if (StringUtils.isNotBlank(sysRoleMenu.getRoleId())) {
            queryWrapper.eq(SysRoleMenuEntity::getRoleId, sysRoleMenu.getRoleId());
        }
        if (StringUtils.isNotBlank(sysRoleMenu.getMenuId())) {
            queryWrapper.eq(SysRoleMenuEntity::getMenuId, sysRoleMenu.getMenuId());
        }
        if (StringUtils.isNotBlank(sysRoleMenu.getTenantId())) {
            queryWrapper.eq(SysRoleMenuEntity::getTenantId, sysRoleMenu.getTenantId());
        }
        if (!Objects.isNull(sysRoleMenu.getRevision())) {
            queryWrapper.eq(SysRoleMenuEntity::getRevision, sysRoleMenu.getRevision());
        }
        if (StringUtils.isNotBlank(sysRoleMenu.getCreatedBy())) {
            queryWrapper.eq(SysRoleMenuEntity::getCreatedBy, sysRoleMenu.getCreatedBy());
        }
        if (!Objects.isNull(sysRoleMenu.getCreatedTime())) {
            queryWrapper.eq(SysRoleMenuEntity::getCreatedTime, sysRoleMenu.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysRoleMenu.getUpdatedBy())) {
            queryWrapper.eq(SysRoleMenuEntity::getUpdatedBy, sysRoleMenu.getUpdatedBy());
        }
        if (!Objects.isNull(sysRoleMenu.getUpdatedTime())) {
            queryWrapper.eq(SysRoleMenuEntity::getUpdatedTime, sysRoleMenu.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysRoleMenuEntity> pagin = new Page<>(current, size, true);
        IPage<SysRoleMenuEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 根据角色ID集合查询角色菜单集合
     *
     * @param roleIds :
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/6/23 上午11:15
     **/
    @Override
    public List<SysMenuEntity> getMenuByRoleId(List<String> roleIds) {
        return this.baseMapper.getMenuByRoleId(roleIds);
    }

}