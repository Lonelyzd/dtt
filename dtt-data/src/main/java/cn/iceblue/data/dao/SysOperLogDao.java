package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysOperLogEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作日志记录
 *
 * @author IceBlue
 * @email 
 * @date 2025-07-02 15:27:29
 */
@Mapper
public interface SysOperLogDao extends BaseMapper<SysOperLogEntity> {

    /**
    * 分页查询指定行数据
    *
    * @param page 分页参数
    * @param wrapper 动态查询条件
    * @return 分页对象列表
    */
    IPage<SysOperLogEntity> selectByPage(IPage<SysOperLogEntity> page , @Param(Constants.WRAPPER) Wrapper<SysOperLogEntity> wrapper);

}
