package com.rampage.algorithm.leetcode.util;

import com.rampage.algorithm.leetcode.model.ListNode;

/**
 * 
 * @author kidezi
 *
 */
public class ListNodeUtils {
    private ListNodeUtils() {
    }
    
    public static void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "--->");
            head = head.next;
        }
    }
}
