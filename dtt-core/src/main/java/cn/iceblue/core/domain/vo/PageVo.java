package cn.iceblue.core.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "分页包装类", description = "")
public class PageVo<T> implements Serializable {
    /**
     * 总条数
     **/
    @ApiModelProperty(name = "counts", notes = "记录总条数")
    private long counts;
    /**
     * 每页个数
     **/
    @ApiModelProperty(name = "pageSize", notes = "每页记录数")
    private long pageSize;
    /**
     * 总页数
     **/
    @ApiModelProperty(name = "pages", notes = "总页数")
    private long pages;
    /**
     * 当前页
     **/
    @ApiModelProperty(name = "current", notes = "当前页码")
    private long current;

    /**
     * 数据记录
     **/
    @ApiModelProperty(name = "items", notes = "分页数据")
    private List<T> items;

    /**
     * @param current:当前页号
     * @param counts:总记录量
     * @param size:分页大小
     * @param items:       数据
     * @return null
     * @author IceBlue
     * @date 2025/6/11 下午2:16
     **/
    public PageVo(long current, long size, long counts, List<T> items) {
        this.counts = counts;
        this.current = current;
        this.items = items;
    }
}