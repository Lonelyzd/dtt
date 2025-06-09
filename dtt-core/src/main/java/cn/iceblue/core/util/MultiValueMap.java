package cn.iceblue.core.util;

import java.util.List;
import java.util.Map;

public interface MultiValueMap<K, V> extends Map<K, List<V>> {
    V getFirst(K var1);

    void add(K var1, V var2);

    void addAll(K var1, List<? extends V> var2);

    void addAll(MultiValueMap<K, V> var1);

    default void addIfAbsent(K key, V value) {
        if (!this.containsKey(key)) {
            this.add(key, value);
        }

    }

    void set(K var1, V var2);

    void setAll(Map<K, V> var1);

    Map<K, V> toSingleValueMap();
}