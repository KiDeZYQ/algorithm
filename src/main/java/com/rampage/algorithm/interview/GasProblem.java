package com.rampage.algorithm.interview;

/**
 * 2019-12-06 头条面试问题：
 * 题目描述大致如下：
 * 		头条有很多基地，这些基地组成一个环形。现在有一辆车，该车可以装的油无上限。现有数组
 * gas = {1, 2, 3, 4, 5} 其中gas[i]表示基地i的油储备量。另有数组 
 * cost = {2, 3, 4, 4, 3} 其中cost[i]表示基地i到i+1的耗油量
 * 现要求从基地0开始找最开始的可以连续走完一圈的基地i，如果不存在，则输出-1
 * @author ziyuqi
 *
 */
public class GasProblem {
	public static void main(String[] args) {
		int[] gas = {1, 1, 4, 4, 6};
		int[] cost = {2, 3, 4, 4, 3};
		
		System.out.println(calStart(gas, cost));
		
	}

	private static int calStart(int[] gas, int[] cost) {
		int leftGas = 0;								// 剩余gas
		int detectedStart = 0;					// 检测开始的元素下标
		boolean newTurn = false;			// 是否新的一轮探测
		for (int i = detectedStart, len = gas.length; i < len; ) {
			if (leftGas + gas[i] >= cost[i]) {
				leftGas += gas[i] - cost[i];
				i++;
				newTurn = false;
			} else {
				// 燃料不够，则表示要开始新的一轮 下一个检测起点加1
				i = ++detectedStart;
				leftGas = 0;
				newTurn = true;
			}
			
			// 如果检测起点变成了与数组长度一致，则表示探测完成未找到合适的点，直接返回-1
			if (detectedStart == len) {
				return -1;
			}
			
			// 环形结构，到达末尾了之后需要重新从0开始
			if (i == len) {
				i = 0;
			}
			
			// 不是新的一轮开始且下一个探测点等于这一轮探测的起始点，则代表符合要求，返回探测起始点即可
			if (!newTurn && i == detectedStart) {
				return detectedStart;
			}
		}
		
		return -1;
	}
}
