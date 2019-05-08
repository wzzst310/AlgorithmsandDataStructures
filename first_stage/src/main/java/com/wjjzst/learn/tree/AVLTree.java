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

    @Override
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
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalance(node)) {
                //更新高度 while循环同时更新高度 免得递归更新高度
                updateHeight(node);
            } else {
                // 恢复平衡 找到第一个不平衡的节点
                rebalance1(node);
                // 需要一直往上找直到找到平衡的父节点  一直去恢复平
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
                rotate(grandparent, node.left, node, node.right, parent, parent.right, grandparent, grandparent.right);
            } else { // LR
                rotate(grandparent, parent.left, parent, node.left, node, node.right, grandparent, grandparent.right);
            }
        } else { //R
            if (node.isLeftChild()) { //RL
                rotate(grandparent, grandparent.left, grandparent, node.left, node, node.right, parent, parent.right);
            } else { //RR
                rotate(grandparent, grandparent.left, grandparent, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotate(
            Node<E> r, // 原来子树里面的根节点
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g) {
        // 让d成为根节点,并维护与原本根节点的父节点关系
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // 处理a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b); //b的左右子节点变了需要调整高度
        // 处理e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        updateHeight(f); //f的左右子节点变了需要调整高度
        // 处理b-d-f
        d.left = b;
        b.parent = d;
        d.right = f;
        f.parent = d;
        updateHeight(d); //d的左右子节点变了需要调整高度
    }

    // 高度最低的那个不平衡的节点  // 就是grandparent节点
    private void rebalance1(Node<E> grandparent) {
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
    private void rotateLeft(Node<E> grandparent) {
        Node<E> parent = grandparent.right;
        Node<E> child = parent.left;
        grandparent.right = child;
        parent.left = grandparent;
        afterRotate(grandparent, parent, child);
    }

    //右旋转
    private void rotateRight(Node<E> grandparent) {
        Node<E> parent = grandparent.left;
        Node<E> child = parent.right;
        grandparent.left = child;
        parent.right = grandparent;
        afterRotate(grandparent, parent, child);
        parent.parent = grandparent.parent;
    }

    // 更新完之后做的事情
    private void afterRotate(Node<E> grandparent, Node<E> parent, Node<E> child) {
        // 新子树根节点代替原子树根节点
        parent.parent = grandparent.parent;
        if (grandparent.isLeftChild()) {
            grandparent.parent.left = parent;
        } else if (grandparent.isRightChild()) {
            grandparent.parent.right = parent;
        } else {// 根节点情况
            root = parent;
        }
        // 更新child为parent
        if (child != null) {
            child.parent = grandparent;
        }
        // 更新grandparent的parent
        grandparent.parent = parent;

        //更新高度 先从高度小的开始更新
        updateHeight(grandparent);
        updateHeight(parent);
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
            } else if (leftHeight < rightHeight) {
                return right;
            } else { //如果左右子节点高度一样 自己是父节点的哪边 就返回自己哪边的子节点
                return isLeftChild() ? left : right;
            }
        }
    }

    @Override
    public Object string(Object node) {
        StringBuilder sb = new StringBuilder();
        sb.append(((AVLNode<E>) node).element).append("_p(");
        if (((AVLNode<E>) node).parent == null) {
            sb.append("null");
        } else {
            sb.append(((AVLNode<E>) node).parent.element);
        }
        sb.append(")_h(").append((((AVLNode<E>) node).height)).append(")");
        return sb.toString();
    }

}
