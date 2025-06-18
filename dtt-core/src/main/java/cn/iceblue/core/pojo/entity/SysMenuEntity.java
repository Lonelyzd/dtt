package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 系统菜单表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-04 14:36:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dtt_sys_menu")
@ApiModel(value = "系统菜单表",description = "")
public class SysMenuEntity  extends  BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

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

}
