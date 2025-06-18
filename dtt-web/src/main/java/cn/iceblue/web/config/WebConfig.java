package cn.iceblue.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 对所有路径开放跨域访问
               .allowedOrigins("*")  // 允许的源
               .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的方法
               .allowedHeaders("*")  // 允许的头信息，可以使用 "*" 代表所有头信息
//               .allowCredentials(true)  // 是否允许证书，例如cookies等凭证信息
               .maxAge(3600);           // 预检请求的缓存时间（秒）
    }
}