package com.rampage.algorithm.classical;

/**
 * 字符串匹配的KMP算法
 * @author KiDe
 *
 */
public class KMPProblem {
	public static void main(String[] args) {
        String original = "aaabaaabcab";
        String pattern = "aaab";
        System.out.println(kmp(original, pattern));
    }
                           
    private static int kmp(String original, String pattern) {
    	/**
    	 * 算法思想：
    	 * 	KMP算法的特别之处在于，它能够用线性的时间来实现字符串的匹配算法
    	 * 算法主要通过两个游标i和j，i从元字符串开始往后移动，j从匹配串开始往后移动
    	 * 匹配移动的时候有一个规则：就是i永远不回退，j根据当前匹配的结果决定是否回退以及回退至多少重新与原串i处字符开始匹配
    	 * 	所以当发生不匹配的时候j需要根据当前的匹配状态往前回退至可能重新与原串中i处匹配的位置
    	 * 而这个回退的位置就对应next数组
    	 * 
    	 * next数组的求法来源于真实前缀和真实后缀的最大匹配长度
    	 * 对于字符串abab我们定义 真实前缀为 a/ab/aba
    	 * 					  真实后缀为 b/ab/bab
    	 * 其真实前缀和真实后缀的最大匹配长度为 2
    	 * 这个2代表着，当j移动到b的下一个字符时与原字符串i处位置的字符不匹配时，则j应该回退到待匹配串的下标为2的字符
    	 * 也就是跳过最大匹配长度字符的下一个字符，所以我们的next数组中next[i]的值是pattern中0-i-1的最大前缀和后缀匹配长度
    	 * 为了计算方便我们同时定义next[0] = -1以及 next[1] = 0
    	 * 也就是a来继续匹配（j最多能回退多少是有讲究的，如果回退太少，因为i不变，相当于pattern要浪费更多的时间在回退上，那么
    	 * 最多能回退的距离就是回退到0-j-1的真实前缀和后缀的最长匹配长度的下一个字符）
    	 * 
    	 * 这里也存在一个优化点，我们假设pattern为 aaaab 当匹配到第5个字符b的时候不匹配，则我们如果按照前面说的，j应该回退至next[4] = 3
    	 * 也就是第四个a的位置，此时如果第4个a不匹配，按照原来继续回退至第三个a 我们发现其实前面三个a没有必要再次遍历 最好的情况是当第一次发现b不匹配的时候
    	 * pattern直接从next[0] = -1开始匹配  也就是可以通过 next[3] = next[2] = next[1] = next[0] = -1来优化j的回退次数从而减少匹配次数
    	 * 即在求next数组的时候，如果当前i和j字符串匹配，且下一个i和j也匹配，则可以另next[i] = next[j]
    	 */
        int[] next = generateNext(pattern);
        int i = 0; 
        int j = 0;
        while (i < original.length() && j < pattern.length()) {
            if (j == -1 || original.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == pattern.length()) {
                return i - pattern.length();
            } 
        }
        
        return -1;
    }
                           
    private static int[] generateNext(String pattern) {
        int[] next = new int[pattern.length()];
        int j = -1;  // j从-1开始
        int i = 0;   // 从第一个字符开始
        next[0] = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                // 当i和j所在位置字符相等的时候，j进一 此时以i的前缀和后缀最大匹配长度为j
                i++;
                j++;
                // next[i] = j;
                /**
                 * 这里有一个可以优化的点：
                 *   我们将j从新定位到next[j]看做把子串向右移动j - next[j], i和j都加1之后如果还想等,
                 *   可以又滑的距离应该更长
                 *   我们可以假设pattern为
                 *   aaabaaac
                 *   假设j加1之后到b 如果原字符串下一个字符（即i加1之后的位置）也为b
                 *   此时pattern可以向右滑动的距离应该为
                 */
                if (pattern.charAt(i) != pattern.charAt(j)) {
                	next[i] = j;
                } else {
                	next[i] = next[j];
                }
            } else {
                // i和j不匹配，j回退到0至j之间的字符的前缀后后缀的最长匹配
                j = next[j];
            }
        }
        return next;
    }
}
