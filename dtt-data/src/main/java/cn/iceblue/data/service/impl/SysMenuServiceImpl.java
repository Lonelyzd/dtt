package cn.iceblue.data.service.impl;


import cn.iceblue.core.pojo.entity.SysMenuEntity;
import cn.iceblue.data.dao.SysMenuDao;
import cn.iceblue.data.service.SysMenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    /**
     * 分页查询
     *
     * @param sysMenu 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    @Override
    public Page<SysMenuEntity> pagingQuery(SysMenuEntity sysMenu, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysMenu.getId())) {
            queryWrapper.eq(SysMenuEntity::getId, sysMenu.getId());
        }
        if (StringUtils.isNotBlank(sysMenu.getMenuName())) {
            queryWrapper.eq(SysMenuEntity::getMenuName, sysMenu.getMenuName());
        }
        if (StringUtils.isNotBlank(sysMenu.getParentId())) {
            queryWrapper.eq(SysMenuEntity::getParentId, sysMenu.getParentId());
        }
        if (!Objects.isNull(sysMenu.getOrderNum())) {
            queryWrapper.eq(SysMenuEntity::getOrderNum, sysMenu.getOrderNum());
        }
        if (StringUtils.isNotBlank(sysMenu.getMenuPath())) {
            queryWrapper.eq(SysMenuEntity::getMenuPath, sysMenu.getMenuPath());
        }
        if (!Objects.isNull(sysMenu.getFrameFlag())) {
            queryWrapper.eq(SysMenuEntity::getFrameFlag, sysMenu.getFrameFlag());
        }
        if (!Objects.isNull(sysMenu.getIsCache())) {
            queryWrapper.eq(SysMenuEntity::getIsCache, sysMenu.getIsCache());
        }
        if (!Objects.isNull(sysMenu.getMenuType())) {
            queryWrapper.eq(SysMenuEntity::getMenuType, sysMenu.getMenuType());
        }
        if (!Objects.isNull(sysMenu.getStatus())) {
            queryWrapper.eq(SysMenuEntity::getStatus, sysMenu.getStatus());
        }
        if (StringUtils.isNotBlank(sysMenu.getPerms())) {
            queryWrapper.eq(SysMenuEntity::getPerms, sysMenu.getPerms());
        }
        if (StringUtils.isNotBlank(sysMenu.getMenuIcon())) {
            queryWrapper.eq(SysMenuEntity::getMenuIcon, sysMenu.getMenuIcon());
        }
        if (StringUtils.isNotBlank(sysMenu.getRemark())) {
            queryWrapper.eq(SysMenuEntity::getRemark, sysMenu.getRemark());
        }
        if (!Objects.isNull(sysMenu.getReadOnly())) {
            queryWrapper.eq(SysMenuEntity::getReadOnly, sysMenu.getReadOnly());
        }
        if (StringUtils.isNotBlank(sysMenu.getTenantId())) {
            queryWrapper.eq(SysMenuEntity::getTenantId, sysMenu.getTenantId());
        }
        if (!Objects.isNull(sysMenu.getRevision())) {
            queryWrapper.eq(SysMenuEntity::getRevision, sysMenu.getRevision());
        }
        if (StringUtils.isNotBlank(sysMenu.getCreatedBy())) {
            queryWrapper.eq(SysMenuEntity::getCreatedBy, sysMenu.getCreatedBy());
        }
        if (!Objects.isNull(sysMenu.getCreatedTime())) {
            queryWrapper.eq(SysMenuEntity::getCreatedTime, sysMenu.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysMenu.getUpdatedBy())) {
            queryWrapper.eq(SysMenuEntity::getUpdatedBy, sysMenu.getUpdatedBy());
        }
        if (!Objects.isNull(sysMenu.getUpdatedTime())) {
            queryWrapper.eq(SysMenuEntity::getUpdatedTime, sysMenu.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysMenuEntity> pagin = new Page<>(current, size, true);
        IPage<SysMenuEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 根据条件查询所有菜单
     *
     * @param sysMenu :
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/6/20 下午1:17
     **/
    @Override
    public List<SysMenuEntity> list(SysMenuEntity sysMenu) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysMenuEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysMenu.getParentId())) {
            queryWrapper.eq(SysMenuEntity::getParentId, sysMenu.getParentId());
        }
        if (!Objects.isNull(sysMenu.getOrderNum())) {
            queryWrapper.eq(SysMenuEntity::getOrderNum, sysMenu.getOrderNum());
        }
        if (!Objects.isNull(sysMenu.getFrameFlag())) {
            queryWrapper.eq(SysMenuEntity::getFrameFlag, sysMenu.getFrameFlag());
        }
        if (!Objects.isNull(sysMenu.getIsCache())) {
            queryWrapper.eq(SysMenuEntity::getIsCache, sysMenu.getIsCache());
        }
        if (!Objects.isNull(sysMenu.getMenuType())) {
            queryWrapper.eq(SysMenuEntity::getMenuType, sysMenu.getMenuType());
        }
        if (!Objects.isNull(sysMenu.getStatus())) {
            queryWrapper.eq(SysMenuEntity::getStatus, sysMenu.getStatus());
        }
        if (StringUtils.isNotBlank(sysMenu.getPerms())) {
            queryWrapper.eq(SysMenuEntity::getPerms, sysMenu.getPerms());
        }
        if (!Objects.isNull(sysMenu.getReadOnly())) {
            queryWrapper.eq(SysMenuEntity::getReadOnly, sysMenu.getReadOnly());
        }
        if (StringUtils.isNotBlank(sysMenu.getTenantId())) {
            queryWrapper.eq(SysMenuEntity::getTenantId, sysMenu.getTenantId());
        }
        if (!Objects.isNull(sysMenu.getRevision())) {
            queryWrapper.eq(SysMenuEntity::getRevision, sysMenu.getRevision());
        }
        return this.baseMapper.selectList(queryWrapper);
    }


    /**
     * 根据用户ID查询用户所有菜单
     *
     * @param userId:
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/7/9 下午3:46
     **/
    @Override
    public List<SysMenuEntity> getSysMenuByUserId(String userId) {
        return this.baseMapper.selectMenuByUserId(userId);
    }

    /**
     * 根据用户ID查询用户所有权限
     *
     * @param userId:
     * @return List<String>
     * @author IceBlue
     * @date 2025/7/11 下午3:50
     **/
    @Override
    public Set<String> getMenuPermsByUserId(String userId) {
        List<String> strings = this.baseMapper.selectMenuPermsByUserId(userId);

        Set<String> permsSet = new HashSet<>();
        for (String perm : strings) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

}