package cn.iceblue.core.util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface StreamUtils {
    static <T> Stream<T> createStreamFromIterator(Iterator<T> iterator) {
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, 256);
        return StreamSupport.stream(spliterator, false);
    }

    static <T> Stream<T> createStreamFromIterator(CloseableIterator<T> iterator) {
        Assert.notNull(iterator, "Iterator must not be null!");
        return (Stream)createStreamFromIterator((Iterator)iterator).onClose(() -> {
            iterator.close();
        });
    }

    static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList);
    }

    static <T> Collector<T, ?, Set<T>> toUnmodifiableSet() {
        return Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet);
    }

    static <T, K, V> Collector<T, MultiValueMap<K, V>, MultiValueMap<K, V>> toMultiMap(Function<T, K> keyFunction, Function<T, V> valueFunction) {
        return MultiValueMapCollector.of(keyFunction, valueFunction);
    }

    static <T> Stream<T> fromNullable( T source) {
        return source == null ? Stream.empty() : Stream.of(source);
    }

    static <L, R, T> Stream<T> zip(Stream<L> left, Stream<R> right, final BiFunction<L, R, T> combiner) {
        Assert.notNull(left, "Left stream must not be null!");
        Assert.notNull(right, "Right must not be null!");
        Assert.notNull(combiner, "Combiner must not be null!");
        final Spliterator<L> lefts = left.spliterator();
        final Spliterator<R> rights = right.spliterator();
        long size = Long.min(lefts.estimateSize(), rights.estimateSize());
        int characteristics = lefts.characteristics() & rights.characteristics();
        boolean parallel = left.isParallel() || right.isParallel();
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(size, characteristics) {
            public boolean tryAdvance(Consumer<? super T> action) {
                return lefts.tryAdvance((left) -> {
                    rights.tryAdvance((right) -> {
                        action.accept(combiner.apply(left, right));
                    });
                });
            }
        }, parallel);
    }
}