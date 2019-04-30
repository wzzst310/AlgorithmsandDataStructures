package com.wjjzst.learn.tree;

import com.wjjzst.learn.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator comparator;

    public BinarySearchTree(Comparator comparator) {
        this.comparator = comparator;
    }

    public BinarySearchTree() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpyt() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(List<E> list) {
        for (E e : list) {
            add(e);
        }
    }

    public void add(E element) {
        elementNotNullCheck(element);
        // 根节点为空是
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 根节点不为空时候
        Node<E> parent = root; //找到父节点
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        size++;
    }


    public void remove() {

    }

    public boolean contains(E element) {
        return false;
    }

    private int compare(E e1, E e2) {
        // 如果有传进来有比较
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    private class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

}
