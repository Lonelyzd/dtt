package cn.iceblue.api.controller.common;

import cn.iceblue.core.config.DttBaseConfig;
import cn.iceblue.core.domain.constant.BaseConstants;
import cn.iceblue.core.domain.constant.CacheConstants;
import cn.iceblue.core.domain.vo.CaptchaVo;
import cn.iceblue.core.domain.vo.R;
import cn.iceblue.core.redis.RedisCache;
import cn.iceblue.core.util.uuid.IdUtils;
import cn.iceblue.data.service.SysConfigService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author IceBlue
 */
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public R<CaptchaVo> getCode() throws IOException {
        CaptchaVo vo = new CaptchaVo();
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        vo.setCaptchaEnabled(captchaEnabled);
        if (!captchaEnabled) {
            return R.ok(vo);
        }

        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = DttBaseConfig.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, BaseConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);


        vo.setUuid(uuid);
        vo.setImg(Base64.getEncoder().encodeToString(os.toByteArray()));
        return R.ok(vo);
    }
}
