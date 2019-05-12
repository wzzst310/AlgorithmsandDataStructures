package com.wjjzst.learn.tree;

/**
 * @Author: Wjj
 * @Date: 2019/5/10 0:43
 * @desc:
 */
public class RBTree<E> extends BBSTree<E> {
    private static boolean RED = false;
    private static boolean BLACK = true;


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        // 添加的是根节点
        if (parent == null) {
            black(node);
            return;
        }
        // 如果父节点是黑色的则不用管 所有红黑树的性质都满足
        if (isBlack(parent)) {
            return;
        }
        // 叔父节点
        Node<E> uncle = parent.sibiling();
        // 祖父节点
        Node<E> grandparent = parent.parent;
        // 如果叔父节点是红色 则说明原来的节点有三个值 新加的元素后发生上溢现象 grandparent上去
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //把祖父节点染成红色并当作新添加的节点
            afterAdd(red(grandparent));
            return;
        }
        //叔父节点不是红色  需要旋转
        if (parent.isLeftChild()) {
            red(grandparent);
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grandparent);
        } else { //R
            red(grandparent);
            if (node.isLeftChild()) { //RL
                black(node);
                rotateRight(parent);
            } else { //RR
                black(parent);
            }
            rotateLeft(grandparent);
        }
    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 如果被删除的节点是红色的则不用做任何处理
        if (isRed(node)) {
            return;
        }
        // 如果用于取代node的replacement节点是红色的,则染成黑色即可
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        // 删除的是黑色叶子节点
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
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

    private RBNode<E> red(Node<E> node) {
        return color(node, RED);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {

        public boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }


    }

    @Override
    public Object string(Object node) {
        RBNode<E> rbNode = (RBNode<E>) node;
        StringBuilder sb = new StringBuilder();
        if (rbNode.color == RED) {
            sb.append("R_");
        }
        sb.append(rbNode.element).append("_p(");
        if (rbNode.parent == null) {
            sb.append("null");
        } else {
            sb.append((rbNode.parent.element));
        }
        sb.append(")");
        return sb.toString();
    }
}
