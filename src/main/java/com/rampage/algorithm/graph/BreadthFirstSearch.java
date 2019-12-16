package com.rampage.algorithm.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 广度优先搜索
 * @author ziyuqi
 *
 */
public class BreadthFirstSearch {
	public static void main(String[] args) {
		// 采用邻接表的形式来表示图
		AdjacentArray[] graph = initGraph();
		
		// 从点0开始，通过广度优先搜索找到7的最短距离  广度优先第一次找到的即为最短距离
		breadthSearch(graph, 0, 7);
	}

	
	private static void breadthSearch(AdjacentArray[] graph, int startNode, int endNode) {
		boolean[] processedNode = new boolean[graph.length];		// 存储已经探索过的节点
		int[] prevNode = new int[graph.length];									// 存储当前节点的前置节点
		List<Integer> detectQueue = new ArrayList<>();
		
		/**
		 * 广度优先搜索思路：
		 * 	通过队列来实现，先进先出，先将步长一样的点入队列
		 */
		// 初始化状态
		detectQueue.add(startNode);		// 将初始节点放入探测队列中来
		Iterator<Integer> itr = null;
		int curNode = -1;
		List<AdjacentNode> adjacentNodes = null;
		boolean found = false;
		while ((itr = detectQueue.iterator()).hasNext()) {
			// 从队列中移除第一个节点开始进行探测
			curNode = itr.next();
			itr.remove();
			processedNode[curNode] = true;
			
			adjacentNodes = graph[curNode].ajPoints;
			if (adjacentNodes.isEmpty()) {
				continue;
			}
			for (AdjacentNode oneNode : adjacentNodes) {
				if (processedNode[oneNode.index] || detectQueue.contains(oneNode.index)) {
					continue;	// 已经处理过了或者在前面加入的点之间（说明这次处理到改点走的路比前面的长），不再处理
				}
				prevNode[oneNode.index] = curNode;
				if (oneNode.index == endNode) {
					found = true;
					break;			// 到达终点则跳过
				}
				detectQueue.add(oneNode.index);
			}
			
			if (found) {
				break;
			}
		}
		
		// 输出最短路径
		int index = endNode;
		while (index != 0) {
			System.out.print(" <--- " + index);
			index = prevNode[index];
		}
		System.out.print(" <--- 0");
	}


	public static AdjacentArray[] initGraph() {
		AdjacentArray[] graph = new AdjacentArray[8];	 	// 假设有一个8个顶点的无向图  具体图见README.md中附录 Undirected Graph1
		// 构造邻接表  无向图的邻接表和逆邻接表一样	
		graph[0] = new AdjacentArray();
		graph[0].ajPoints.add(new AdjacentNode(1));
		graph[0].ajPoints.add(new AdjacentNode(2));
		graph[1] = new AdjacentArray();
		graph[1].ajPoints.add(new AdjacentNode(0));
		graph[1].ajPoints.add(new AdjacentNode(2));
		graph[1].ajPoints.add(new AdjacentNode(3));
		graph[2] = new AdjacentArray();
		graph[2].ajPoints.add(new AdjacentNode(0));
		graph[2].ajPoints.add(new AdjacentNode(1));
		graph[2].ajPoints.add(new AdjacentNode(7));
		graph[3] = new AdjacentArray();
		graph[3].ajPoints.add(new AdjacentNode(1));
		graph[3].ajPoints.add(new AdjacentNode(5));
		graph[4] = new AdjacentArray();
		graph[4].ajPoints.add(new AdjacentNode(5));
		graph[4].ajPoints.add(new AdjacentNode(6));
		graph[5] = new AdjacentArray();
		graph[5].ajPoints.add(new AdjacentNode(3));
		graph[5].ajPoints.add(new AdjacentNode(4));
		graph[6] = new AdjacentArray();
		graph[6].ajPoints.add(new AdjacentNode(7));
		graph[6].ajPoints.add(new AdjacentNode(4));
		graph[7] = new AdjacentArray();
		graph[7].ajPoints.add(new AdjacentNode(6));
		graph[7].ajPoints.add(new AdjacentNode(2));
		return graph;
	}
}
