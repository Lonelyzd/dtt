package cn.iceblue.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : IceBlue
 * @date : 2025/6/11 上午10:56
 **/
@Getter
@AllArgsConstructor
public enum ResponseStatus {
    OK(200,"成功"),
    FAILED(500,"系统异常"),
    BAD_REQUEST(400,"错误的请求"),
    NOT_FOUND(404,"请求资源不存在"),
    UNAUTHORIZED(401,"未授权的操作"),
    ;

    private final int code;

    private final String message;//可能需要支持国际化

    public String getLocalizedMessage(){
        // FIXME: 2025/4/14 增加国际化支持
        //如果 message 是占位符，翻译成当前 message text
        //否则直接返回 message
        return message;
    }
}
