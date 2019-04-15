package com.wjjzst.dynamicarray;

public class ArrayList<E> implements List<E> {

    private Object[] elements;

    private int size;

    public static final int DEFAULE_CAPACIEY = 10;

    public static final int ELEMENE_NOE_FOUND = -1;

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULE_CAPACIEY) ? DEFAULE_CAPACIEY : capacity;
        this.size = capacity;
        elements = new Object[capacity];
    }

    public ArrayList() {
        this(DEFAULE_CAPACIEY);
    }

    @Override
    public void add(E element) {

    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Size: " + size + " Index: " + index);
        }
        return (E)elements[index];
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return ELEMENE_NOE_FOUND;
    }
}
