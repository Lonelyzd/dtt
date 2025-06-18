package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户拥有的角色
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dtt_sys_user_role")
@ApiModel(value = "用户拥有的角色",description = "")
public class SysUserRoleEntity extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 用户id
	 */
	@ApiModelProperty(name = "userId",notes = "用户id")
	private String userId;
	/**
	 * 角色id
	 */
	@ApiModelProperty(name = "roleId",notes = "角色id")
	private String roleId;

}
