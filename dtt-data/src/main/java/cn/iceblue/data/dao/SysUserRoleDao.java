package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysUserRoleEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户拥有的角色
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 14:18:12
 */
@Mapper
public interface SysUserRoleDao extends BaseMapper<SysUserRoleEntity> {

    /**
    * 分页查询指定行数据
    *
    * @param page 分页参数
    * @param wrapper 动态查询条件
    * @return 分页对象列表
    */
    IPage<SysUserRoleEntity> selectByPage(IPage<SysUserRoleEntity> page , @Param(Constants.WRAPPER) Wrapper<SysUserRoleEntity> wrapper);

}
