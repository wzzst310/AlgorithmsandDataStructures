package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.old.BinarySearchTree;
import com.wjjzst.learn.tree.printer.BinaryTrees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static void test1() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 100));
        }
//        list = Arrays.asList(7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12);
//        list = Arrays.asList(7, 4, 9, 2, 5);
        //list = Arrays.asList(13, 68, 89, 54, 78, 93, 84, 43, 16, 11, 71, 61, 55, 43, 91, 18, 32, 68, 90, 51);
        list = Arrays.asList(13, 11, 68, 54, 89, 43, 61, 78, 93, 16, 51, 55, 71, 84, 91, 18, 90, 32);
        System.out.println(list);
        bst.add(list);
        BinaryTrees.print(bst);
        System.out.println("\n");
        //bst.preorderTraversal();
        //bst.inorderTraversal();
        //bst.postorderTraversal();
        //bst.levelOrderTraversal();
        /*bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.println(element);
            }
        });*/
        //System.out.println(bst);
        // System.out.println(bst.height());
        // System.out.println(bst.height2());
        // System.out.println(bst.isComplete());
        /*list.clear();
        bst.preorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                list.add(element);
            }
        });
        System.out.println("前序:"+list);
        list.clear();
        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                list.add(element);
            }
        });
        System.out.println("中序:"+list);*/
        /*List<Integer> newList = new ArrayList<>();
        bst.levelOrder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                newList.add(element);
            }
        });
        System.out.println("层序:"+newList);*/
        bst.remove(13);
        BinaryTrees.print(bst);
        System.out.println("\n");
        bst.remove(68);
        BinaryTrees.print(bst);
        System.out.println("\n");

    }

    public static void main(String[] args) {
        test1();
        new AVLTree<>();

    }
}
