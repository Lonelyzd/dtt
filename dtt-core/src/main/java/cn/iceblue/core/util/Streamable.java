package cn.iceblue.core.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@FunctionalInterface
public interface Streamable<T> extends Iterable<T>, Supplier<Stream<T>> {
    static <T> Streamable<T> empty() {
        return Collections::emptyIterator;
    }

    @SafeVarargs
    static <T> Streamable<T> of(T... t) {
        return () -> {
            return Arrays.asList(t).iterator();
        };
    }

    static <T> Streamable<T> of(Iterable<T> iterable) {
        Assert.notNull(iterable, "Iterable must not be null!");
        return iterable::iterator;
    }

    static <T> Streamable<T> of(Supplier<? extends Stream<T>> supplier) {
        return LazyStreamable.of(supplier);
    }

    default Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    default <R> Streamable<R> map(Function<? super T, ? extends R> mapper) {
        Assert.notNull(mapper, "Mapping function must not be null!");
        return of(() -> {
            return this.stream().map(mapper);
        });
    }

    default <R> Streamable<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        Assert.notNull(mapper, "Mapping function must not be null!");
        return of(() -> {
            return this.stream().flatMap(mapper);
        });
    }

    default Streamable<T> filter(Predicate<? super T> predicate) {
        Assert.notNull(predicate, "Filter predicate must not be null!");
        return of(() -> {
            return this.stream().filter(predicate);
        });
    }

    default boolean isEmpty() {
        return !this.iterator().hasNext();
    }

    default Streamable<T> and(Supplier<? extends Stream<? extends T>> stream) {
        Assert.notNull(stream, "Stream must not be null!");
        return of(() -> {
            return Stream.concat(this.stream(), (Stream)stream.get());
        });
    }

    default Streamable<T> and(T... others) {
        Assert.notNull(others, "Other values must not be null!");
        return of(() -> {
            return Stream.concat(this.stream(), Arrays.stream(others));
        });
    }

    default Streamable<T> and(Iterable<? extends T> iterable) {
        Assert.notNull(iterable, "Iterable must not be null!");
        return of(() -> {
            return Stream.concat(this.stream(), StreamSupport.stream(iterable.spliterator(), false));
        });
    }

    default Streamable<T> and(Streamable<? extends T> streamable) {
        return this.and((Supplier)streamable);
    }

    default List<T> toList() {
        return (List)this.stream().collect(StreamUtils.toUnmodifiableList());
    }

    default Set<T> toSet() {
        return (Set)this.stream().collect(StreamUtils.toUnmodifiableSet());
    }

    default Stream<T> get() {
        return this.stream();
    }

}