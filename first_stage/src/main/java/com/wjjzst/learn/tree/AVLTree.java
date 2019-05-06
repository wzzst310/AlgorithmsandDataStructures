package com.wjjzst.learn.tree;

import java.util.Comparator;

/**
 * @Author: Wjj
 * @Date: 2019/5/6 21:28
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(
            Comparator<E> comparator) {
        super(comparator);
    }

    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度 while循环同时更新高度 免得递归更新高度
                updateHeight(node);
            } else {
                // 恢复平衡 找到第一个不平衡的节点
                rebalance(node);
                // 只需要改一个平衡整个树就平衡了
                break;

            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    // 高度最低的那个不平衡的节点  // 就是grandparent节点
    private void rebalance(Node<E> grandparent) {
        Node<E> parent = ((AVLNode<E>) grandparent).tallerChild();
        Node<E> node = ((AVLNode<E>) parent).tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                rotateRight(grandparent);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grandparent);
            }
        } else { //R
            if (node.isLeftChild()) { //RL
                rotateRight(parent);
                rotateLeft(grandparent);
            } else { //RR
                rotateLeft(grandparent);
            }
        }
    }

    //左旋转
    private void rotateLeft(Node<E> node) {
        AVLNode<E> up = (AVLNode<E>) node;
        AVLNode<E> down = (AVLNode<E>) up.tallerChild();
        down.parent = up.parent;
        up.left = down.right;
    }

    //右旋转
    private void rotateRight(Node<E> node) {

    }

    // 左右子树高度差绝对值 < 1
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactory()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {

        private int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactory() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        private void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight > rightHeight) {
                return right;
            } else { //如果左右子节点高度一样 自己是父节点的哪边 就返回自己哪边的子节点
                return isLeftChild() ? left : right;
            }
        }
    }

}
