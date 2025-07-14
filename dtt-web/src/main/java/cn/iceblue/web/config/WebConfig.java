package cn.iceblue.web.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 统一加密器
     *
     * @return TextEncryptor
     * @author IceBlue
     * @date 2025/6/12 下午5:05
     **/
    @Bean
    public TextEncryptor textEncryptor(@Value("${jasypt.encryptor.password}") String salt) {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor;
    }
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 对所有路径开放跨域访问
               .allowedOrigins("*")  // 允许的源
               .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的方法
               .allowedHeaders("*")  // 允许的头信息，可以使用 "*" 代表所有头信息
//               .allowCredentials(true)  // 是否允许证书，例如cookies等凭证信息
               .maxAge(3600);           // 预检请求的缓存时间（秒）
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/sys/user/login",
                        "/api/user/change-password",
                        "/captchaImage",
                        "/sys/user/logout"

                );
    }

    /**
     * 静态资源访问规则
     *
     * @param registry:
     * @return void
     * @author IceBlue
     * @date 2023/5/30 13:58
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
//			.addResourceLocations("classpath:/META-INF/resources/")
//			.addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/")
//			.addResourceLocations("classpath:/public/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/ui/")
        ;

    }

    /**
     * 首页访问控制
     *
     * @param registry:
     * @return void
     * @author IceBlue
     * @date 2023/5/30 15:11
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}