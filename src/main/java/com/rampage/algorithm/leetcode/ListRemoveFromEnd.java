package com.rampage.algorithm.leetcode;

import com.rampage.algorithm.leetcode.model.ListNode;
import com.rampage.algorithm.leetcode.util.ListNodeUtils;

/**
 * Given a linked list, remove the n-th node from the end of list and return its
 * head.
 * 
 * Example:
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5. Note:
 * 
 * Given n will always be valid.
 * 
 * @author kidezi
 *
 */
public class ListRemoveFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 0;
        ListNode endNode = head;
        while (i++ < n) {
            endNode = endNode.next;
        }
        
        ListNode curNode = head;
        ListNode preNode = curNode;
        while (endNode != null) {
            preNode = curNode;
            curNode = curNode.next;
            endNode = endNode.next;
        }
        
        if (preNode == curNode) {
            head = preNode.next;            // 前置节点等于当前节点，说明根本没有移动，移除的是head节点
        } else {
            preNode.next = curNode.next;  
        }
        return head;
    }
    
    public static void main(String[] args) {
        ListRemoveFromEnd listRemove = new ListRemoveFromEnd();
        ListNode head = new ListNode(1);
        // head.next = new ListNode(2);
        // head.next.next = new ListNode(3);
        // head.next.next.next = new ListNode(4);
        // head.next.next.next.next = new ListNode(5);
        ListNodeUtils.print(listRemove.removeNthFromEnd(head, 1));
    }
}



