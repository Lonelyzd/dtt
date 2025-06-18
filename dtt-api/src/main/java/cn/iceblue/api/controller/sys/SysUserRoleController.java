package cn.iceblue.api.controller.sys;

import cn.iceblue.core.domain.po.PageRequest;
import cn.iceblue.core.domain.vo.PageVO;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.pojo.entity.SysUserRoleEntity;
import cn.iceblue.data.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 用户拥有的角色
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:11:43
 */
@Api(tags = "系统/用户拥有的角色对象功能接口")
@RestController
@RequestMapping("sys/userrole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;



    /**
    * 分页查询
    *
    * @param sysUserRoleEntity 筛选条件
    * @param pageRequest 分页对象
    * @return 查询结果
    */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVO<SysUserRoleEntity>> paginQuery(SysUserRoleEntity sysUserRoleEntity, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysUserRoleEntity> pageResult = sysUserRoleService.pagingQuery(sysUserRoleEntity, current,size);
        //3. 分页结果组装
        return R.ok(new PageVO<>(current, size, pageResult.getTotal(), pageResult.getRecords()));
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/{id}")
    public R<SysUserRoleEntity> queryById(@PathVariable("id") String id){
        return R.ok(sysUserRoleService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody SysUserRoleEntity sysUserRole){
        return R.ok(sysUserRoleService.save(sysUserRole));
    }


    /**
     * 更新数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody SysUserRoleEntity sysUserRole){
        return R.ok(sysUserRoleService.updateById(sysUserRole));
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
        return R.ok(sysUserRoleService.removeById(id));
    }

}
