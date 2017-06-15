package com.edgarxie.utils.functional;

/**
 * Created by edgar on 16-12-24.
 */

@FunctionalInterface
public interface BiConsumers<T,U> {
    void accept(T t, U u);
}
