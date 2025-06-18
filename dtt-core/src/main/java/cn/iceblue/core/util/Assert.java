package cn.iceblue.core.util;

import cn.iceblue.core.domain.enums.ResponseTemplate;
import cn.iceblue.core.domain.enums.ResponseStatus;
import cn.iceblue.core.exception.DttRuntimeException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class Assert {
    public Assert() {
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    public static void state(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalStateException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void state(boolean expression) {
        state(expression, "[Assertion failed] - this state invariant must be true");
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression, Supplier<String> messageSupplier) {
        if (!expression) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, Supplier<String> messageSupplier) {
        if (object != null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }


    public static void notNull(Object object, Supplier<String> messageSupplier) {
        if (object == null) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    public static void notNull(Object o, ResponseTemplate fmt, Object... params) {
        notNull(o, fmt.getCode(), fmt.getText(), params);
    }

    /**
     * @param expression: 表达式
     * @param fmt:        消息模版{}
     * @param params:     参数
     * @return void
     * @author IceBlue
     * @date 2023/3/31 11:12
     **/
    public static void isTrue(boolean expression, ResponseTemplate fmt, Object... params) {
        isTrue(expression, fmt.getCode(), fmt.getText(), params);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void hasLength(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasLength(String text, Supplier<String> messageSupplier) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void hasLength(String text) {
        hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasText(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void hasText(String text, Supplier<String> messageSupplier) {
        if (StringUtils.isBlank(text)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void hasText(String text) {
        hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void doesNotContain(String textToSearch, String substring, Supplier<String> messageSupplier) {
        if (StringUtils.isNotBlank(textToSearch) && StringUtils.isNotBlank(substring) && textToSearch.contains(substring)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring, () -> {
            return "[Assertion failed] - this String argument must not contain the substring [" + substring + "]";
        });
    }


    public static void notEmpty(Object[] array, Supplier<String> messageSupplier) {
        if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void notEmpty(Object[] array) {
        notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void noNullElements(Object[] array, Supplier<String> messageSupplier) {
        if (array != null) {
            Object[] var2 = array;
            int var3 = array.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Object element = var2[var4];
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }


    public static void notEmpty(Collection<?> collection, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void noNullElements(Collection<?> collection, String message) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while (var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    throw new IllegalArgumentException(message);
                }
            }
        }

    }

    public static void noNullElements(Collection<?> collection, Supplier<String> messageSupplier) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while (var2.hasNext()) {
                Object element = var2.next();
                if (element == null) {
                    throw new IllegalArgumentException(nullSafeGet(messageSupplier));
                }
            }
        }

    }

    public static void notEmpty(Map<?, ?> map, Supplier<String> messageSupplier) {
        if (CollectionUtils.isEmpty(map)) {
            throw new IllegalArgumentException(nullSafeGet(messageSupplier));
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, (String) "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message);
        }

    }

    public static void isInstanceOf(Class<?> type, Object obj, Supplier<String> messageSupplier) {
        notNull(type, (String) "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, nullSafeGet(messageSupplier));
        }

    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        isInstanceOf(type, obj, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, (String) "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message);
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType, Supplier<String> messageSupplier) {
        notNull(superType, (String) "Super type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, nullSafeGet(messageSupplier));
        }

    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
        String className = obj != null ? obj.getClass().getName() : "null";
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.isNotBlank(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, className);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + "Object of class [" + className + "] must be an instance of " + type;
        }

        throw new IllegalArgumentException(result);
    }

    private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
        String result = "";
        boolean defaultMessage = true;
        if (StringUtils.isNotBlank(msg)) {
            if (endsWithSeparator(msg)) {
                result = msg + " ";
            } else {
                result = messageWithTypeName(msg, subType);
                defaultMessage = false;
            }
        }

        if (defaultMessage) {
            result = result + subType + " is not assignable to " + superType;
        }

        throw new IllegalArgumentException(result);
    }

    private static boolean endsWithSeparator(String msg) {
        return msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith(".");
    }

    private static String messageWithTypeName(String msg, Object typeName) {
        return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
    }


    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? (String) messageSupplier.get() : null;
    }

    public static <T> void isTrue(Predicate<T> predicate, T t, String fmt, Object... params) {
        isTrue(Objects.requireNonNull(predicate).test(t), fmt, params);
    }

    /**
     * 判断是否符合条件
     *
     * @param expression 表达式
     * @param fmt        信息格式, 比如: Hi, {}.
     * @param params     参数
     */
    public static void isTrue(boolean expression, String fmt, Object... params) {
        if (!expression) {
            throw new DttRuntimeException(StrFormatter.format(fmt, params));
        }
    }

    /**
     * @param expression: 表达式
     * @param code:       错误状态码
     * @param fmt:        信息格式, 比如: Hi, {}.
     * @param params:     参数
     * @return void
     * @author IceBlue
     * @date 2023/3/13 14:04
     **/
    public static void isTrue(boolean expression, Integer code, String fmt, Object... params) {
        if (!expression) {
            throw new DttRuntimeException(code, fmt, params);
        }
    }


    /**
     * @param expression: 表达式
     * @param code:       错误状态码枚举
     * @param fmt:        消息模版{}
     * @param params:     参数
     * @return void
     * @author IceBlue
     * @date 2023/3/31 11:12
     **/
    public static void isTrue(boolean expression, ResponseStatus code, String fmt, Object... params) {
        if (!expression) {
            throw new DttRuntimeException(code.getCode(), fmt, params);
        }
    }



    public static void notNull(Object o, Integer code, String fmt, Object... params) {
        isTrue(o != null, code, fmt, params);
    }

    public static void notNull(Object o, ResponseStatus code, String fmt, Object... params) {
        notNull(o, code.getCode(), fmt, params);
    }



    public static void notNull(Object o, String name) {
        isTrue(o != null, "{}不能为NULL", name);
    }


    /**
     * @param cs:     要校验的字符串
     * @param code:   错误状态码
     * @param fmt:    错误消息模版
     * @param params: 消息模版参数
     * @return void
     * @author IceBlue
     * @date 2024/5/20 16:04
     **/
    public static void notBlank(CharSequence cs, Integer code, String fmt, Object... params) {
        if (cs == null || cs.length() == 0) {
            throw new DttRuntimeException(code, fmt, params);
        }
    }

    public static void notBlank(CharSequence cs, ResponseStatus code, String fmt, Object... params) {
        notBlank(cs, code.getCode(), fmt, params);
    }


    /**
     * @param cs:     要校验的字符串
     * @param fmt:    错误消息模版
     * @param params: 消息模版参数
     * @return void
     * @author IceBlue
     * @date 2024/5/20 16:03
     **/
    public static void notBlank(CharSequence cs, String fmt, Object... params) {
        notBlank(cs, ResponseStatus.FAILED.getCode(), fmt, params);
    }

    public static void notBlank(CharSequence cs) {
        notBlank(cs, "字符不可为空", (Object) null);
    }


    public static void notEmpty(CharSequence cs, String name) {
        isTrue(cs != null && cs.length() > 0, "{}不能为空", name);
    }


    public static void notEmpty(Collection<?> collection, String name) {
        isTrue(collection != null && !collection.isEmpty(), "{}不能为空", name);
    }

    public static void notEmpty(Map<?, ?> map, String name) {
        isTrue(map != null && !map.isEmpty(), "{}不能为空", name);
    }

    public static <T> void notEmpty(T[] array, String name) {
        isTrue(array != null && array.length > 0 && array[0] != null, "{}不能为空", name);
    }
}