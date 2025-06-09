package cn.iceblue.api.vo;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 返回处理消息
	 */
	private String message ;

	/**
	 * 返回代码
	 */
	private Integer code ;

	/**
	 * 返回数据对象 data
	 */
	private T data;

	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();


	public R() {
		this(HttpStatus.OK.value(), null);
	}

	public R(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	public static <T> R<T> error() {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
	}

	public static <T> R<T> error(String msg) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
	}

	public static <T> R<T> error(int code, String msg) {
		R<T> r = new R<T>();
		r.code = code;
		r.message = msg;
		return r;
	}
	public static <T> R<T> error(HttpStatus status, String msg) {
		R<T> r = new R<T>();
		r.code = status.value();
		r.message = msg;
		return r;
	}

	public static <T> R<T> notFound() {
		return error(HttpStatus.NOT_FOUND.value(), "所请求内容不存在");
	}

	public static <T> R<T> notFound(String msg) {
		return error(HttpStatus.NOT_FOUND.value(), msg);
	}


	public static <T> R<T> ok(String msg) {
		R<T> r = new R<T>();
		r.message = msg;
		return r;
	}

	public static <T> R<T> ok(T data) {
		R<T> r = new R<T>();
		r.data = data;
		return r;
	}

	public static <T> R<T> ok() {
		return new R<T>();
	}

	public static <T> R<T> ok(String msg, T data) {
		R<T> r = new R<T>();
		r.setMessage(msg);
		r.setData(data);
		return r;
	}

	public static <T> R<T> unAuth(String msg) {
		return error(HttpStatus.UNAUTHORIZED.value(), msg);
	}

}