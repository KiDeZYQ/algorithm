package com.rampage.algorithm.leetcode;

import com.rampage.algorithm.leetcode.model.ListNode;
import com.rampage.algorithm.leetcode.util.ListNodeUtils;

/**
 * Merge two sorted linked lists and return it as a new sorted list. The new
 * list should be made by splicing together the nodes of the first two lists.
 * 
 * Example:
 * 
 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
 * 
 * @author kidezi
 *
 */
public class MergeTwoSortedList {
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        
        ListNode curNode1 = l1;
        ListNode curNode2 = l2;
        
        ListNode preNode = null;
        /**
         * 总体思想就是比对两个链表的当前节点，取当前节点中较小的节点作为下一个节点
         */
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val > curNode2.val) {
                if (preNode == null) {
                    preNode = l2;
                    curNode2 = curNode2.next;
                }
                while (curNode2 != null && curNode2.val < curNode1.val) {
                    preNode.next = curNode2;
                    preNode = preNode.next;
                    curNode2 = curNode2.next;
                }
            } else {
                if (preNode == null) {
                    preNode = l1;
                    curNode1 = curNode1.next;
                }
                // 当一直都是l1中元素小的时候，则l1向前推进，直到找到l1中节点不小于l2中当前节点
                while (curNode1 != null && curNode2.val >= curNode1.val) {
                    preNode.next = curNode1;
                    preNode = preNode.next;
                    curNode1 = curNode1.next;
                }
            }
        }
        if (curNode1 != null) {
            preNode.next = curNode1;
        } else if (curNode2 != null) {
            preNode.next = curNode2;
        }
        
        return l1.val > l2.val ? l2 : l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(5);
        
        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
        
        MergeTwoSortedList mergeList = new MergeTwoSortedList();
        ListNodeUtils.print(mergeList.mergeTwoLists(l1, l2));
    }
}
