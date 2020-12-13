package com.rampage.algorithm.leetcode;

import com.rampage.algorithm.leetcode.model.ListNode;
import com.rampage.algorithm.leetcode.util.ListNodeUtils;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * Example:
 * 
 * Input: [ 1->4->5, 1->3->4, 2->6 ] Output: 1->1->2->3->4->4->5->6
 * 当前解法在时间上还需要优化
 * @author kidezi
 *
 */
public class MergeKSortedList {
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        
        // 选取值最小的节点作为最小节点
        ListNode head = pickMinValNode(lists);
        // 传入的列表都为空的情况需要考虑
        if (head == null) {
            return null;
        }
        ListNode curNode = head;
        while ((curNode.next = pickMinValNode(lists)) != null) {
            curNode = curNode.next;
        }
        
        return head;
    }

    /**
     * 选取最小节点
     * @param lists 链表列表
     * @return 最小值节点
     */
    private ListNode pickMinValNode(ListNode[] lists) {
        ListNode minNode = lists[0];
        int minIndex = 0;
        for (int i = 1, len = lists.length; i < len; i++) {
            if (lists[i] == null) {
                continue;
            }
            if (minNode == null || minNode.val > lists[i].val) {
                minIndex = i;
                minNode = lists[i];
            }
        }
        
        if (minNode != null) {
            lists[minIndex] = lists[minIndex].next;
        }
        return minNode;
    }
    
    public static void main(String[] args) {
        MergeKSortedList mergeKSortedList = new MergeKSortedList();
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(3);
        list1.next.next = new ListNode(5);
        
        ListNode list2 = new ListNode(2);
        list2.next = new ListNode(5);
        
        ListNode list3 = new ListNode(1);
        list3.next = new ListNode(4);
        ListNodeUtils.print(mergeKSortedList.mergeKLists(new ListNode[] {list1, list2, list3}));
    }
}
