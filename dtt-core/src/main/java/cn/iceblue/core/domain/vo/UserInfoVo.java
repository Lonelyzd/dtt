package cn.iceblue.core.domain.vo;

import cn.iceblue.core.pojo.entity.SysMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 用户信息,包括用户角色,菜单
 *
 * @author : IceBlue
 * @date : 2025/6/18 下午2:52
 **/
@Data
public class UserInfoVo {

    @ApiModelProperty(name = "id", notes = "用户ID")
    private String userId;

    @ApiModelProperty(name = "name", notes = "用户名")
    private String name;

    @ApiModelProperty(name = "avatar", notes = "用户头像地址")
    private String avatar;

    @ApiModelProperty(name = "roles", notes = "用户角色集合")
    private List<String> roles;

    @ApiModelProperty(name = "introduction", notes = "用户描述")
    private String introduction;

    @ApiModelProperty(name = "menus", notes = "用户菜单信息")
    private List<SysMenuEntity> menus;

    @ApiModelProperty(name = "permissions", notes = "用户角色权限")
    private Set<String> permissions;

    @ApiModelProperty(name = "isDefaultModifyPwd", notes = "是否提示初始密码")
    private Boolean isDefaultModifyPwd;

    @ApiModelProperty(name = "isDefaultModifyPwd", notes = "是否提示密码过期")
    private Boolean isPasswordExpired;

}
