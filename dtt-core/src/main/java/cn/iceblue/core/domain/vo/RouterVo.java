package cn.iceblue.core.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 路由配置信息
 *
 * @author IceBlue
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(value = "前端路由实体类", description = "")
public class RouterVo {
    /**
     * 路由名字
     */
    @ApiModelProperty(name = "name", notes = "路由名称")
    private String name;

    /**
     * 路由地址
     */
    @ApiModelProperty(name = "path", notes = "路由地址")
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    @ApiModelProperty(name = "redirect", notes = "重定向地址")
    private String redirect;

    /**
     * 组件地址
     */
    @ApiModelProperty(name = "component", notes = "组件地址")
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "ry"}
     */
    @ApiModelProperty(name = "query", notes = "路由参数")
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    @ApiModelProperty(name = "alwaysShow", notes = "是否自动嵌套")
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    @ApiModelProperty(name = "meta", notes = "其他元素")
    private MetaVo meta;

    /**
     * 子路由
     */
    @ApiModelProperty(name = "children", notes = "子路由集合")
    private List<RouterVo> children;

}
