package cn.iceblue.data.service;

import cn.iceblue.core.domain.po.LoginVo;
import cn.iceblue.core.domain.vo.UserInfoVo;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统用户表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-03 15:32:23
 */
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    Page<SysUserEntity> pagingQuery(SysUserEntity sysUser, long current, long size);

    /**
     * 用户登录
     *
     * @param sysUser:
     * @return SysUserEntity
     * @author IceBlue
     * @date 2025/6/10 下午4:06
     **/
    SysUserEntity login(LoginVo sysUser);

    /**
     * 获取用户信息
     *
     * @param userId:
     * @return UserInfoVo
     * @author IceBlue
     * @date 2025/6/18 下午3:04
     **/
    UserInfoVo info(String userId);
}

