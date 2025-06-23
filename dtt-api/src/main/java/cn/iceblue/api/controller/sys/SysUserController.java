package cn.iceblue.api.controller.sys;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.iceblue.core.domain.po.LoginVo;
import cn.iceblue.core.domain.po.PageRequest;
import cn.iceblue.core.domain.vo.PageVO;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.domain.vo.UserInfoVo;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.data.service.SysUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 系统用户表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-04 14:11:43
 */
@Api(tags = {"系统/用户表对象功能接口"})
@RestController
@RequestMapping("sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    /**
     * 分页查询
     *
     * @param sysUserEntity 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping
    public R<PageVO<SysUserEntity>> paginQuery(SysUserEntity sysUserEntity, PageRequest pageRequest) {
        //1.分页参数
        long current = pageRequest.getPage();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        Page<SysUserEntity> pageResult = sysUserService.pagingQuery(sysUserEntity, current, size);
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
    public R<SysUserEntity> queryById(@PathVariable("id") String id) {
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
    public R<Boolean> add(@RequestBody SysUserEntity sysUser) {
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
    public R<Boolean> edit(@RequestBody SysUserEntity sysUser) {
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
    public R<Boolean> deleteById(@PathVariable("id") String id) {
        return R.ok(sysUserService.removeById(id));
    }

    /**
     * 用户登录
     *
     * @param sysUser:
     * @return R<SaTokenInfo>
     * @author IceBlue
     * @date 2025/6/10 下午4:03
     **/
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R<SaTokenInfo> login(@RequestBody @Valid LoginVo sysUser) {
        SysUserEntity entity = sysUserService.login(sysUser);

        // 第1步，先登录上
        StpUtil.login(entity.getId());
        // 第2步，获取 Token  相关参数
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 第3步，返回给前端
        return R.ok(tokenInfo);
    }

    /**
     * 获取用户信息
     *
     * @return R<UserInfoVo>
     * @author IceBlue
     * @date 2025/6/18 下午3:03
     **/
    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public R<UserInfoVo> info() {
        String userId = StpUtil.getLoginIdAsString();
        UserInfoVo vo = sysUserService.info(userId);
        return R.ok(vo);
    }

    @ApiOperation("注销登录")
    @PostMapping("/logout")
    public R<?> logout() {
        StpUtil.logout();
        return R.ok();
    }
}
