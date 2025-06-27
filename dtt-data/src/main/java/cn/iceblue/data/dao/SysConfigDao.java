package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysConfigEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 参数配置表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-26 16:56:50
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfigEntity> {

    /**
    * 分页查询指定行数据
    *
    * @param page 分页参数
    * @param wrapper 动态查询条件
    * @return 分页对象列表
    */
    IPage<SysConfigEntity> selectByPage(IPage<SysConfigEntity> page , @Param(Constants.WRAPPER) Wrapper<SysConfigEntity> wrapper);

}
