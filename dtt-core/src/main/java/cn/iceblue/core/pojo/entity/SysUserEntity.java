package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@TableName("dtt_sys_user")
@ApiModel(value = "系统用户表",description = "")
public class SysUserEntity extends  BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;


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
	 * 密码最后更新时间
	 */
	@ApiModelProperty(name = "pwdUpdateDate",notes = "密码最后更新时间")
	private Date pwdUpdateDate;

	/**
	 * 备注
	 */
	@ApiModelProperty(name = "remark",notes = "备注")
	private String remark;

}
