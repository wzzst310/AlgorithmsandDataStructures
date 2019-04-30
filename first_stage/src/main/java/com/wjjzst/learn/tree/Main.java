package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.printer.BinaryTrees;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        tree.add(list);
        BinaryTrees.print(tree);
    }
}
