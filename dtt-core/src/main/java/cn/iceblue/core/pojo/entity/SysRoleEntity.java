package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统角色表
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dtt_sys_role")
@ApiModel(value = "系统角色表",description = "")
public class SysRoleEntity extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@ApiModelProperty(name = "roleName",notes = "角色名称")
	private String roleName;
	/**
	 * 角色描述
	 */
	@ApiModelProperty(name = "roleDesc",notes = "角色描述")
	private String roleDesc;
	/**
	 * 角色状态
	 */
	@ApiModelProperty(name = "status",notes = "角色状态")
	private Integer status;

}
