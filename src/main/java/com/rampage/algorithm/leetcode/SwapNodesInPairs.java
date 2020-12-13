package com.rampage.algorithm.leetcode;

import com.rampage.algorithm.leetcode.model.ListNode;
import com.rampage.algorithm.leetcode.util.ListNodeUtils;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * You may not modify the values in the list's nodes, only nodes itself may be
 * changed.
 * 
 * Example:
 * 
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        // 没有节点或者只有一个节点的时候直接返回head节点即可
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode reverseEnd = head;  // 存储每一个反转组最后一个元素
        ListNode preNode = head;
        ListNode curNode = preNode.next;
        ListNode tempNode = null;       // 临时节点
        ListNode firstNode = null;
        boolean setHead = false;
        int groupLen = 1;           // 每一组翻转链表的当前长度
        while (preNode != null) {
            if (groupLen == 1) {
                firstNode = preNode;
            }
            groupLen++;
            // 下一个节点为空： 则如果存在上一组末尾节点，则上一组末尾节点的下一个节点即为该节点， 否则头节点为该节点
            if (curNode == null) {
                if (!setHead) {
                    head = preNode;
                    setHead = true;
                } else {
                    reverseEnd.next = preNode;
                }
                break;
            }
            
            tempNode = curNode.next;       // 存储当前节点的下一个节点到临时变量中去（因为要逆序，当前节点的下一个节点需要修改）
            curNode.next = preNode;        // 逆序操作： 当前节点的下一个节点为原链表前一个节点
            if (preNode.next == curNode) {
                preNode.next = null;           // 如果前置节点的下一个节点是当前节点，则应该置空，否则会出现死循环
            }
            if (groupLen == 2) {
                // 已经到了最后一个节点
                if (!setHead) {
                    head = curNode;
                    setHead = true;
                } else {
                    reverseEnd.next = curNode;
                    reverseEnd = firstNode;
                }
                preNode = tempNode;
                if (preNode != null) {
                    curNode = preNode.next;
                }
                groupLen = 1;
            } else {
                preNode = curNode;
                curNode = tempNode;
            }
            
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);
        list1.next.next.next.next = new ListNode(5);
        list1.next.next.next.next.next = new ListNode(6);
        list1.next.next.next.next.next.next = new ListNode(7);
        list1.next.next.next.next.next.next.next = new ListNode(8);
        list1.next.next.next.next.next.next.next.next = new ListNode(9);
        SwapNodesInPairs swap = new SwapNodesInPairs();
        ListNodeUtils.print(swap.swapPairs(list1));
    }
}
