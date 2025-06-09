package cn.iceblue.config;

import cn.iceblue.api.vo.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * 全局统一异常处理
 */
@RestControllerAdvice
public class ExceptionConfiguration {
    @ExceptionHandler(ValidationException.class)
    public R<?> onValidationException(ValidationException e) {
        return R.error(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return R.error(e.getLocalizedMessage());
    }
}