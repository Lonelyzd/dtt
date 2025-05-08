package cn.iceblue.core.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

@Slf4j
public class DateFormatter {
    public final String[] PARSEPATTERNS = new String[]{"yyyy-MM",
            "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
            "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};

    public Date format(Object value) {
        if (value instanceof Date) {
            return (Date) value;
        } else {
            if (value instanceof String && !StringUtils.isEmpty((String) value)) {
                try {
                    return DateUtils.parseDate(String.valueOf(value), PARSEPATTERNS);
                } catch (ParseException e) {
                    log.error("日期格式化异常", e);
                }
            }
        }
        return null;
    }
}
