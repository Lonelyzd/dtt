package cn.iceblue.core.domain.vo;

import cn.iceblue.core.domain.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 返回处理消息
     */
    private String msg;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回数据对象 data
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();


    public R() {
        this(ResponseStatus.OK.getCode(), null);
    }

    public R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }



    public static <T> R<T> ok(T data) {
        R<T> r = new R<T>();
        r.data = data;
        return r;
    }

    public static <T> R<T> ok() {
        return new R<T>();
    }

    public static <T> R<T> error() {
        return error(ResponseStatus.FAILED.getCode(), ResponseStatus.FAILED.getLocalizedMessage());
    }

    public static <T> R<T> error(String msg) {
        return error(ResponseStatus.FAILED.getCode(), msg);
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<T>();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static <T> R<T> error(ResponseStatus status) {
        R<T> r = new R<T>();
        r.code = status.getCode();
        r.msg = status.getMessage();
        return r;
    }

    public static <T> R<T> notFound() {
        return notFound(ResponseStatus.NOT_FOUND.getLocalizedMessage());
    }

    public static <T> R<T> notFound(String msg) {
        return error(ResponseStatus.NOT_FOUND.getCode(), msg);
    }





    public static <T> R<T> unAuth(String msg) {
        return error(ResponseStatus.UNAUTHORIZED.getCode(), msg);
    }

}