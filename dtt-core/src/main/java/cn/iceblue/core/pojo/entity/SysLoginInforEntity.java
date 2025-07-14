package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统访问记录
 *
 * @author IceBlue
 * @email
 * @date 2025-06-30 10:25:59
 */
@Data
@TableName("dtt_sys_login_infor")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统访问记录", description = "")
public class SysLoginInforEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @ApiModelProperty(name = "userCode", notes = "用户ID")
    private String userId;


    /**
     * 登录IP地址
     */
    @ApiModelProperty(name = "ipaddr", notes = "登录IP地址")
    private String ipaddr;


    /**
     * 登录地点
     */
    @ApiModelProperty(name = "loginLocation", notes = "登录地点")
    private String loginLocation;


    /**
     * 浏览器类型
     */
    @ApiModelProperty(name = "browser", notes = "浏览器类型")
    private String browser;


    /**
     * 操作系统
     */
    @ApiModelProperty(name = "os", notes = "操作系统")
    private String os;


    /**
     * 登录状态
     */
    @ApiModelProperty(name = "status", notes = "登录状态")
    private Integer status;


    /**
     * 提示消息
     */
    @ApiModelProperty(name = "msg", notes = "提示消息")
    private String msg;


    /**
     * 访问时间
     */
    @ApiModelProperty(name = "loginTime", notes = "访问时间")
    private Date loginTime;


}
