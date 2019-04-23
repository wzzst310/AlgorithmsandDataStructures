package com.wjjzst.exam.链表.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @Author: Wjj
 * @Date: 2019-04-23 00:33
 */
public class _21_合并两个有序链表merge_two_sorted_lists {
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//            ListNode node = null;
//            sortNode(l1);
//            sortNode(l2);
//            while(l1 !=null){
//                while(l2 != null){
//                    if(l1.val < l2.val){
//                        node = l1;
//                        node.next = null;
//                    }else{
//                        node = l2;
//                        node.next = null;
//                    }
//                }
//            }
            return null;
        }
        //链表排序值交换就行了
        private void sortNode(ListNode node){
            while(node != null) {
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
