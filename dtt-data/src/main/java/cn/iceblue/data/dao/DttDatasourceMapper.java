package cn.iceblue.data.dao;

import cn.iceblue.api.model.DttDatasource;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

 /**
 * 数据源表;(DTT_DATASOURCE)表数据库访问层
 * @author : http://www.chiner.pro
 * @date : 2025-5-9
 */
@Mapper
public interface DttDatasourceMapper  extends BaseMapper<DttDatasource>{
    /** 
     * 分页查询指定行数据
     *
     * @param page 分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<DttDatasource> selectByPage(IPage<DttDatasource> page , @Param(Constants.WRAPPER) Wrapper<DttDatasource> wrapper);
}