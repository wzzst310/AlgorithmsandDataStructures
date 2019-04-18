package com.wjjzst.learn.dynamicarray;

import com.wjjzst.learn.linkedlist.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<>();
        list.add(1);
        list.add("A");
        list.remove(1);
        list.add("B");
        list.add(null);
        list.add(new Object());
        // list.clear();
        System.out.println(list);
        System.out.println(list.contains(null));
        System.out.println(list.indexOf("B"));
        System.out.println(list.get(3));
    }
}