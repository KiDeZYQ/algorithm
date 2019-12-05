package com.rampage.algorithm.dp;

import java.util.Arrays;

/**
 * 0/1 背包问题：
 * 		物品可以取或者不取，即物品的数量在0和1之间
 * @author ziyuqi
 *
 */
public class ZeroOneKnapsack {
	
	private static int[] weight = {1, 4, 3, 6, 8};
	private static int[] value =   {1, 6, 5, 11, 16};
	private static final int capacity = 10;
	
	public static void main(String[] args) {
		/**
		 * 01背包问题描述： 已知有一堆物品，每个物品的重量存储在数组 weight 中
		 * 每个物品的价值存储在数组value中，现在每个物品只有一件，背包容量为10 
		 * 请问如何取物品才能使背包最大，最大值为多少
		 */
		
		/**
		 * 基本思想： 首先确定要求的结果： 最大价值
		 * 				以及影响结果的两个变量： 物品重量和背包的容量   
		 * 	建模： 根据变量建立dp数组，其中物品重量不好作为一维，而物品的重量可以根据第几个物品来得到，所以第一维设为到此处理过的物品下标
		 * 			而第二维就直接设为迄今为止可用的背包容量
		 * 			而dp数组的具体值就表示当前的最大价值
		 * 			不难得到状态转移方程 
		 * 			dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])    也就是如果在上一个物品处理完之后的背包还能放下当前物品，则最大值为上次物品处理完最大值+本次处理物品最大中
		 * 			如果放不下，则当前最大值就为上次处理完物品的最大值
		 */
		int len = weight.length;
		int[][] dp = new int[len+1][capacity + 1];
		int[][] selected = new int[len + 1][capacity + 1];
		for (int i=1; i<=len; i++) {
			int curWeight = weight[i - 1];		// 当前物品的重量
			for (int j = 1; j <= capacity; j++) {	
				// 背包容量大于当前物品重量的时候，才要考虑是否丢弃一件物品放入当前物品 如果放入当前物品后价值更大，则表示当前物品的最大容量需要更新
				if (j >= curWeight && (dp[i - 1][j - curWeight] + value[i - 1]) > dp[i - 1][j]) {		
					dp[i][j] = dp[i - 1][j - curWeight] + value[i - 1];
					selected[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		// 打印最终的数组
		for (int i = 0; i <= len; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		System.out.println("\n\n");
		// 回溯法求得放了什么东西
		int m = weight.length;
		int n = capacity;
		while (m > 0 && n >0) {
			if (selected[m][n] == 1) {
				System.out.println(weight[m - 1] + "---->");
				n -= weight[--m];
			} else {
				m--;
			}
		}
		
		// 另一种不需要借助外部存储找到放了什么物品的方法
		int[] putIndex = new int[len + 1]; 
		int curCapacity = capacity;		// 存储当前容量
		for (int i = len; i >= 1; i--) {
			if (dp[i][curCapacity] != dp[i - 1][curCapacity]) {
				putIndex[i] = 1;						// 当前元素放入背包，将putIndex的值置为1
				curCapacity -= weight[i - 1];		// 命中了之后当前容量需要减一
			}
		}
		// System.out.println(Arrays.toString(putIndex));		// 可以看到放入的是第三个和第五个背包
		
		
		// 优化版： 将二维数组转化为1维数组
		/**
		 * 优化思想： 
		 * 		原来的二维数组有很大空间是浪费的 导致原来算法的时间和空间复杂度都是 len*capacity
		 * 发现原来的二维数组其实只有最后一行数据是有效的，所以我们可以只设置一个长度为最大容量的一维数组即可
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];
		int curWeight = Integer.MAX_VALUE;
		for (int i=1; i<=len; i++) {
			curWeight = weight[i-1];
			// 需要注意这里应该为逆序，如果正序的话就变成了完全背包问题
			for (int j=capacity; j>=curWeight; j--) {
				dp[j] = Math.max(dp[j - curWeight] + value[i - 1],  dp[j]);
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		// 01背包问题的变种
		// 1. 背包能不能被装满/背包能装的最大重量:  将原来的int数组改成boolean数组，判断最后一个dp数组的值是否是true
		/*int len = weight.length;
		boolean[] dp = new boolean[capacity + 1];		// 表示正好可以装到该重量
		dp[0] = true;														// 啥都不装的时候重量为0
		int curWeight = Integer.MAX_VALUE;
		for (int i=1; i<=len; i++) {
			curWeight = weight[i-1];
			// 需要注意这里应该为逆序，如果正序的话就变成了完全背包问题
			for (int j=capacity; j>=curWeight; j--) {
				if (dp[j - curWeight]) {
					dp[j] = true;
				}
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		// 2. 要求恰好装满背包时的最优解
		/**
		 * 思路：
		 * 	此时与01背包问题的不同之处在于要求恰好能装满，需要将dp[1]以后的值设为 Integer.MIN_VALUE
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];
		for (int i=1; i<=capacity; i++) {
			dp[i] = Integer.MIN_VALUE;
		}
		int curWeight = Integer.MIN_VALUE;
		for (int i=1; i<=len; i++) {
			curWeight = weight[i-1];
			// 需要注意这里应该为逆序，如果正序的话就变成了完全背包问题
			for (int j=capacity; j>=curWeight; j--) {
				dp[j] = Math.max(dp[j], dp[j - curWeight] + value[i - 1]);
			}
		}
		System.out.println(Arrays.toString(dp));*/
		
		// 3. 共有多少种方式塞满背包
		/**
		 * 将dp数组表示含义改为背包被填充的个数
		 */
		/*int len = weight.length;
		int[] dp = new int[capacity + 1];	// 背包被填充的个数
		for (int i=1; i<=capacity; i++) {
			dp[i] = Integer.MIN_VALUE;
		}
		int curWeight = Integer.MIN_VALUE;
		for (int i=1; i<=len; i++) {
			curWeight = weight[i-1];
			
  			// 需要注意这里应该为逆序，如果正序的话就变成了完全背包问题
			for (int j=capacity; j>=curWeight; j--) {
				if (dp[j - curWeight] >= 0) {
					dp[j] = dp[j] < 0 ? 1 : dp[j] + 1;		// 这个很关键
				}
			}
		}
		System.out.println(Arrays.toString(dp));*/
	}
}
