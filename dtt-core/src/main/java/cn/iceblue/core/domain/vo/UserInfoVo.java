package cn.iceblue.core.domain.vo;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户信息,包括用户角色,菜单
 *
 * @author : IceBlue
 * @date : 2025/6/18 下午2:52
 **/
@Data
public class UserInfoVo {

    @ApiModelProperty(name = "roles", notes = "角色列表")
    private List<String> roles;

    @ApiModelProperty(name = "name", notes = "用户名")
    private String name;

    @ApiModelProperty(name = "avatar", notes = "用户头像地址")
    private String avatar;

    @ApiModelProperty(name = "introduction", notes = "用户描述")
    private String introduction;

    @ApiModelProperty(name = "menus", notes = "用户菜单信息")
    private List<SysMenuEntity> menus;
}
