package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import cn.iceblue.core.pojo.entity.SysRoleMenuEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色的菜单权限表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-03 14:18:12
 */
@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 分页查询指定行数据
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<SysRoleMenuEntity> selectByPage(IPage<SysRoleMenuEntity> page, @Param(Constants.WRAPPER) Wrapper<SysRoleMenuEntity> wrapper);



}
