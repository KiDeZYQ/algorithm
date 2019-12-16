package com.rampage.algorithm.graph;

import java.util.Arrays;
import java.util.List;

/**
 * 迪杰斯特拉算法： 单源最短路径算法
 * 支持边为非负数权值的最短路径算法
 * @author ziyuqi
 *
 */
public class DijkstraSearch {
	
	public static void main(String[] args) {
		// 采用邻接表的形式来表示图
		AdjacentArray[] graph = initWeightGraph();		// 构建一个加权无向图
		
		// 从点0开始，通过深度优先搜索找到7的最短距离 
		dijkstraSearch(graph, 0, 7);
	}
	
	private static void dijkstraSearch(AdjacentArray[] graph, int start, int end) {
		/**
		 * 算法思想：
		 * 		首先构建一个数组distance，distance中存放起始点到各个点的直接距离（如果不能直接到达，则为正无穷大）
		 * 		同时有一个集合detected，集合表示探测过的中间点 一开始detected中只有起点
		 * 		随后依次从distance中选择另一个不在集合detected内且到起点距离最短的点，探测从此中间点到其他点的距离是否比当前distance表中的距离短
		 * 		如果短的话，则更新distance中起点到各个点之间的距离
		 * 		到detected中包含所有元素时终止探测
		 */
		// STEP1: 初始化distance数组
		int[] distance = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			if (i != start) {
				distance[i] = Integer.MAX_VALUE;
			}
		}
		List<AdjacentNode> adjecentNodes = graph[start].ajPoints;
		int[] preNode = new int[graph.length];		// 构造一个前置节点，用来回溯寻找最短路径
		for (AdjacentNode oneNode : adjecentNodes) {
			distance[oneNode.index] = oneNode.weight;
			preNode[oneNode.index] = start;
		}
		
		// STEP2: 初始化detected数组
		boolean[] detected = new boolean[graph.length];
		detected[start] = true;
		
		
		// STEP3: 整个dijkstra过程
		boolean found = true;
		int minDistance = Integer.MAX_VALUE;
		int minIndex = -1;
		List<AdjacentNode> detectAdjacentNodes = null;
		while (found) {
			found = false;
			minIndex = -1;
			minDistance = Integer.MAX_VALUE;
			for (int i = 1, len = graph.length; i < len; i++) {
				if (!detected[i] && distance[i] < minDistance) {
					minDistance = distance[i];
					minIndex = i;
					found = true;
				}
			} 
			if (!found) {
				break;
			}
			
			detected[minIndex] = true;
			detectAdjacentNodes = graph[minIndex].ajPoints;
			for (AdjacentNode oneNode : detectAdjacentNodes) {
				// 如果通过中间探测点到另一点的距离小于当前distance数组中的距离，则更新distance数组中的距离
				if (minDistance + oneNode.weight < distance[oneNode.index]) {
					distance[oneNode.index] = minDistance + oneNode.weight;
					preNode[oneNode.index] = minIndex;
				}
			}
		}
		 
		// STEP4: 输出起点到各个点的最短距离
		System.out.println(Arrays.toString(distance));
		// 输出前置数组
		System.out.println(Arrays.toString(preNode));
		// 输出0到7的最短路径
		int curIndex = end;
		while (curIndex != 0) {
			System.out.print(curIndex + "<---");
			curIndex = preNode[curIndex];
		}
		System.out.print(0);
	}
	

	public static AdjacentArray[] initWeightGraph() {
		AdjacentArray[] graph = new AdjacentArray[8];	 	// 假设有一个8个顶点的加权无向图  具体图见README.md中附录 Undirected Weighted Graph2
		// 构造邻接表  无向图的邻接表和逆邻接表一样	
		graph[0] = new AdjacentArray();
		graph[0].ajPoints.add(new AdjacentNode(1));
		graph[0].ajPoints.add(new AdjacentNode(2, 4));
		graph[1] = new AdjacentArray();
		graph[1].ajPoints.add(new AdjacentNode(0));
		graph[1].ajPoints.add(new AdjacentNode(2));
		graph[1].ajPoints.add(new AdjacentNode(3));
		graph[2] = new AdjacentArray();
		graph[2].ajPoints.add(new AdjacentNode(0, 4));
		graph[2].ajPoints.add(new AdjacentNode(1));
		graph[2].ajPoints.add(new AdjacentNode(7, 7));
		graph[3] = new AdjacentArray();
		graph[3].ajPoints.add(new AdjacentNode(1));
		graph[3].ajPoints.add(new AdjacentNode(5));
		graph[4] = new AdjacentArray();
		graph[4].ajPoints.add(new AdjacentNode(5));
		graph[4].ajPoints.add(new AdjacentNode(6, 2));
		graph[5] = new AdjacentArray();
		graph[5].ajPoints.add(new AdjacentNode(3));
		graph[5].ajPoints.add(new AdjacentNode(4));
		graph[6] = new AdjacentArray();
		graph[6].ajPoints.add(new AdjacentNode(7, 4));
		graph[6].ajPoints.add(new AdjacentNode(4, 2));
		graph[7] = new AdjacentArray();
		graph[7].ajPoints.add(new AdjacentNode(6, 4));
		graph[7].ajPoints.add(new AdjacentNode(2, 7));
		return graph;
	}
}
