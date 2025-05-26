package cn.iceblue.api.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("DTT_DATASOURCE")
public class DttDatasource implements Serializable {
    /** 唯一ID */
    @TableId
    private Integer id ;
    /** 数据源名称 */
    private String name ;
    /** 数据源类型 */
    private String type ;
    /** 数据源IP地址 */
    private String ip ;
    /** 数据源端口 */
    private Integer port ;
    /** 数据库名称 */
    private String databaseName ;
    /** 用户名 */
    private String userName ;
    /** 密码;加密保存 */
    private String password ;
    /** 拓展属性 */
    private String expand ;
    /** 备注 */
    private String notes ;
    /** 创建人 */
    private Integer createdBy ;
    /** 创建时间 */
    private Date createdTime ;
    /** 更新人 */
    private Integer updatedBy ;
    /** 更新时间 */
    private Date updatedTime ;

}