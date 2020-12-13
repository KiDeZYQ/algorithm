package com.rampage.algorithm.leetcode.model;

/**
 * 链表节点
 * @author kidezi
 *
 */
public class ListNode {
    // 全定义为public方便访问
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
