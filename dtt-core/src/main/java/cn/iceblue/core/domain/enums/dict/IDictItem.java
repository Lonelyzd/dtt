package cn.iceblue.core.domain.enums.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: IceBlue
 * @Date: 2022/3/29 14:08
 * @return: null
 * @Description: 字典表接口
 **/
public interface IDictItem<T extends Serializable> {


    T getCode();

    String getText();

    /**
     * 通过 value 获取指定 枚举类型中的 枚举对象
     *
     * @param enumClass
     * @param value
     * @param <R>
     * @return
     */
    static <R extends IDictItem, T> R getByCode(Class<R> enumClass, T value) {
        //通过反射取出Enum所有常量的属性值
        for (R each : enumClass.getEnumConstants()) {
            //利用value进行循环比较，获取对应的枚举
            if (value.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }


    /**
     * 当前对象的 value 是否 和参数中的 value 相等
     *
     * @param value
     * @return
     */
    default boolean isCode(String value) {
        return this.getCode().equals(value);
    }

    /**
     * 当前对象是否和已知对象不等
     *
     * @param item
     * @return
     */
    default boolean isNotEquals(IDictItem item) {
        return this != item;
    }

    /**
     * 当前对象key是否相等
     *
     * @param k 对象的key
     * @return
     */
    default boolean equals(T k) {
        return this.getCode().equals(k);
    }

    /**
     * 根据字典类名,获取字典枚举类所有
     *
     * @param dicName:
     * @return List<Class < IDictItem>>
     * @author IceBlue
     * @date 2025/7/14 下午4:30
     **/
    static List<IDictItem> getSubClass(String dicName) {
        String packageName = IDictItem.class.getPackage().getName();
        try {
            Class<?> aClass = Class.forName(packageName + "." + dicName);
            if (IDictItem.class.isAssignableFrom(aClass)) {
                IDictItem[] enumConstants = (IDictItem[]) aClass.getEnumConstants();
                return Arrays.asList(enumConstants);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
