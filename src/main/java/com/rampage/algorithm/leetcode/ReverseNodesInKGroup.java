package com.rampage.algorithm.leetcode;

import com.rampage.algorithm.leetcode.model.ListNode;
import com.rampage.algorithm.leetcode.util.ListNodeUtils;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list. k is a positive integer and is less than or equal
 * to the length of the linked list. If the number of nodes is not a multiple of
 * k then left-out nodes in the end should remain as it is.
 * 
 * Example: Given this linked list: 1->2->3->4->5 For k = 2, you should return:
 * 2->1->4->3->5 For k = 3, you should return: 3->2->1->4->5
 * 
 * Note: Only constant extra memory is allowed. You may not alter the values in
 * the list's nodes, only nodes itself may be changed.
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *
 */
public class ReverseNodesInKGroup {
    /**
     * 查看solution得知这道题可以从两方面进行优化
     *  1. 可以通过 head节点来遍历实现倒叙 
     *  2. 可以通过定义一个dummy节点采用哨兵的方式去掉特殊情况讨论 
     *  3. 可以通过截断一个group的方式来实现对于每个子问题，类似于对长度为k的链表进行逆序
     * 
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 没有节点或者只有一个节点的时候直接返回head节点即可
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        boolean setHead = false;
        ListNode startNode = head;
        ListNode curNode = startNode;
        int i = 0;
        while (curNode != null) {
            i++;
            if (i == k) {
                if (!setHead) {
                    head = curNode;
                    setHead = true;
                    startNode = reverseNode(startNode, curNode, true);
                } else {
                    startNode = reverseNode(startNode, curNode, false);
                }
                curNode = startNode.next;
                i = 0;
            } else {
                curNode = curNode.next;
            }
        }

        return head;
    }

    private ListNode reverseNode(ListNode startNode, ListNode endNode, boolean headNull) {
        ListNode curNode = null;
        ListNode temp = null;
        if (!headNull) {
            temp = startNode.next;
            startNode.next = endNode;
            startNode = temp;
        }
        curNode = startNode;
        ListNode nextNode = curNode.next;
        startNode.next = endNode.next; // 首尾相连

        while (nextNode != null && nextNode != endNode) {
            temp = nextNode.next;
            nextNode.next = curNode;
            if (curNode.next == nextNode) {
                curNode.next = null;
            }
            curNode = nextNode;
            nextNode = temp;
        }
        endNode.next = curNode;

        return startNode;
    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);
        list1.next.next.next.next = new ListNode(5);
        list1.next.next.next.next.next = new ListNode(6);
        ReverseNodesInKGroup reverse = new ReverseNodesInKGroup();
        ListNodeUtils.print(reverse.reverseKGroup(list1, 2));
    }
}
