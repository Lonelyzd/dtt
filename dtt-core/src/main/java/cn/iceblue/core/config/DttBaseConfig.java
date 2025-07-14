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

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 上传路径
     */
    private static String uploadPath;

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        DttBaseConfig.addressEnabled = addressEnabled;
    }


    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        DttBaseConfig.captchaType = captchaType;
    }

    public static String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        DttBaseConfig.uploadPath = uploadPath;
    }

}
