package cn.iceblue.api.controller;


import cn.iceblue.api.vo.PageRequest;
import cn.iceblue.api.vo.PageVO;
import cn.iceblue.api.vo.R;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.data.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:11:43
 */
@Api(tags = "系统用户表对象功能接口")
@RestController
@RequestMapping("sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;



    /**
    * 分页查询
    *
    * @param sysUserEntity 筛选条件
    * @param pageRequest 分页对象
    * @return 查询结果
    */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVO<SysUserEntity>> paginQuery(SysUserEntity sysUserEntity, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysUserEntity> pageResult = sysUserService.pagingQuery(sysUserEntity, current,size);
        //3. 分页结果组装
        return R.ok(new PageVO<>(pageResult));
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/{id}")
    public R<SysUserEntity> queryById(@PathVariable("id") String id){
        return R.ok(sysUserService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody SysUserEntity sysUser){
        return R.ok(sysUserService.save(sysUser));
    }


    /**
     * 更新数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody SysUserEntity sysUser){
        return R.ok(sysUserService.updateById(sysUser));
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("/{id}")
    public R<Boolean> deleteById(@PathVariable("id")String id){
        return R.ok(sysUserService.removeById(id));
    }

}
