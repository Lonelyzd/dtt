package cn.iceblue.core.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class DateFormatter {
    public final String[] PARSEPATTERNS = new String[]{"yyyy-MM",
            "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd",
            "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};
    Logger logger = LoggerFactory.getLogger(DateFormatter.class);

    public Date format(Object value) {
        if (value instanceof Date) {
            return (Date) value;
        } else {
            if (value instanceof String && !StringUtils.isEmpty((String) value)) {
                try {
                    return DateUtils.parseDate(String.valueOf(value), PARSEPATTERNS);
                } catch (ParseException e) {
                    logger.debug("@@@ 非法日期！以 NULL 处理。", e);
                }
            }
        }
        return null;
    }
}
