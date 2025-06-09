package cn.iceblue.api.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
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


	public PageVO(IPage<T> page){
		this.counts = page.getTotal();
		this.pageSize = page.getSize();
		this.pages = page.getPages();
		this.current = page.getCurrent();
		this.items = page.getRecords();
	}
}