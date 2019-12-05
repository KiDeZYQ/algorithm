package com.rampage.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 完全背包问题：
 * 		物品数量可以选择0或者n
 * @author ziyuqi
 *
 */
public class CompleteKnapsack {
	private static int[] weight = {1, 4, 3, 6, 8};
	private static int[] value =   {1, 6, 5, 11, 16};
	private static final int capacity = 10;
	
	public static void main(String[] args) {
		/**
		 * 最原始的二维数组法
		 */
		int len = weight.length;
		int[][] dp = new int[len + 1][capacity + 1];
		int[][] selected = new int[len + 1][capacity + 1];
		for (int i=1; i<=len; i++) {
			for (int j=1; j<=capacity; j++) {
				int curWeight = weight[i - 1];
				if (j < curWeight) {
					dp[i][j] = dp[i - 1][j];
				} else {
					int max = j / curWeight;		// 得到当前重量最多放多少个当前物品
					for (int k=1; k<=max; k++) {
						if (dp[i - 1][j - curWeight *k] + value[i - 1]*k > dp[i - 1][j]) {
							dp[i][j] = dp[i - 1][j - curWeight *k] + value[i - 1]*k;
							selected[i][j]++;
						} else {
							dp[i][j] = dp[i - 1][j];
						}
					}
				}
			}
  		}
		for (int i=0; i<=len; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		
		// 回溯法输出放入物品的重量
		int m = weight.length;
		int n = capacity;
		while (m > 0 && n >0) {
			if (selected[m][n] > 0) {
				// 输出放入物品的重量以及个数（个数输出在括号内）
				System.out.print(weight[m - 1] + "(" + selected[m][n] + ")  ");
				n -= weight[--m];
			} else {
				m--;
			}
		}
		
		/**
		 * 一维数组法: 时间复杂度 i*j*k
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];
		for (int i = 1; i <= len; i++) {
			int curWeight = weight[i - 1];
			for (int j=curWeight; j<=capacity; j++) {
				int k = 1;
				while (k * curWeight <= j) {
					dp[j] = Math.max(dp[j], dp[j - k*curWeight] + value[i - 1]*k);
					k++;
				}
 			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		/**
		 * 一维数组法； 进一步优化，时间复杂度降为 i * j
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];
		for (int i = 1; i <= len; i++) {
			int curWeight = weight[i - 1];
			for (int j=curWeight; j<=capacity; j++) {		// 正序遍历，此时就是完全背包问题
				dp[j] = Math.max(dp[j], dp[j - curWeight] + value[i - 1]);
 			}
		}
		System.out.println(Arrays.toString(dp));*/
	}
	
}
