package cn.iceblue.data.service.impl;

import cn.iceblue.core.domain.enums.ResponseTemplate;
import cn.iceblue.core.domain.enums.dict.UserStatusDict;
import cn.iceblue.core.domain.po.LoginVo;
import cn.iceblue.core.domain.vo.UserInfoVo;
import cn.iceblue.core.exception.DttRuntimeException;
import cn.iceblue.core.pojo.entity.SysRoleEntity;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.core.util.Assert;
import cn.iceblue.core.util.RsaEncryptUtils;
import cn.iceblue.data.dao.SysUserDao;
import cn.iceblue.data.dao.SysUserRoleDao;
import cn.iceblue.data.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private TextEncryptor textEncryptor;

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    @Override
    public Page<SysUserEntity> pagingQuery(SysUserEntity sysUser, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysUser.getId())) {
            queryWrapper.eq(SysUserEntity::getId, sysUser.getId());
        }
        if (StringUtils.isNotBlank(sysUser.getUserCode())) {
            queryWrapper.eq(SysUserEntity::getUserCode, sysUser.getUserCode());
        }
        if (StringUtils.isNotBlank(sysUser.getUserName())) {
            queryWrapper.eq(SysUserEntity::getUserName, sysUser.getUserName());
        }
        if (StringUtils.isNotBlank(sysUser.getUserPassword())) {
            queryWrapper.eq(SysUserEntity::getUserPassword, sysUser.getUserPassword());
        }
        if (!Objects.isNull(sysUser.getReadOnly())) {
            queryWrapper.eq(SysUserEntity::getReadOnly, sysUser.getReadOnly());
        }
        if (StringUtils.isNotBlank(sysUser.getEmail())) {
            queryWrapper.eq(SysUserEntity::getEmail, sysUser.getEmail());
        }
        if (StringUtils.isNotBlank(sysUser.getPhone())) {
            queryWrapper.eq(SysUserEntity::getPhone, sysUser.getPhone());
        }
        if (StringUtils.isNotBlank(sysUser.getAvatar())) {
            queryWrapper.eq(SysUserEntity::getAvatar, sysUser.getAvatar());
        }
        if (!Objects.isNull(sysUser.getStatus())) {
            queryWrapper.eq(SysUserEntity::getStatus, sysUser.getStatus());
        }
        if (!Objects.isNull(sysUser.getLastLoginIp())) {
            queryWrapper.eq(SysUserEntity::getLastLoginIp, sysUser.getLastLoginIp());
        }
        if (StringUtils.isNotBlank(sysUser.getRemark())) {
            queryWrapper.eq(SysUserEntity::getRemark, sysUser.getRemark());
        }
        if (StringUtils.isNotBlank(sysUser.getTenantId())) {
            queryWrapper.eq(SysUserEntity::getTenantId, sysUser.getTenantId());
        }
        if (!Objects.isNull(sysUser.getRevision())) {
            queryWrapper.eq(SysUserEntity::getRevision, sysUser.getRevision());
        }
        if (StringUtils.isNotBlank(sysUser.getCreatedBy())) {
            queryWrapper.eq(SysUserEntity::getCreatedBy, sysUser.getCreatedBy());
        }
        if (!Objects.isNull(sysUser.getCreatedTime())) {
            queryWrapper.eq(SysUserEntity::getCreatedTime, sysUser.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysUser.getUpdatedBy())) {
            queryWrapper.eq(SysUserEntity::getUpdatedBy, sysUser.getUpdatedBy());
        }
        if (!Objects.isNull(sysUser.getUpdatedTime())) {
            queryWrapper.eq(SysUserEntity::getUpdatedTime, sysUser.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysUserEntity> pagin = new Page<>(current, size, true);
        IPage<SysUserEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 用户登录
     *
     * @param sysUser :
     * @return SysUserEntity
     * @author IceBlue
     * @date 2025/6/10 下午4:06
     **/
    @Override
    public SysUserEntity login(LoginVo sysUser) {
        SysUserEntity entity = this.getOne(Wrappers
                        .<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserCode, sysUser.getUsername())
                        .ne(SysUserEntity::getStatus, UserStatusDict.DELETED.getCode())
                , false
        );

        Assert.notNull(entity, ResponseTemplate.USER_NON_EXISTENT);

        try {
            boolean equals = RsaEncryptUtils.decrypt(sysUser.getPassword()).equals(textEncryptor.decrypt(entity.getUserPassword()));
            Assert.isTrue(equals, ResponseTemplate.DECODE_ERROR);
        } catch (Exception e) {
            log.error("解密失败,源字符串:{}", sysUser.getPassword());
            throw new DttRuntimeException(e, ResponseTemplate.DECODE_ERROR);
        }
        Assert.isTrue(!UserStatusDict.DEACTIVATE.getCode().equals(entity.getStatus()), ResponseTemplate.USER_LOCKED);
        return entity;
    }

    /**
     * 获取用户信息
     *
     * @param userId :
     * @return UserInfoVo
     * @author IceBlue
     * @date 2025/6/18 下午3:04
     **/
    @Override
    public UserInfoVo info(String userId) {
        UserInfoVo vo = new UserInfoVo();
        SysUserEntity user = getById(userId);
        vo.setAvatar(user.getAvatar());
        vo.setName(user.getUserName());
        vo.setIntroduction(user.getRemark());

        List<SysRoleEntity> sysRoleEntities = sysUserRoleDao.selectRoleByUserId(userId);
        List<String> collect = sysRoleEntities.stream()
                .map(SysRoleEntity::getRoleName)
                .collect(Collectors.toList());

        vo.setRoles(collect);
        return vo;
    }


}