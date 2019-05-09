package com.wjjzst.learn.tree;

/**
 * @Author: Wjj
 * @Date: 2019/5/10 0:43
 * @desc:
 */
public class RBTree<E> extends BBSTree<E> {
    private static boolean RED = false;
    private static boolean BLACK = true;

    private boolean isBlack(Node<E> node) {
        return ((RBNode<E>) node).color == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return ((RBNode<E>) node).color == RED;
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private RBNode<E> color(Node<E> node, boolean color) {
        if (node != null) {
            ((RBNode<E>) node).color = color;
        }
        return (RBNode<E>) node;
    }

    private RBNode<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private RBNode<E> red(Node<E> node, boolean color) {
        return color(node, RED);
    }

    private static class RBNode<E> extends Node<E> {

        public boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (color == RED) {
                sb.append("R_");
            }
            return sb.append(element).toString();
        }
    }
}
