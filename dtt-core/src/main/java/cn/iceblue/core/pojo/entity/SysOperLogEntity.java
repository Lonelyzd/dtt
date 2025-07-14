package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 操作日志记录
 *
 * @author IceBlue
 * @email
 * @date 2025-07-02 15:27:29
 */
@Data
@TableName("sys_oper_log")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "操作日志记录", description = "")
public class SysOperLogEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 模块标题
     */
    @ApiModelProperty(name = "title", notes = "模块标题")
    private String title;


    /**
     * 业务类型
     */
    @ApiModelProperty(name = "businessType", notes = "业务类型")
    private Integer businessType;


    /**
     * 方法名称
     */
    @ApiModelProperty(name = "method", notes = "方法名称")
    private String method;


    /**
     * 请求方式
     */
    @ApiModelProperty(name = "requestMethod", notes = "请求方式")
    private String requestMethod;


    /**
     * 操作类别
     */
    @ApiModelProperty(name = "operatorType", notes = "操作类别")
    private String operatorType;


    /**
     * 操作人员
     */
    @ApiModelProperty(name = "operUserId", notes = "操作人员")
    private String operUserId;


    /**
     * 请求URL
     */
    @ApiModelProperty(name = "operUrl", notes = "请求URL")
    private String operUrl;


    /**
     * 主机地址
     */
    @ApiModelProperty(name = "operIp", notes = "主机地址")
    private String operIp;


    /**
     * 操作地点
     */
    @ApiModelProperty(name = "operLocation", notes = "操作地点")
    private String operLocation;


    /**
     * 请求参数
     */
    @ApiModelProperty(name = "operParam", notes = "请求参数")
    private String operParam;


    /**
     * 返回参数
     */
    @ApiModelProperty(name = "jsonResult", notes = "返回参数")
    private String jsonResult;


    /**
     * 操作状态
     */
    @ApiModelProperty(name = "status", notes = "操作状态")
    private Integer status;


    /**
     * 错误消息
     */
    @ApiModelProperty(name = "errorMsg", notes = "错误消息")
    private String errorMsg;


    /**
     * 操作时间
     */
    @ApiModelProperty(name = "operTime", notes = "操作时间")
    private String operTime;


    /**
     * 消耗时间
     */
    @ApiModelProperty(name = "costTime", notes = "消耗时间")
    private Integer costTime;


}
