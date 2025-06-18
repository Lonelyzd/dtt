package cn.iceblue.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : IceBlue
 * @date : 2025/6/12 下午4:30
 **/
@Getter
@AllArgsConstructor
public enum ResponseTemplate {

    USER_NON_EXISTENT(ResponseStatus.NOT_FOUND, "登录用户不存在"),
    USER_LOCKED(ResponseStatus.FAILED, "用户已锁定"),
    LOGIN_FAIL(ResponseStatus.FAILED, "登录失败,用户名或密码错误"),
    DECODE_ERROR(ResponseStatus.FAILED, "解码异常"),
    ;

    /**
     * 模版代码
     **/
    private final ResponseStatus code;

    /**
     * 响应消息模版内容
     **/
    private final String text;
}
