package cn.iceblue.core.exception;


import cn.iceblue.core.domain.enums.ResponseStatus;
import cn.iceblue.core.util.StrUtil;

/**
 * TSS统一非运行时异常
 *
 * @author : IceBlue
 * @date : 2023/6/14 16:57
 **/
public  class DttException extends Exception {
	private final Integer code;
	private final String message;

	public DttException(Integer code, String msg, Object... params) {
		this(code, null, msg, params);
	}

	public DttException(String msg, Object... params) {
		this(ResponseStatus.FAILED.getCode(), msg, params);
	}

	public DttException(Throwable ex, String msg, Object... params) {
		this(500, ex, msg, params);
	}


	public DttException(int errCode, Throwable ex, String msg, Object... params) {
		super(ex);
		this.code = errCode;
		this.message = StrUtil.format(msg, params);
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public Integer getCode() {
		return code;
	}
}
