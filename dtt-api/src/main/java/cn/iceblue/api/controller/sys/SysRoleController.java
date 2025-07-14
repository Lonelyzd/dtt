package cn.iceblue.api.controller.sys;

import cn.iceblue.core.domain.po.PageRequest;
import cn.iceblue.core.domain.vo.PageVo;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.pojo.entity.SysRoleEntity;
import cn.iceblue.data.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 系统角色表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:11:43
 */
@Api(tags = "系统/角色表对象功能接口")
@RestController
@RequestMapping("sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;



    /**
    * 分页查询
    *
    * @param sysRoleEntity 筛选条件
    * @param pageRequest 分页对象
    * @return 查询结果
    */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVo<SysRoleEntity>> paginQuery(SysRoleEntity sysRoleEntity, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysRoleEntity> pageResult = sysRoleService.pagingQuery(sysRoleEntity, current,size);
        //3. 分页结果组装
        return R.ok(new PageVo<>(current, size, pageResult.getTotal(), pageResult.getRecords()));
    }



    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("/{id}")
    public R<SysRoleEntity> queryById(@PathVariable("id") String id){
        return R.ok(sysRoleService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody SysRoleEntity sysRole){
        return R.ok(sysRoleService.save(sysRole));
    }


    /**
     * 更新数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody SysRoleEntity sysRole){
        return R.ok(sysRoleService.updateById(sysRole));
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
        return R.ok(sysRoleService.removeById(id));
    }

}
