package cn.iceblue.core.domain.enums.dict;

import java.io.Serializable;

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
	static <R extends IDictItem,T> R getByCode(Class<R> enumClass, T value) {
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
}
