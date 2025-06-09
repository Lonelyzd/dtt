package cn.iceblue.config;

import cn.iceblue.api.vo.R;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 接口返回统一处理
 */
@RestControllerAdvice
public class CustumerResponseBodyAdvice implements ResponseBodyAdvice<Object> {
  
	@Override  
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class)
	|| returnType.hasMethodAnnotation(ResponseBody.class))  
	&& !R.class.isAssignableFrom(returnType.getParameterType());
	}  
	  
	@Override  
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
								  Class<? extends HttpMessageConverter<?>> selectedConverterType,
								  ServerHttpRequest request, ServerHttpResponse response) {
		if (Objects.isNull(body)) {
			return R.ok();
		}  
		if (body instanceof String) {  
			return R.ok(body);
		}  
		if (body instanceof R) {
			return body;  
		}  
		return R.ok(body);
	}  
}