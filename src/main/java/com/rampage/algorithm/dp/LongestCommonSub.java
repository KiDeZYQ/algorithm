package com.rampage.algorithm.dp;

import java.util.Arrays;

/**
 * 最长公共子序列问题：
 * 		给两个字符串，求两个字符串的最差个公共的子序列		
 * 			一般是指字串不连续但是要按顺序来
 * @author ziyuqi
 *
 */
public class LongestCommonSub {
	public static void main(String[] args) {
		int[] a1 = {1, 2, 3, 5 ,4 ,7 ,9};
		int[] a2 = {2, 1, 5, 2, 3, 4, 8, 7};
		/**
		 * 动态规划问题：
		 * 		设 dp[i][j] 为a1以a1[i]结尾a2以a2[j]结尾时的最长公共子序列的长度
		 * 则有动态转移方程：
		 * 		dp[i][j] = dp[i - 1][j - 1]  			(a1[i] != a2[j])
		 * 		dp[i][j] = dp[i - 1][j - 1] + 1 		(a1[i] = a2[j])
		 * 初始条件
		 * 	 if (a1[0] == a2[0]) {
		 * 		dp[0][0] = 1;
		 * }
 		 */
		int[][] dp = new int[a1.length][a2.length];
		if (a1[0] == a2[0]) {
			dp[0][0] = 1;
		}
		int[][] select = new int[a1.length][a2.length];
		for (int i=1; i<a1.length; i++) {
			for (int j=1; j<a2.length; j++) {
				if (a1[i] == a2[j]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					select[i][j] = 0;
				} else {
					// dp[i][j] = Math.max(dp[i - 1][j], dp[i][j-1]) ;
					if (dp[i - 1][j] > dp[i][j - 1]) {
						dp[i][j] = dp[i - 1][j];
						select[i][j] = -1;
					} else {
						dp[i][j] = dp[i][j - 1];
						select[i][j] = 1;
					}
				}
			}
		}
		for (int i=0; i<a1.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println("\n\n");
		// 回溯法找到最长子序列本尊
		for (int i=0; i<a1.length; i++) {
			System.out.println(Arrays.toString(select[i]));
		}
		int curVal = 0;
		int j = a2.length - 1;
		int i = a1.length - 1;
		while (i >=0 && j >= 0) {
			curVal = select[i][j];
			if (curVal == 0) {
				System.out.print(String.format("(%d)<---", a1[i]));
				i--;
				j--;
			} else if (curVal == -1) {
				i--;
			} else {
				j--;
			}
		}
		
		/**
		 * 如果求最长公共子串（要求连续）的话，则另dp[i][j] 为 a1以a1[i]结尾a2以a2[j]结尾时的最长公共子序列，则有
		 * if (a1[i] == a2[j]) {
		 * 	dp[i][j] = dp[i - 1][j - 1] + 1;
		 * } else {
		 * 	dp[i][j] = 0;
		 * }
		 */
	}
}
