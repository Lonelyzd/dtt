package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单表
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@TableName("dtt_sys_menu")
@ApiModel(value = "系统菜单表",description = "")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId
	@ApiModelProperty(name = "id",notes = "菜单ID")
	private String id;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(name = "menuName",notes = "菜单名称")
	private String menuName;
	/**
	 * 上级菜单id
	 */
	@ApiModelProperty(name = "parentId",notes = "上级菜单id")
	private String parentId;
	/**
	 * 菜单排序
	 */
	@ApiModelProperty(name = "orderNum",notes = "菜单排序")
	private Integer orderNum;
	/**
	 * 菜单路径
	 */
	@ApiModelProperty(name = "menuPath",notes = "菜单路径")
	private String menuPath;
	/**
	 * 1是 0否
	 */
	@ApiModelProperty(name = "frameFlag",notes = "1是 0否")
	private Integer frameFlag;
	/**
	 * 是否缓存
	 */
	@ApiModelProperty(name = "isCache",notes = "是否缓存")
	private Integer isCache;
	/**
	 * 菜单类型
	 */
	@ApiModelProperty(name = "menuType",notes = "菜单类型")
	private Integer menuType;
	/**
	 * 菜单状态
	 */
	@ApiModelProperty(name = "status",notes = "菜单状态")
	private Integer status;
	/**
	 * 权限标识
	 */
	@ApiModelProperty(name = "perms",notes = "权限标识")
	private String perms;
	/**
	 * 菜单图标
	 */
	@ApiModelProperty(name = "menuIcon",notes = "菜单图标")
	private String menuIcon;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark",notes = "备注")
	private String remark;
	/**
	 * 是否只读
	 */
	@ApiModelProperty(name = "readOnly",notes = "是否只读")
	private Integer readOnly;
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
