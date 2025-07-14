package cn.iceblue.api.controller.sys;

import cn.iceblue.core.domain.po.PageRequest;
import cn.iceblue.core.domain.vo.PageVo;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.pojo.entity.SysLoginInforEntity;
import cn.iceblue.data.service.SysLoginInforService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 系统访问记录
 *
 * @author IceBlue
 * @email 
 * @date 2025-06-30 10:25:59
 */
@Api(tags = "系统访问记录对象功能接口")
@RestController
@RequestMapping("common/syslogininfor")
public class SysLoginInforController {
    @Autowired
    private SysLoginInforService sysLoginInforService;



    /**
    * 分页查询
    *
    * @param sysLoginInforEntity 筛选条件
    * @param pageRequest 分页对象
    * @return 查询结果
    */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVo<SysLoginInforEntity>> paginQuery(SysLoginInforEntity sysLoginInforEntity, PageRequest pageRequest){
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysLoginInforEntity> pageResult = sysLoginInforService.pagingQuery(sysLoginInforEntity, current,size);
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
    public R<SysLoginInforEntity> queryById(@PathVariable("id") String id){
        return R.ok(sysLoginInforService.getById(id));
    }


    /**
     * 新增数据
     *
     * @param sysLoginInfor 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Boolean> add(@RequestBody SysLoginInforEntity sysLoginInfor){
        return R.ok(sysLoginInforService.save(sysLoginInfor));
    }


    /**
     * 更新数据
     *
     * @param sysLoginInfor 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping
    public R<Boolean> edit(@RequestBody SysLoginInforEntity sysLoginInfor){
        return R.ok(sysLoginInforService.updateById(sysLoginInfor));
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
        return R.ok(sysLoginInforService.removeById(id));
    }

}
