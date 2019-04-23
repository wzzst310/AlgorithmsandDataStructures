package com.wjjzst.exam.链表.easy;

import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @Author: Wjj
 * @Date: 2019-04-23 00:33
 */
public class _21_合并两个有序链表merge_two_sorted_lists {
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 != null) {
                ListNode cur = l1;
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = l2;
                sortNode(l1);
                return l1;
            }else{
                if(l2 != null){
                    sortNode(l2);
                    return l2;
                }else{
                    return null;
                }
            }
        }

        //链表排序值交换就行了
        private void sortNode(ListNode node) {
            while (node != null) {
                ListNode cur = node;
                while (cur.next != null) {
                    if (cur.val > cur.next.val) {
                        int temp = cur.val;
                        cur.val = cur.next.val;
                        cur.next.val = temp;
                    }
                    cur = cur.next;
                }
                node = node.next;
            }
        }

        private void getNodes(ListNode node, ArrayList<ListNode> nodes) {
            if (node != null) {
                while (node.next != null) {
                    ListNode temp = node;
                    temp.next = null;
                    nodes.add(temp);
                    node = node.next;
                }
            }
        }

    }
}