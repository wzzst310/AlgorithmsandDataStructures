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
            ArrayList<ListNode> nodes = new ArrayList<>();

            getNodes(l1, nodes);
            getNodes(l2, nodes);
            return null;
        }
        ListNode sortNode(ListNode node){
            ListNode cur = node;
            while(cur.next != null){
                if(cur.val > cur.next.val){
                    ListNode temp = cur.next;
                    cur.next = cur.next.next;
                    temp.next = cur;
                }else{
                    cur = cur.next;
                }
            }
            return node;
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
