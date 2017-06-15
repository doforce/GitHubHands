package com.edgarxie.utils.functional;

/**
 * Created by edgar on 16-12-15.
 */

@FunctionalInterface
public interface Consumers<T> {
    void accept(T t);
}
