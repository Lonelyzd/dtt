package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 参数配置表
 *
 * @author IceBlue
 * @email
 * @date 2025-06-26 16:56:50
 */
@Data
@TableName("dtt_sys_config")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "参数配置表", description = "")
public class SysConfigEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 参数名称
     */
    @ApiModelProperty(name = "configName", notes = "参数名称")
    private String configName;


    /**
     * 参数键名
     */
    @ApiModelProperty(name = "configKey", notes = "参数键名")
    private String configKey;


    /**
     * 参数键值
     */
    @ApiModelProperty(name = "configValue", notes = "参数键值")
    private String configValue;


    /**
     * 是否只读
     */
    @ApiModelProperty(name = "readOnly", notes = "是否只读")
    private Integer readOnly;


}
