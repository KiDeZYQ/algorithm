package com.rampage.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 最长递增子序列
 * @author ziyuqi
 *
 */
public class LongestIncrementSub {
	
	public static void main(String[] args) {
		int[] arr = {2, 1, 3, 7, 2, 3, 6, 5};
		
		/**
		 * 动态规划
		 */
		// 如果是连续的情况，该算法比较简单
		/*int len = arr.length;
		int[] dp = new int[len + 1];		// dp[i]表示以当前元素为结尾的最长递增子序列
		for (int i=1; i<=len; i++) {
			if (i == 1 || arr[i - 1] > arr[i - 2]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] = 0;   // 此时表示连续的最长递增子序列
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		// 如果是不连续的情况，则比较麻烦
		int len = arr.length;
		int[] dp = new int[len + 1];		// dp[i]表示以当前元素为结尾的最长递增子序列
		for (int i = 1; i <= len; i++) {
			dp[i] = 1;
		}
		int[] selected = new int[len+1];
 		for (int i=1; i<=len; i++) {
			for (int j=1; j<i; j++) {
				if (arr[i - 1] > arr[j - 1]) {
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
						selected[i] = 1;
					}
				} 
 			}
		}
		System.out.println(Arrays.toString(dp));
		System.out.println("\n\n");
		
		// 求得最长递增子序列的值
		Stack<Integer> subSequence = new Stack<>();
		int m = len;
		while (m > 0) {
			if (dp[m] > dp[m - 1] && (subSequence.isEmpty() || arr[m - 1] < subSequence.peek())) {
				subSequence.push(arr[m - 1]);
				m--;
			} else {
				if (subSequence.isEmpty() || arr[m - 1] < subSequence.peek()) {
					subSequence.push(arr[m - 1]);
				}
				m--;
			}
		}
		while (!subSequence.isEmpty()) {
			System.out.print(subSequence.pop() + " ---> ");
		}
   	}
}
