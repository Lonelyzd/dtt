package cn.iceblue.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author : IceBlue
 * @date : 2025/6/12 下午5:05
 **/
@Component
@ConfigurationProperties(prefix = "dtt")
public class DttBaseConfig {


    /**
     * 验证码类型
     */
    private static String captchaType;


    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        DttBaseConfig.captchaType = captchaType;
    }
}
