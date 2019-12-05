package com.rampage.algorithm.dp;

import java.util.Arrays;

/**
 * 买股票问题
 * 			给出一个股票价格数组，只能最多买卖一次，求最大收益是多少  买卖不能同一天
 * @author ziyuqi
 *
 */
public class BuyStock {
	
	public static void main(String[] args) {
		int[] price = {3, 2, 6, 1, 3, 4};
		if (price == null || price.length < 2) {
			System.out.println("0");		// 最大收益为0
			return;
		}
		
		/**
		 * 动态规划实现
		 * dp[i]表示在第i天卖出的最大收益
		 * 则有状态转移方程
		 * dp[i] = Math.max(dp[i], dp[j] + (price[i] - price[j])) (0<=j<i)
		 */
		/*int len = price.length;
		int[] dp = new int[len];		
		for (int i=1; i<len; i++) {
			for (int j=0; j<i; j++) {
				if (price[i] > price[j] && dp[i] < dp[j] + (price[i] - price[j])) {
					dp[i] = dp[j] + (price[i] - price[j]);
				}
				dp[i] = Math.max(dp[i], dp[j] + (price[i] - price[j]));
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		// 另一种优雅的方式实现
		/**
		 * 可以以日期作为x轴，股票价格作为y轴画一个直角坐标图，发现其实是一张折线图
		 * 而最大收益出现在折线波谷和波峰差值最大的时候
		 * 可以通过类似于双指针的算法来计算实现
		 * 时间复杂度为O(n) 空间复杂度为 O(1)
		 */
		/*int low = 0;		// 指向波谷
		int high = 1;		// 指向波峰
		int max = 0;
		while (high < price.length) {
			if (price[high] - price[high - 1] >= 0) {
				if (price[high] - price[low] >= max) {
					max = price[high] - price[low];
				}
				high++;
			} else {
				low = high++;		// 波谷节点移到当前波峰节点，波峰节点往前移动
			}
		}
		System.out.println(max);*/
 		
		// 扩展，假设交易次数无限制
		/**
		 * 动态规划版：
		 * 假设dp[i]表示第i天股票的最大收益 则有状态转移方程
		 * dp[i] = max(dp[j], dp[j] + price[i] - price[j]); (0<=j<i)   // 当前的最大值，为前面某一天
		 * 
		 * 空间复杂度为 O(n)
		 * 时间复杂度还是 O(n*n)
		 */
		/*int[] dp = new int[price.length];
		dp[0] = 0;
		dp[1] = 0;
		for (int i = 2; i<price.length; i++) {
			for (int j=0; j<i; j++) {
				dp[i] = Math.max(dp[j], dp[j] + price[i] - price[j]); 
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		/**
		 * 优雅算法扩展版：
		 * 	很简单，只要开始走下坡，则计算当前上坡的距离，然后将每个上坡距离累加即可
		 */
		int low = 0;		// 指向波谷
		int high = 1;		// 指向波峰
		int max = 0;
		while (high < price.length) {
			if (price[high] - price[high - 1] >= 0) {
				high++;
			} else {
				max += price[high - 1] - price[low];
				low = high++;		// 波谷节点移到当前波峰节点，波峰节点往前移动
			}
		}
		max = Math.max(max, max + price[high - 1] - price[low]);
		System.out.println(max);
	}
}
