package com.wjjzst.exam.链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @Author: Wjj
 * @Date: 2019-04-19 00:33
 */
public class _206_反转链表reverse_linked_list {
    static class Solution {
        public ListNode reverseList(ListNode head) {
            while (head != null) {
                ListNode newHead = null;
                ListNode temp = head.next;
                head.next = newHead;
                newHead.next = head;
                head = temp;
                head = head.next;
            }
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution solution = new Solution();
        ListNode listNode = solution.reverseList(node1);
        System.out.println(listNode.val);
    }
}
