package cn.iceblue.core.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户拥有的角色
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@TableName("dtt_sys_user_role")
@ApiModel(value = "用户拥有的角色",description = "")
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id",notes = "主键")
	private String id;
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
	/**
	 * 租户号
	 */
	@ApiModelProperty(name = "tenantId",notes = "租户号")
	private String tenantId;
	/**
	 * 乐观锁
	 */
	@ApiModelProperty(name = "revision",notes = "乐观锁")
	private Integer revision;
	/**
	 * 创建人
	 */
	@ApiModelProperty(name = "createdBy",notes = "创建人")
	private String createdBy;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(name = "createdTime",notes = "创建时间")
	private Date createdTime;
	/**
	 * 更新人
	 */
	@ApiModelProperty(name = "updatedBy",notes = "更新人")
	private String updatedBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(name = "updatedTime",notes = "更新时间")
	private Date updatedTime;

}
