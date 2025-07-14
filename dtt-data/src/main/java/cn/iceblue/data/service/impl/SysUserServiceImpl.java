package cn.iceblue.data.service.impl;

import cn.iceblue.core.domain.constant.BaseConstants;
import cn.iceblue.core.domain.constant.FrontConstants;
import cn.iceblue.core.domain.dto.SysMenuDto;
import cn.iceblue.core.domain.enums.dict.BooleanDict;
import cn.iceblue.core.domain.enums.dict.MenuTypeDict;
import cn.iceblue.core.domain.vo.MetaVo;
import cn.iceblue.core.domain.vo.RouterVo;
import cn.iceblue.core.domain.vo.UserInfoVo;
import cn.iceblue.core.pojo.entity.SysMenuEntity;
import cn.iceblue.core.pojo.entity.SysRoleEntity;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.core.util.DateUtils;
import cn.iceblue.core.util.text.Convert;
import cn.iceblue.data.dao.SysUserDao;
import cn.iceblue.data.dao.SysUserRoleDao;
import cn.iceblue.data.service.SysConfigService;
import cn.iceblue.data.service.SysMenuService;
import cn.iceblue.data.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 分页查询
     *
     * @param sysUser 筛选条件
     * @param current 当前页码
     * @param size    每页大小
     * @return
     */
    @Override
    public Page<SysUserEntity> pagingQuery(SysUserEntity sysUser, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(sysUser.getId())) {
            queryWrapper.eq(SysUserEntity::getId, sysUser.getId());
        }
        if (StringUtils.isNotBlank(sysUser.getUserCode())) {
            queryWrapper.eq(SysUserEntity::getUserCode, sysUser.getUserCode());
        }
        if (StringUtils.isNotBlank(sysUser.getUserName())) {
            queryWrapper.eq(SysUserEntity::getUserName, sysUser.getUserName());
        }
        if (StringUtils.isNotBlank(sysUser.getUserPassword())) {
            queryWrapper.eq(SysUserEntity::getUserPassword, sysUser.getUserPassword());
        }
        if (!Objects.isNull(sysUser.getReadOnly())) {
            queryWrapper.eq(SysUserEntity::getReadOnly, sysUser.getReadOnly());
        }
        if (StringUtils.isNotBlank(sysUser.getEmail())) {
            queryWrapper.eq(SysUserEntity::getEmail, sysUser.getEmail());
        }
        if (StringUtils.isNotBlank(sysUser.getPhone())) {
            queryWrapper.eq(SysUserEntity::getPhone, sysUser.getPhone());
        }
        if (StringUtils.isNotBlank(sysUser.getAvatar())) {
            queryWrapper.eq(SysUserEntity::getAvatar, sysUser.getAvatar());
        }
        if (!Objects.isNull(sysUser.getStatus())) {
            queryWrapper.eq(SysUserEntity::getStatus, sysUser.getStatus());
        }
        if (!Objects.isNull(sysUser.getLastLoginIp())) {
            queryWrapper.eq(SysUserEntity::getLastLoginIp, sysUser.getLastLoginIp());
        }
        if (StringUtils.isNotBlank(sysUser.getRemark())) {
            queryWrapper.eq(SysUserEntity::getRemark, sysUser.getRemark());
        }
        if (StringUtils.isNotBlank(sysUser.getTenantId())) {
            queryWrapper.eq(SysUserEntity::getTenantId, sysUser.getTenantId());
        }
        if (!Objects.isNull(sysUser.getRevision())) {
            queryWrapper.eq(SysUserEntity::getRevision, sysUser.getRevision());
        }
        if (StringUtils.isNotBlank(sysUser.getCreatedBy())) {
            queryWrapper.eq(SysUserEntity::getCreatedBy, sysUser.getCreatedBy());
        }
        if (!Objects.isNull(sysUser.getCreatedTime())) {
            queryWrapper.eq(SysUserEntity::getCreatedTime, sysUser.getCreatedTime());
        }
        if (StringUtils.isNotBlank(sysUser.getUpdatedBy())) {
            queryWrapper.eq(SysUserEntity::getUpdatedBy, sysUser.getUpdatedBy());
        }
        if (!Objects.isNull(sysUser.getUpdatedTime())) {
            queryWrapper.eq(SysUserEntity::getUpdatedTime, sysUser.getUpdatedTime());
        }

        //2. 执行分页查询
        Page<SysUserEntity> pagin = new Page<>(current, size, true);
        IPage<SysUserEntity> selectResult = this.baseMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }


    /**
     * 获取用户信息
     *
     * @param userId :
     * @return UserInfoVo
     * @author IceBlue
     * @date 2025/6/18 下午3:04
     **/
    @SneakyThrows
    @Override
    public UserInfoVo info(String userId) {
        UserInfoVo vo = new UserInfoVo();
        //用户信息
        CompletableFuture<SysUserEntity> userFuture = CompletableFuture.supplyAsync(() -> {

            SysUserEntity user = getById(userId);
            vo.setAvatar(user.getAvatar());
            vo.setName(user.getUserName());
            vo.setIntroduction(user.getRemark());
            return user;
        }, threadPoolTaskExecutor);

        //角色信息
        CompletableFuture<Void> roleFuture = userFuture.thenAcceptAsync((user) -> {
            List<SysRoleEntity> sysRoleEntities = sysUserRoleDao.selectRoleByUserId(userId);
            List<String> roleNameList = sysRoleEntities.stream()
                    .map(SysRoleEntity::getRoleName)
                    .collect(Collectors.toList());
            vo.setRoles(roleNameList);
        }, threadPoolTaskExecutor);

        // 权限
        CompletableFuture<Void> permissionsFuture = userFuture.thenAcceptAsync(user -> {
            Set<String> menuPermsByUserId = sysMenuService.getMenuPermsByUserId(userId);
            vo.setPermissions(menuPermsByUserId);
        }, threadPoolTaskExecutor);

        // 初始化密码提示
        CompletableFuture<Void> defaultModifyPwdFuture = userFuture.thenAcceptAsync(user -> {
            Integer initPasswordModify = Convert.toInt(sysConfigService.selectConfigByKey("sys.account.initPasswordModify"));
            boolean b = initPasswordModify != null && BooleanDict.TRUE.equals(initPasswordModify) && user.getPwdUpdateDate() == null;
            vo.setIsDefaultModifyPwd(b);
        }, threadPoolTaskExecutor);

        //密码过期提示
        CompletableFuture<Void> passwordValidateDayFuture = userFuture.thenAcceptAsync(user -> {
            Integer passwordValidateDays = Convert.toInt(sysConfigService.selectConfigByKey("sys.account.passwordValidateDays"));
            if (passwordValidateDays != null && passwordValidateDays > 0) {
                if (Objects.isNull(user.getPwdUpdateDate())) {
                    // 如果从未修改过初始密码，直接提醒过期
                    vo.setIsPasswordExpired(Boolean.TRUE);
                    return;
                }
                boolean b = DateUtils.differentDaysByMillisecond(new Date(), user.getPwdUpdateDate()) > passwordValidateDays;
                vo.setIsPasswordExpired(b);
                return;
            }
            vo.setIsPasswordExpired(Boolean.FALSE);
        }, threadPoolTaskExecutor);


        CompletableFuture.allOf(roleFuture, permissionsFuture, defaultModifyPwdFuture, passwordValidateDayFuture)
                .get();

        return vo;
    }

    /**
     * 获取用户菜单,并组装成Routers
     *
     * @param userId :
     * @return List<SysMenuEntity>
     * @author IceBlue
     * @date 2025/6/23 下午2:07
     **/
    @Override
    public List<RouterVo> userMenu(String userId) {
        List<SysMenuEntity> SysMenuList = sysMenuService.getSysMenuByUserId(userId);
        List<SysMenuDto> sysMenuDtoList = structureArrange(SysMenuList);
        return buildMenus(sysMenuDtoList);
    }

    /**
     * 整理菜单的父子结构
     *
     * @param sysMenuList:
     * @return List<SysMenuDto>
     * @author IceBlue
     * @date 2025/7/10 下午1:57
     **/
    private List<SysMenuDto> structureArrange(List<SysMenuEntity> sysMenuList) {
        Map<String, SysMenuDto> tileMenuList = sysMenuList.stream().collect(Collectors.toMap(SysMenuEntity::getId, (item) -> {
            SysMenuDto sysMenuDto = new SysMenuDto();
            BeanUtils.copyProperties(item, sysMenuDto);
            return sysMenuDto;
        }));

        sysMenuList.forEach(item -> {
            if (StringUtils.isNotBlank(item.getParentId())) {
                SysMenuDto parentMenuDto = tileMenuList.get(item.getParentId());
                SysMenuDto sysMenuDto = tileMenuList.get(item.getId());
                if (parentMenuDto != null && sysMenuDto != null) {
                    parentMenuDto.getChildren().add(sysMenuDto);
                }
            }
        });

        return sysMenuList
                .stream()
                .filter(item -> StringUtils.isBlank(item.getParentId()))
                .map(item -> tileMenuList.get(item.getId()))
                .collect(Collectors.toList());
    }


    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenuDto> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenuDto menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(BooleanDict.FALSE.equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getMenuTitle(), menu.getMenuIcon(), BooleanDict.TRUE.equals(menu.getIsCache()), menu.getMenuPath()));
            List<SysMenuDto> cMenus = menu.getChildren();
            if (!CollectionUtils.isEmpty(cMenus) && MenuTypeDict.CATALOGUE.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getMenuPath());
                children.setComponent(menu.getComponent());
                children.setName(getRouteName(menu.getRouteName(), menu.getMenuPath()));
                children.setMeta(new MetaVo(menu.getMenuTitle(), menu.getMenuIcon(), BooleanDict.TRUE.equals(menu.getIsCache()), menu.getMenuPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (StringUtils.isNotBlank(menu.getParentId()) && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getMenuTitle(), menu.getMenuIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getMenuPath());
                children.setPath(routerPath);
                children.setComponent(BaseConstants.INNER_LINK);
                children.setName(getRouteName(menu.getRouteName(), routerPath));
                children.setMeta(new MetaVo(menu.getMenuTitle(), menu.getMenuIcon(), menu.getMenuPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenuEntity menu) {
        String routerPath = menu.getMenuPath();
        // 内链打开外网方式
        if (StringUtils.isNotBlank(menu.getParentId()) && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (StringUtils.isNotBlank(menu.getParentId()) && MenuTypeDict.CATALOGUE.equals(menu.getMenuType())
                && BooleanDict.FALSE.equals(menu.getFrameFlag())) {
            routerPath = "/" + menu.getMenuPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return 替换后的内链域名
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{BaseConstants.HTTP, BaseConstants.HTTPS, BaseConstants.WWW, ".", ":"},
                new String[]{"", "", "", "/", "/"});
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenuEntity menu) {
        return BooleanDict.FALSE.getCode().equals(menu.getFrameFlag()) && StringUtils.startsWithAny(menu.getMenuPath(), BaseConstants.HTTP, BaseConstants.HTTPS);
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenuEntity menu) {
        String component = FrontConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && StringUtils.isNotBlank(menu.getParentId()) && isInnerLink(menu)) {
            component = FrontConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = FrontConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenuEntity menu) {
        return StringUtils.isNotBlank(menu.getParentId()) && MenuTypeDict.CATALOGUE.equals(menu.getMenuType());
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean checkMenuNameUnique(SysMenuEntity menu) {
       /* String menuId = menu.getId();
        SysMenuEntity info = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;*/

        return true;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenuEntity menu) {
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            return StringUtils.EMPTY;
        }
        return getRouteName(menu.getRouteName(), menu.getMenuPath());
    }

    /**
     * 获取路由名称，如没有配置路由名称则取路由地址
     *
     * @param name 路由名称
     * @param path 路由地址
     * @return 路由名称（驼峰格式）
     */
    public String getRouteName(String name, String path) {
        String routerName = StringUtils.isNotEmpty(name) ? name : path;
        return StringUtils.capitalize(routerName);
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenuEntity menu) {
        return StringUtils.isBlank(menu.getParentId()) && MenuTypeDict.MENU.getCode().equals(menu.getMenuType())
                && BooleanDict.TRUE.getCode().equals(menu.getFrameFlag());
    }
}