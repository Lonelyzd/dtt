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
    LOGIN_USER_LOCKED(ResponseStatus.FAILED, "用户已锁定"),
    LOGIN_FAIL(ResponseStatus.FAILED, "登录失败,用户名或密码错误"),
    LOGIN_CAPTCHA_EXPIRE(ResponseStatus.FAILED, "验证码已失效"),
    LOGIN_CAPTCHA_EXCEPTION(ResponseStatus.FAILED, "验证码错误"),
    DECODE_ERROR(ResponseStatus.FAILED, "解码异常"),
    IP_BLACK(ResponseStatus.UNAUTHORIZED, "IP被锁定");

    /**
     * 模版代码
     **/
    private final ResponseStatus code;

    /**
     * 响应消息模版内容
     **/
    private final String text;
}
