package cn.iceblue.core.util;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class MultiValueMapCollector<T, K, V> implements Collector<T, MultiValueMap<K, V>, MultiValueMap<K, V>> {
    private final Function<T, K> keyFunction;
    private final Function<T, V> valueFunction;

    private MultiValueMapCollector(Function<T, K> keyFunction, Function<T, V> valueFunction) {
        this.keyFunction = keyFunction;
        this.valueFunction = valueFunction;
    }

    static <T, K, V> MultiValueMapCollector<T, K, V> of(Function<T, K> keyFunction, Function<T, V> valueFunction) {
        return new MultiValueMapCollector(keyFunction, valueFunction);
    }

    public Supplier<MultiValueMap<K, V>> supplier() {
        return () -> {
            return CollectionUtils.toMultiValueMap(new HashMap<>());
        };
    }

    public BiConsumer<MultiValueMap<K, V>, T> accumulator() {
        return (map, t) -> {
            map.add(this.keyFunction.apply(t), this.valueFunction.apply(t));
        };
    }

    public BinaryOperator<MultiValueMap<K, V>> combiner() {
        return (map1, map2) -> {
            Iterator var2 = map2.keySet().iterator();

            while(var2.hasNext()) {
                K key = (K) var2.next();
                map1.addAll(key, (List)map2.get(key));
            }

            return map1;
        };
    }

    public Function<MultiValueMap<K, V>, MultiValueMap<K, V>> finisher() {
        return Function.identity();
    }

    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
    }
}