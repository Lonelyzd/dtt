package cn.iceblue.api.controller.sys;

import cn.iceblue.core.domain.po.PageRequest;
import cn.iceblue.core.domain.vo.PageVo;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.pojo.entity.SysConfigEntity;
import cn.iceblue.data.service.SysConfigService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 参数配置表
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-26 16:56:50
 */
@Api(tags = "参数配置表对象功能接口")
@RestController
@RequestMapping("common/sysconfig")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;



    /**
    * 分页查询
    *
    * @param sysConfigEntity 筛选条件
    * @param pageRequest 分页对象
    * @return 查询结果
    */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVo<SysConfigEntity>> paginQuery(SysConfigEntity sysConfigEntity, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysConfigEntity> pageResult = sysConfigService.pagingQuery(sysConfigEntity, current,size);
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
    public R<SysConfigEntity> queryById(@PathVariable("id") String id){
        return R.ok(sysConfigService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param sysConfig 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody SysConfigEntity sysConfig){
        return R.ok(sysConfigService.save(sysConfig));
    }


    /**
     * 更新数据
     *
     * @param sysConfig 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody SysConfigEntity sysConfig){
        return R.ok(sysConfigService.updateById(sysConfig));
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
        return R.ok(sysConfigService.removeById(id));
    }

}
