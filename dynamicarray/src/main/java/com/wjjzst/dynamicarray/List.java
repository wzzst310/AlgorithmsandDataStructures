package com.wjjzst.dynamicarray;

public interface List<E> {
    void add(E element);

    void add(int index, E element);

    E remove(int index);

    void clear();

    int size();

    boolean isEmpty();

    boolean contains(E element);

    E get(int index);

    E set(int index, E element);

    int indexOf(E element);


}
