package cn.iceblue.web.config;

import cn.dev33.satoken.exception.SaTokenException;
import cn.iceblue.core.domain.enums.ResponseStatus;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.exception.DttRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;

/**
 * 全局统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(value = Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R<?> handle(Exception e) {
        log.error("【系统异常】", e);
        return R.error(500, "未知异常");
    }

    @ExceptionHandler(ValidationException.class)
    public R<?> onValidationException(ValidationException e) {
        return R.error(e.getLocalizedMessage());
    }

    /**
     * sa-token校验异常
     *
     * @param e:
     * @param response:
     * @return R
     * @author IceBlue
     * @date 2023/7/26 16:42
     **/
    @ExceptionHandler(value = SaTokenException.class)
    public R<?> saTokenException(SaTokenException e, HttpServletResponse response) {
        // 根据不同异常细分状态码返回不同的提示
        if (e.getCode() == 11012) {
            response.setStatus(ResponseStatus.UNAUTHORIZED.getCode());
            return R.error(ResponseStatus.UNAUTHORIZED);
        }
        return R.error(e.getCode(), e.getMessage());
    }

    /**
     * 前端参数校验-POST请求
     *
     * @param ex:
     * @return R
     * @author IceBlue
     * @date 2023/7/26 16:43
     **/
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("POST参数校验失败-");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("; ");
        }
        String msg = sb.toString();
        return R.error(ResponseStatus.BAD_REQUEST.getCode(), msg);
    }

    /**
     * 前端参数校验-GET请求
     *
     * @param ex:
     * @return R
     * @author IceBlue
     * @date 2023/7/26 17:16
     **/
    @ResponseBody
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public static R notValidBindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("GET参数校验失败-");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append("; ");
        }
        String msg = sb.toString();
        return R.error(ResponseStatus.BAD_REQUEST.getCode(), msg);
    }

    /**
     * GET请求参数绑定异常
     *
     * @param ex:
     * @return R
     * @author IceBlue
     * @date 2024/5/24 11:05
     **/
    @ExceptionHandler({MissingRequestValueException.class})
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleMissingRequestValueException(MissingRequestValueException ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            String parameterName = ((MissingServletRequestParameterException) ex).getParameterName();
            String msg = "缺少接口必填参数:{" + parameterName + "}";
            return R.error(ResponseStatus.FAILED.getCode(), msg);
        }
        return R.error(ResponseStatus.BAD_REQUEST.getCode(), ex.getMessage());
    }

    /**
     * 违反约束异常
     *
     * @param ex:
     * @return R
     * @author IceBlue
     * @date 2023/7/26 16:43
     **/
    @ExceptionHandler({ConstraintViolationException.class})
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleConstraintViolationException(ConstraintViolationException ex) {
        return R.error(ResponseStatus.FAILED.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = DttRuntimeException.class)
    public R dttRuntimeException(DttRuntimeException e) {
        return R.error(e.getCode(), e.getMessage());
    }
}