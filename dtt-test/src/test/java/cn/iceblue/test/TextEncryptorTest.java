package cn.iceblue.test;

import org.jasypt.util.text.TextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : IceBlue
 * @date : 2025/6/16 下午2:58
 **/
@SpringBootTest
public class TextEncryptorTest {

    @Autowired
    TextEncryptor textEncryptor;

    @Test
    public void testEncrypt() {
        String encryptText = textEncryptor.encrypt("123456");
        System.out.println(encryptText);
    }
}
