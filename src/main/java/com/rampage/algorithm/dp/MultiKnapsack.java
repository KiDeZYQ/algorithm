package com.rampage.algorithm.dp;

import java.util.Arrays;

/**
 * 多重背包问题：
 * 	每个物品可取的数量有另外一个数组控制，其实是01背包问题和完全背包问题的组合   
 * @author ziyuqi
 *
 */
public class MultiKnapsack {
	private static int[] weight = {1, 4, 3, 6, 8};
	private static int[] value =   {2, 6, 5, 11, 13};
	private static int[] count =   {6, 2, 4, 5, 5};
	private static final int capacity = 10;
	
	public static void main(String[] args) {
		/**
		 * 二维数组法
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
					int max = Math.min(j / curWeight, count[i - 1]);		// 得到当前重量最多放多少个当前物品
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
		 * 一维数组法： 可以转换为完全背包问题和01背包问题结合 降低时间复杂度
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];
		for (int i = 1; i <= len; i++) {
			int curWeight = weight[i - 1];
			if (curWeight * count[i - 1]  >= capacity) {
				for (int j=curWeight; j<=capacity; j++) {		// 正序遍历，此时就是完全背包问题
					dp[j] = Math.max(dp[j], dp[j - curWeight] + value[i - 1]);
	 			}
			} else {
				for (int j=curWeight; j<=capacity; j++) {		// 正序遍历，此时就是完全背包问题
					int k = 1;
					while (k <= count[i - 1] && k * curWeight < j) {
						dp[j] = Math.max(dp[j], dp[j - k*curWeight] + value[i - 1]*k);
						k++;
					}
	 			}
			}
		}
		System.out.println(Arrays.toString(dp));*/
	}
}
