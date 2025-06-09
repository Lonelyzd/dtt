package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysRoleEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统角色表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 14:18:12
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRoleEntity> {

    /**
    * 分页查询指定行数据
    *
    * @param page 分页参数
    * @param wrapper 动态查询条件
    * @return 分页对象列表
    */
    IPage<SysRoleEntity> selectByPage(IPage<SysRoleEntity> page , @Param(Constants.WRAPPER) Wrapper<SysRoleEntity> wrapper);

}
