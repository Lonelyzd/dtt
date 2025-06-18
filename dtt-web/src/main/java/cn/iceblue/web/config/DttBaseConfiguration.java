package cn.iceblue.web.config;

import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : IceBlue
 * @date : 2025/6/12 下午5:05
 **/
@Configuration
public class DttBaseConfiguration {

    @Value("${jasypt.encryptor.password}")
    private String salt;

    /**
     * 统一加密器
     *
     * @return TextEncryptor
     * @author IceBlue
     * @date 2025/6/12 下午5:05
     **/
    @Bean
    public TextEncryptor textEncryptor() {
        StrongTextEncryptor encryptor = new StrongTextEncryptor();
        encryptor.setPassword(salt);
        return encryptor;
    }
}
