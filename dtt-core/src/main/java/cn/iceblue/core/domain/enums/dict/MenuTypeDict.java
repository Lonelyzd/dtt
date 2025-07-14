package cn.iceblue.core.domain.enums.dict;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** 菜单类型
 * @author : IceBlue
 * @date : 2025/7/10 上午10:40
 **/
@Getter
@AllArgsConstructor
public enum MenuTypeDict implements IDictItem<Integer>{
    CATALOGUE(0,"目录"),
    MENU(1,"菜单"),
    BUTTON(2,"按钮"),

    ;
    private final Integer code;

    private final String text;
}
