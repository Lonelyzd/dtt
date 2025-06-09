package cn.iceblue.core.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.io.Closeable;

public interface CloseableIterator<T> extends Iterator<T>, Closeable {
    void close();

    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this, 0L, 0);
    }

    default Stream<T> stream() {
        return (Stream) StreamSupport.stream(this.spliterator(), false).onClose(this::close);
    }
}
