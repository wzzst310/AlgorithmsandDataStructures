package com.wjjzst.learn.tree;

import com.wjjzst.learn.tree.printer.BinaryTreeInfo;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public void preorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        preorder(root, visitor);
    }


    private void preorder(Node<E> node, Visitor<E> visitor) {
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

    public void inorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        inorder(root, visitor);
    }


    private void inorder(Node<E> node, Visitor<E> visitor) {
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

    public void postorder(Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        postorder(root, visitor);
    }


    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    public void levelOrderTraversal() {
        /*if (root == null) {
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
        }*/
        levelOrder(new Visitor<E>() {
            @Override
            public void visit(E element) {
                System.out.println(element);
            }
        });

    }

    public void levelOrder(Visitor<E> visitor) {
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

    /**
     * 树的高度
     *
     * @return
     */

    public int height() {
        if (root == null) {
            return 0;
        }
        int height = 0;
        //存储着每一层的元素数量
        int levelSize = 1; //默认第一层有1个 跟不为空
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        if (levelSize == 0) {
            levelSize = queue.size();
            height++;
        }
        return height2(root);
    }


    public int height2() {
        return height2(root);
    }

    /**
     * 任何一个节点的高度
     *
     * @param node
     * @return
     */
    public int height2(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height2(node.left), height2(node.right));
    }

    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false; //要求后面节点是叶子的标志
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {  //后面的节点都应该全是叶子节点 如果不是叶子节点则返回false
                leaf = true;
            }
        }
        return true;
    }

    public void remove() {

    }

    public boolean contains(E element) {
        return false;
    }

    public static interface Visitor<E> {
        void visit(E element);
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

    private class Node<E> {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) {
            return;
        }
        sb.append(prefix).append("[").append(node.element).append("]").append("\n");
        if (node.left != null) {
            toString(node.left, sb, prefix + "[L]");
        }
        if (node.right != null) {
            toString(node.right, sb, prefix + "[R]");
        }

    }
}
