package cn.iceblue.core.domain.enums.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 逻辑字典
 * @author : IceBlue
 * @date : 2025/7/1 下午2:49
 **/
@Getter
@AllArgsConstructor
public enum BooleanDict implements IDictItem<Integer>{
    TRUE(1, "是"),
    FALSE(0, "否"),
    SUCCESS(1,"成功"),
    FAIL(0,"失败"),


    ;
    private final Integer code;

    private final String text;

}
