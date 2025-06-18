package cn.iceblue.core.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class PageVO<T> implements Serializable {
    /**
     * 总个数
     **/
    private long counts;
    /**
     * 每页个数
     **/
    private long pageSize;
    /**
     * 总页数
     **/
    private long pages;
    /**
     * 当前页
     **/
    private long current;

    /**
     * 数据记录
     **/
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
    public PageVO(long current, long size, long counts, List<T> items) {
        this.counts = counts;
        this.current = current;
        this.items = items;
    }
}