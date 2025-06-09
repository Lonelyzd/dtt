package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统菜单表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-03 14:18:12
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
    * 分页查询指定行数据
    *
    * @param page 分页参数
    * @param wrapper 动态查询条件
    * @return 分页对象列表
    */
    IPage<SysMenuEntity> selectByPage(IPage<SysMenuEntity> page , @Param(Constants.WRAPPER) Wrapper<SysMenuEntity> wrapper);

}
