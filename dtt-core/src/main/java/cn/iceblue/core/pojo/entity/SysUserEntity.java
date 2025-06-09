package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 * 
 * @author IceBlue
 * @email 
 * @date 2025-06-04 14:36:05
 */
@Data
@TableName("dtt_sys_user")
@ApiModel(value = "系统用户表",description = "")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户唯一ID,不可变
	 */
	@TableId
	@ApiModelProperty(name = "id",notes = "用户唯一ID,不可变")
	private String id;
	/**
	 * 用户CODE,登录使用
	 */
	@ApiModelProperty(name = "userCode",notes = "用户CODE,登录使用")
	private String userCode;
	/**
	 * 用户显示名称
	 */
	@ApiModelProperty(name = "userName",notes = "用户显示名称")
	private String userName;
	/**
	 * 加密保存
	 */
	@ApiModelProperty(name = "userPassword",notes = "加密保存")
	private String userPassword;
	/**
	 * 是否只读
	 */
	@ApiModelProperty(name = "readOnly",notes = "是否只读")
	private Integer readOnly;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(name = "email",notes = "邮箱")
	private String email;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(name = "phone",notes = "手机号码")
	private String phone;
	/**
	 * 头像路径
	 */
	@ApiModelProperty(name = "avatar",notes = "头像路径")
	private String avatar;
	/**
	 * 账号状态
	 */
	@ApiModelProperty(name = "status",notes = "账号状态")
	private Integer status;
	/**
	 * 最后登录IP
	 */
	@ApiModelProperty(name = "lastLoginIp",notes = "最后登录IP")
	private Date lastLoginIp;
	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark",notes = "备注")
	private String remark;
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
