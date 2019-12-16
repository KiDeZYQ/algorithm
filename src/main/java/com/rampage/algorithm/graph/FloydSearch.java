package com.rampage.algorithm.graph;

import java.util.Arrays;
import java.util.List;

/**
 * Floyd算法
 * @author ziyuqi
 * 一种多源加权最短路径算法 支持边的权值为负数
 *
 */
public class FloydSearch {
	
	public static void main(String[] args) {
		// 采用邻接表的形式来表示图
		AdjacentArray[] graph = DijkstraSearch.initWeightGraph();		// 构建一个加权无向图
		
		floydSearch(graph);
	}

	private static void floydSearch(AdjacentArray[] graph) {
		/**
		 * 动态规划的思想： 
		 * 		对于无向图，直接构造一个二维数组 distance，其中distance[i][j]表示i到j的最短距离
		 * 		那么存在状态转移方程:
		 * 			distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]) (0 <= k <= graph.length)
		 */
		// STEP1： 初始化distance数组
		int[][] distance = new int[graph.length][graph.length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (i == j) {
					distance[i][j] = 0;
				} else {
					distance[i][j] = Integer.MAX_VALUE;
				}
			}
 		}
		List<AdjacentNode> adjecentNodes = null;
		for (int i = 0; i < graph.length; i++) {
			adjecentNodes = graph[i].ajPoints;
			for (AdjacentNode oneNode : adjecentNodes) {
				distance[i][oneNode.index] = oneNode.weight;
			}
 		}
		for (int i = 0; i < graph.length; i++) {
			System.out.println(Arrays.toString(distance[i]));
		}
		
		System.out.println();
		// STEP2: floyd算法的核心部分 --- 让人崩溃的三次方时间复杂度
		for (int k = 0, len = graph.length; k < len; k++) {			// 需要注意k在最外层
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (distance[i][k] < Integer.MAX_VALUE && distance[k][j] < Integer.MAX_VALUE
					        && (distance[i][j] > distance[i][k] + distance[k][j])) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
		// STEP3: 输出最后的矩阵
		for (int i = 0; i < graph.length; i++) {
			System.out.println(Arrays.toString(distance[i]));
		}
 	}
}
