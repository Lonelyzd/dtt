package cn.iceblue.core.util;

import org.apache.commons.lang3.ObjectUtils;

import java.util.*;

public abstract class CollectionUtils {
    static final float DEFAULT_LOAD_FACTOR = 0.75F;

    public CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <K, V> HashMap<K, V> newHashMap(int expectedSize) {
        return new HashMap((int) ((float) expectedSize / 0.75F), 0.75F);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(int expectedSize) {
        return new LinkedHashMap((int) ((float) expectedSize / 0.75F), 0.75F);
    }

    public static <K, V> MultiValueMap<K, V> toMultiValueMap(Map<K, List<V>> targetMap) {
        return new MultiValueMapAdapter(targetMap);
    }


    public static <K, V> void mergePropertiesIntoMap(Properties props, Map<String, String> map) {
        String key;
        String value;
        if (props != null) {
            for (Enumeration<?> en = props.propertyNames(); en.hasMoreElements(); map.put(key, value)) {
                key = (String) en.nextElement();
                value = (String) props.get(key);
                if (value == null) {
                    value = props.getProperty(key);
                }
            }
        }

    }


    public static boolean containsInstance(Collection<?> collection, Object element) {
        if (collection != null) {
            Iterator var2 = collection.iterator();

            while (var2.hasNext()) {
                Object candidate = var2.next();
                if (candidate == element) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean containsAny(Collection<?> source, Collection<?> candidates) {
        return findFirstMatch(source, candidates) != null;
    }

    public static <E> E findFirstMatch(Collection<?> source, Collection<E> candidates) {
        if (!isEmpty(source) && !isEmpty(candidates)) {
            Iterator var2 = candidates.iterator();

            Object candidate;
            do {
                if (!var2.hasNext()) {
                    return null;
                }

                candidate = var2.next();
            } while (!source.contains(candidate));

            return (E) candidate;
        } else {
            return null;
        }
    }

    public static <T> T findValueOfType(Collection<?> collection, Class<T> type) {
        if (isEmpty(collection)) {
            return null;
        } else {
            T value = null;
            Iterator var3 = collection.iterator();

            while (true) {
                Object element;
                do {
                    if (!var3.hasNext()) {
                        return value;
                    }

                    element = var3.next();
                } while (type != null && !type.isInstance(element));

                if (value != null) {
                    return null;
                }

                value = (T) element;
            }
        }
    }

    public static Object findValueOfType(Collection<?> collection, Class<?>[] types) {
        if (!isEmpty(collection) && !ObjectUtils.isEmpty(types)) {
            Class[] var2 = types;
            int var3 = types.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Class<?> type = var2[var4];
                Object value = findValueOfType(collection, type);
                if (value != null) {
                    return value;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static boolean hasUniqueObject(Collection<?> collection) {
        if (isEmpty(collection)) {
            return false;
        } else {
            boolean hasCandidate = false;
            Object candidate = null;
            Iterator var3 = collection.iterator();

            while (var3.hasNext()) {
                Object elem = var3.next();
                if (!hasCandidate) {
                    hasCandidate = true;
                    candidate = elem;
                } else if (candidate != elem) {
                    return false;
                }
            }

            return true;
        }
    }

    public static Class<?> findCommonElementType(Collection<?> collection) {
        if (isEmpty(collection)) {
            return null;
        } else {
            Class<?> candidate = null;
            Iterator var2 = collection.iterator();

            while (var2.hasNext()) {
                Object val = var2.next();
                if (val != null) {
                    if (candidate == null) {
                        candidate = val.getClass();
                    } else if (candidate != val.getClass()) {
                        return null;
                    }
                }
            }

            return candidate;
        }
    }

    public static <T> T firstElement(Set<T> set) {
        if (isEmpty((Collection) set)) {
            return null;
        } else if (set instanceof SortedSet) {
            return (T) ((SortedSet) set).first();
        } else {
            Iterator<T> it = set.iterator();
            T first = null;
            if (it.hasNext()) {
                first = it.next();
            }

            return first;
        }
    }


    public static <T> T firstElement(List<T> list) {
        return isEmpty((Collection) list) ? null : list.get(0);
    }


    public static <T> T lastElement(Set<T> set) {
        if (isEmpty((Collection) set)) {
            return null;
        } else if (set instanceof SortedSet) {
            return (T) ((SortedSet) set).last();
        } else {
            Iterator<T> it = set.iterator();

            T last;
            for (last = null; it.hasNext(); last = it.next()) {
            }

            return last;
        }
    }


    public static <T> T lastElement(List<T> list) {
        return isEmpty((Collection) list) ? null : list.get(list.size() - 1);
    }

    public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] array) {
        ArrayList<A> elements = new ArrayList();

        while (enumeration.hasMoreElements()) {
            elements.add(enumeration.nextElement());
        }

        return elements.toArray(array);
    }

    public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
        return (enumeration != null ? new EnumerationIterator(enumeration) : Collections.emptyIterator());
    }


    private static class EnumerationIterator<E> implements Iterator<E> {
        private final Enumeration<E> enumeration;

        public EnumerationIterator(Enumeration<E> enumeration) {
            this.enumeration = enumeration;
        }

        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        public E next() {
            return this.enumeration.nextElement();
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Not supported");
        }


    }
}
