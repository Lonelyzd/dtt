package cn.iceblue.core.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统角色表
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@TableName("dtt_sys_role")
@ApiModel(value = "系统角色表",description = "")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@ApiModelProperty(name = "id",notes = "主键")
	private String id;
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
