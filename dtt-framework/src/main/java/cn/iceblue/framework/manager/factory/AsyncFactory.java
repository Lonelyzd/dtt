package cn.iceblue.framework.manager.factory;

import cn.iceblue.core.domain.constant.BaseConstants;
import cn.iceblue.core.domain.enums.dict.BooleanDict;
import cn.iceblue.core.pojo.entity.SysLoginInforEntity;
import cn.iceblue.core.pojo.entity.SysOperLogEntity;
import cn.iceblue.core.util.LogUtils;
import cn.iceblue.core.util.ServletUtils;
import cn.iceblue.core.util.ip.AddressUtils;
import cn.iceblue.core.util.ip.IpUtils;
import cn.iceblue.core.util.spring.SpringUtils;
import cn.iceblue.data.service.SysLoginInforService;
import cn.iceblue.data.service.SysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 *
 * @author ruoyi
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr();
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginInforEntity logininfor = new SysLoginInforEntity();
                logininfor.setUserId(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                logininfor.setLoginTime(new Date());
                // 日志状态
                if (StringUtils.equalsAny(status, BaseConstants.LOGIN_SUCCESS, BaseConstants.LOGOUT, BaseConstants.REGISTER)) {
                    logininfor.setStatus(BooleanDict.SUCCESS.getCode());
                } else if (BaseConstants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(BooleanDict.FAIL.getCode());
                }
                // 插入数据
                SpringUtils.getBean(SysLoginInforService.class).save(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLogEntity operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).save(operLog);
            }
        };
    }
}
