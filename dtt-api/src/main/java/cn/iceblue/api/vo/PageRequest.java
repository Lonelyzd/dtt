package cn.iceblue.api.vo;

import lombok.Data;

/**
 * 公共查询参数
 *
 * @author : IceBlue
 * @date : 2023/6/30 13:20
 **/
@Data
public class PageRequest {

	private Integer page = 1;

	private Integer pageSize = 10;

	/**
	 * 检索关键字
	 **/
	private String keyWord;
}
