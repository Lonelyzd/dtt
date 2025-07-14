package cn.iceblue.data.dao;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据用户ID集合查询角色菜单集合
     *
     * @param userId:
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/6/23 上午11:15
     **/
    List<SysMenuEntity> selectMenuByUserId(@Param("userId") String userId);

    /** 根据用户ID集合查询权限集合
    * @author IceBlue
    * @date 2025/7/11 下午3:47
    * @param userId:
    * @return List<String>
    **/
    List<String> selectMenuPermsByUserId(@Param("userId") String userId);
}
