package cn.iceblue.core.domain.enums.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 记录状态
 * @author : IceBlue
 * @date : 2025/6/18 下午1:41
 **/
@Getter
@AllArgsConstructor
public enum RecordStatusDict implements IDictItem<Integer>{

    NORMAL(0, "正常"),
    DEACTIVATE(1, "停用"),
    DELETED(2, "删除"),

    ;
    private final Integer code;

    private final String text;
}
