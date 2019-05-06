package com.wjjzst.learn.tree;


import com.wjjzst.learn.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Wjj
 * @Date: 2019/5/6 12:17
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpyt() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * 前序遍历
     */
    public void preorderTraversal() {
        preorderTraversal(root);
    }


    private void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    public void preorder(BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorder(root, visitor);
    }


    private void preorder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }


    private void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    public void inorder(BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        inorder(root, visitor);
    }


    private void inorder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal() {
        postorderTraversal(root);
    }


    private void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    public void postorder(BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorder(root, visitor);
    }


    private void postorder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        /*levelOrder(new BinarySearchTree.Visitor<E>() {
            @Override
            public void visit(E element) {
                System.out.println(element);
            }
        });*/

    }

    public void levelOrder(BinarySearchTree.Visitor<E> visitor) {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }



    public static interface Visitor<E> {
        void visit(E element);
    }


    protected class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }
}
