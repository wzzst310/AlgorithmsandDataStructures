package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
//        list = Arrays.asList(7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12);
        list = Arrays.asList(7, 4, 9, 2, 5);
        tree.add(list);
        BinaryTrees.print(tree);
        System.out.println("\n");
        //tree.preorderTraversal();
        //tree.inorderTraversal();
        //tree.postorderTraversal();
        //tree.levelOrderTraversal();
        /*tree.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.println(element);
            }
        });*/
        //System.out.println(tree);
        // System.out.println(tree.height());
        // System.out.println(tree.height2());
        System.out.println(tree.isComplete());
    }
}
