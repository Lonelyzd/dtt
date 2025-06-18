package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色的菜单权限表
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dtt_sys_role_menu")
@ApiModel(value = "角色的菜单权限表",description = "")
public class SysRoleMenuEntity extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色id
	 */
	@ApiModelProperty(name = "roleId",notes = "角色id")
	private String roleId;
	/**
	 * 菜单id
	 */
	@ApiModelProperty(name = "menuId",notes = "菜单id")
	private String menuId;

}
