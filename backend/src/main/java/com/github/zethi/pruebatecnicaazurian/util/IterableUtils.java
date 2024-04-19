package com.github.zethi.pruebatecnicaazurian.util;

import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.stream.StreamSupport;

public class IterableUtils {
    public static <T> T[] iteratorToArray(Spliterator<T> iterator, IntFunction<T[]> generator) {
        return StreamSupport.stream(iterator, false).toArray(generator);
    }
}
