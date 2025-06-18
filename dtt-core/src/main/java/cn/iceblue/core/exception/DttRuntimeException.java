package cn.iceblue.core.exception;

import cn.iceblue.core.domain.enums.ResponseTemplate;
import cn.iceblue.core.domain.enums.ResponseStatus;
import cn.iceblue.core.util.StrUtil;

/**
 * TSS统一运行时异常
 *
 * @author : IceBlue
 * @date : 2023/6/14 16:57
 **/
public class DttRuntimeException extends RuntimeException {
    private final Integer code;
    private final String message;


    public DttRuntimeException(Integer code, String msg, Object... params) {
        this(code, null, msg, params);
    }

    public DttRuntimeException(Throwable ex, Integer code, String msg, Object... params) {
        this(code, ex, msg, params);
    }

    public DttRuntimeException(String msg, Object... params) {
        this(ResponseStatus.FAILED.getCode(), msg, params);
    }

    public DttRuntimeException(Throwable ex, String msg, Object... params) {
        this(ResponseStatus.FAILED.getCode(), ex, msg, params);
    }

    public DttRuntimeException(Throwable ex) {
        this(ResponseStatus.FAILED.getCode(), ex);
    }

    public DttRuntimeException(int errCode, Throwable ex) {
        this(errCode, ex, ex.getMessage(), (Object) null);
    }


    public DttRuntimeException(int errCode, Throwable ex, String msg, Object... params) {
        super(ex);
        this.code = errCode;
        this.message = StrUtil.format(msg, params);
    }

    public DttRuntimeException(Throwable ex, ResponseTemplate tssResponseMsg, Object... params) {
        this(ex, tssResponseMsg.getCode().getCode(), tssResponseMsg.getText(), params);
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }
}
