package com.rampage.algorithm.leetcode;

/**
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 * Example 1:
 * Input: haystack = "hello", needle = "ll" Output: 2 
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba" Output: -1 
 * 
 * Clarification:
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 * 
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 * 2020-08
 */
public class ImplementStr {
    public int strStr(String haystack, String needle) {
        
        /**
         * 考虑实现KMP算法  
         * 难点： 求next数组 
         */
        // STEP1: 首先解决特殊情况
        if (needle == null || needle.isEmpty() || haystack == null) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        
        // STEP2: 求出next数组
        /**
         * next数组是利用子串的最大匹配前缀后缀
         * 首先要理解什么是next数组，next数组是用来加速子串移动速度，减少字符串匹配回溯的
         * 而next数组的推导来源于PMT Partial Match Table
         * 所谓的PMT就是不分匹配表，而部分匹配表的取值需要根据最大匹配前缀和后缀值
         * 所谓的前缀： 就是针对于字符串A 如果A=BS 其中S为非空字符串，那么B的集合即为A的前缀字符串集合
         * 所谓的后缀： 就是针对字符串A 如果A=SB 其中S为非空字符串，那么B的集合即为A的后缀字符串集合
         * 最大匹配前缀和后缀长度： 就是前缀字符串集合和后缀字符串集合相同字符串中长度最长的字符串的长度
         * 最大匹配前缀和后缀长度的作用：
         *  这里为了直观一点，举一个例子
         *  假设主串s为： ababababca  查找的模式串p为：abababca
         *  我们找到第一个不匹配的位置(这里为了突出，我们将不匹配的字符左右两边加上空格)：
         *      ababab a bca     ---- 此时主串的下标i=6
         *      ababab c a       ---- 此时模式串的下标为 j=6
         * 在使用KMP算法之前，如果使用暴力破解我们会将匹配字符串往后移动一位，即将i置为1，j重置为0  
         * 其实这种算法进行了大量的无效回溯
         * 
         * 而KMP算法的核心就是在于减少回溯，那么是如何减少的呢？
         * 当然是利用最大匹配前缀和后缀长度了：
         *    我们知道模式字符串是在j=6处不匹配，那么说明模式字符串p[0]到p[5]必然和主串s[0]到s[5]依次匹配，
         * 那么我们怎么根据这个来使得匹配回溯最少呢？ --- 最大前缀和后缀匹配长度
         *    我们不难得到ababab（当前已匹配的字符串）的最大前缀和后缀匹配长度为4 （abab）
         * 这就说明：主字符串的s[6-4=2]到s[6-1=5] 与模式串的 p[0]到p[3]一定一一匹配 
         * 那么其实我们可以固定主串不变，即i不变，将模式串向右滑动 6 - 4 = 2位，即将j变为最大前缀和后缀匹配长度4即可
         * 这里其实我们的next数组和PMT有一点区别，因为我们当前匹配的模式串的下标是6,但其实我们找的最长前缀和后缀的匹配长度确是前面6个字符 s[0]到s[5]
         * 所以可以认为其实我们的 next[i] = PMT[i -1]  为了编程方便 我们可以另 next[0] = -1;
         * 
         */
        int[] next = generateNext(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == - 1 || haystack.charAt(i) == needle.charAt(j)) {
                i++; 
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == needle.length()) {
            return i - j;
        }
        
        return -1;
    }

    private int[] generateNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;      // 注意这里j从0 k从-1开始
        while (j < needle.length() - 1) {
           if (k == -1 || needle.charAt(k) == needle.charAt(j)) {
               k++; j++;            // j移动一其实就是因为next[i] = PMT[i-1]  k移动1是因为k就是当前前缀和后缀的最大匹配长度
               if (needle.charAt(j) != needle.charAt(k)) {
                   next[j] = k;
               } else {
                   next[j] = next[k];
               }
           } else {
               k = next[k];         // k回退 k也不需要完全回退到0 因为 s[0]到s[k]必然是当前s[0]到s[j]的子串 那么s[0]到s[j]的最大前缀和后缀匹配长度必然大于等于s[0]到s[k]的最大前缀和后缀匹配长度
           }
        }
        
        return next;
    }
    
    public static void main(String[] args) {
        ImplementStr is = new ImplementStr();
        // System.out.println(is.strStr("hello", "ll"));
        // System.out.println(is.strStr("aaaaa", "aa"));
        // System.out.println(is.strStr("ababababca", "abababca"));
        System.out.println(is.strStr("ababcaaaaab", "aaaaab"));
    }
}
