package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysLoginInforEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统访问记录
 *
 * @author IceBlue
 * @email
 * @date 2025-06-30 10:25:59
 */
@Mapper
public interface SysLoginInforDao extends BaseMapper<SysLoginInforEntity> {

    /**
     * 分页查询指定行数据
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<SysLoginInforEntity> selectByPage(IPage<SysLoginInforEntity> page, @Param(Constants.WRAPPER) Wrapper<SysLoginInforEntity> wrapper);

}
