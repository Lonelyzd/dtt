package cn.iceblue.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : IceBlue
 * @date : 2025/6/18 下午4:27
 **/
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String CREATE_BY = "createdBy";
    public static final String UPDATE_BY = "updatedBy";
    public static final String CREATE_TIME = "createdTime";
    public static final String UPDATE_TIME = "updatedTime";
    public static final String TENANT_ID = "tenantId";

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(name = "id",notes = "主键")
    private String id;

    /**
     * 租户号
     */
    @ApiModelProperty(name = "tenant_id", notes = "租户号")
    @TableField(value = "tenant_id", fill = FieldFill.INSERT)
    private String tenantId;
    /**
     * 乐观锁
     */
    @ApiModelProperty(name = "revision", notes = "乐观锁")
    private Integer revision;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "create_by", notes = "创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createdTime", notes = "创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "updatedBy", notes = "更新人")
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private String updatedBy;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updatedTime", notes = "更新时间")
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
}
