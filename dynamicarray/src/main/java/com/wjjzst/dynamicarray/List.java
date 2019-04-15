package com.wjjzst.dynamicarray;

public interface List<T> {
    void add(T t);

    void remove(int index);

    void clear();

    void size();

    void isEmpty();

    boolean contains(T t);

    T get(int index);


}
