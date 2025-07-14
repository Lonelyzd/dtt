package cn.iceblue.framework.web.service;

import cn.iceblue.core.domain.constant.BaseConstants;
import cn.iceblue.core.domain.constant.CacheConstants;
import cn.iceblue.core.domain.enums.ResponseTemplate;
import cn.iceblue.core.domain.enums.dict.RecordStatusDict;
import cn.iceblue.core.exception.DttRuntimeException;
import cn.iceblue.core.pojo.entity.SysUserEntity;
import cn.iceblue.core.redis.RedisCache;
import cn.iceblue.core.util.Assert;
import cn.iceblue.core.util.MessageUtils;
import cn.iceblue.core.util.RsaEncryptUtils;
import cn.iceblue.core.util.ip.IpUtils;
import cn.iceblue.data.service.SysConfigService;
import cn.iceblue.data.service.SysUserService;
import cn.iceblue.framework.manager.AsyncManager;
import cn.iceblue.framework.manager.factory.AsyncFactory;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Slf4j
@Component
public class SysLoginService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private TextEncryptor textEncryptor;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public SysUserEntity login(String username, String password, String code, String uuid) {
        // 验证码校验
        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);


        SysUserEntity entity = userService.getOne(Wrappers
                        .<SysUserEntity>lambdaQuery()
                        .eq(SysUserEntity::getUserCode, username)
                        .ne(SysUserEntity::getStatus, RecordStatusDict.DELETED.getCode())
                , false
        );

        Assert.notNull(entity, ResponseTemplate.USER_NON_EXISTENT);
        boolean equals = false;
        try {
            equals = RsaEncryptUtils.decrypt(password).equals(textEncryptor.decrypt(entity.getUserPassword()));
        } catch (Exception e) {
            log.error("解密失败,源字符串:{}", password, e);
            throw new DttRuntimeException(e, ResponseTemplate.DECODE_ERROR);
        }
        Assert.isTrue(equals, ResponseTemplate.LOGIN_FAIL);
        Assert.isTrue(!RecordStatusDict.DEACTIVATE.getCode().equals(entity.getStatus()), ResponseTemplate.LOGIN_USER_LOCKED);
        return entity;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        boolean captchaEnabled = sysConfigService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;
            String captcha = redisCache.getCacheObject(verifyKey);
            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, BaseConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new DttRuntimeException(ResponseTemplate.LOGIN_CAPTCHA_EXPIRE);
            }
            redisCache.deleteObject(verifyKey);
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, BaseConstants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new DttRuntimeException(ResponseTemplate.LOGIN_CAPTCHA_EXCEPTION);
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {

        // IP黑名单校验
        String blackStr = sysConfigService.selectConfigByKey("sys.login.blackIPList");
        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, BaseConstants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
            throw new DttRuntimeException(ResponseTemplate.IP_BLACK);
        }
    }
}
