package com.rampage.algorithm.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of
 * the longest valid (well-formed) parentheses substring.
 * 
 * Example 1:
 * 
 * Input: "(()" Output: 2 Explanation: The longest valid parentheses substring
 * is "()" Example 2:
 * 
 * Input: ")()())" Output: 4 Explanation: The longest valid parentheses
 * substring is "()()"
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        /**
         * 考虑使用动态规划，动态规划的三部曲
         * 最优子结构
         * 无后效性  --- 这个有后效性，后一个的最优解需要看前一个是怎么组成的  ---- 其实也可以用动态规划解的 
         * 这个也是无后效性，后一个解只和前一个解有关而不是前面所有解有关
         * 所以无法用动态规划
         * 
         * 考虑用一个数字表示左括号比右括号多的个数，发现当左括号比右括号多的时候，还有可能下一次抵消的时候能够计数加1
         * 如果从左到右遍历，发现右括号比左括号多，此时则不可能后续有符号组成括号对 -- 也行不通比较复杂
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        
        /**
         * 方法1：使用栈。 需要注意最开始应该给一个哨兵下标-1 当前绝对不能组成合法括号集合的元素下标为-1
         * 此时开始从左到右遍历，如果是左括号，则将元素下标入栈
         * 如果是右括号，则先将栈顶元素下标弹出 --- 即将与当前右括号匹配的左括号下标弹出
         * 然后判断当前栈的状态，如果当前栈为空 则将当前下标存入，标识当前下标是下一个可能符合条件的括号组合的前一个元素下标
         * 如果当前栈不为空， 则以当前右括号下标减去当前栈顶元素下标，在于当前最大长度比较更新最大长度
         */
        // 从左到右遍历
        int maxCount = 0;             // 存储总记录数 
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);               // 这个哨兵值很关键
        int len = s.length();
        char ch = 0;
        for (int i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxCount = Math.max(i - stack.peek(), maxCount);
                }
            }
        }
        
        /**
         * 方法二：使用动态规划
         * 最优子结构：假设dp[i]为以下标为i的元素结尾的最大长度
         * 状态转移方程：
         * dp[i] 需要看dp[i-1] 且需要看当前i下标元素的值
         * 当当前charAt(i)为左括号 则dp[i] = dp[i - 1]
         * 当当前charAt(i)为右括号 则先判断charAt(i - 1)是否为左括号，如果为左括号，则dp[i] = dp[i - 2] + 2 否则dp[i] = dp[i - 2] 
         * 如果charAt(i - 1)为右括号，则判断 i - dp[i - 1]下标的值，如果是左括号，此时dp[i] = dp[i - 1] + dp[i - dp[i - 1] -2]
         */
        int[] dp = new int[s.length()];
        for (int i = 1; i < len; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                dp[i] = dp[i-1];
            } else {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 < 0 ? 0 : dp[i - 2]) + 2;
                } else {
                    if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 < 0 ? 2 : dp[i - dp[i - 1] - 2] + 2);
                    }
                }
            }
        }
        maxCount = dp[s.length() - 1];
        return maxCount;
    }
    
    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses("(()(()"));
        System.out.println(longestValidParentheses.longestValidParentheses(")(()(())))(())()()()"));
        System.out.println(longestValidParentheses.longestValidParentheses("((())()())"));
    }
}
