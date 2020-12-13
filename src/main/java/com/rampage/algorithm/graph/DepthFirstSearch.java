package com.rampage.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author ziyuqi
 * 深度优先搜索    不适合找最短路径，只适合来判断可达性
 *
 */
public class DepthFirstSearch {
	public static void main(String[] args) {
		// 采用邻接表的形式来表示图
		AdjacentArray[] graph = BreadthFirstSearch.initGraph();		// 这里和广度优先搜索共用一棵树
		
		// 从点0开始，通过深度优先搜索找到7的最短距离 
		depthSearch(graph, 0, 7);
	}

	private static void depthSearch(AdjacentArray[] graph, int startPoint, int endPoint) {
		/**
		 * 深度优先遍历的思路：
		 * 		通过栈的结构，先随机选择一个分支一路走到底，走不通了之后再逐个出栈，出栈之后如果有新的分支就走新的分支，没有新的分支则继续出栈，当
		 * 栈为空的时候表示都走完
		 */
		boolean[] processedNode = new boolean[graph.length];		// 存储已经探索过的节点
		int[] prevNode = new int[graph.length];				    // 存储当前节点的前置节点
		Stack<Integer> stack = new Stack<>();
		List<String> results = new ArrayList<>();				    // 用来存储结果
		
		// 初始化
		stack.push(startPoint);
		int curNode = -1;
		List<AdjacentNode> adjacentNodes = null;
		boolean backFlag = true;
		while (!stack.isEmpty()) {
			curNode = stack.peek();
			processedNode[curNode] = true;
			adjacentNodes = graph[curNode].ajPoints;
			if (adjacentNodes.isEmpty()) {
				continue;
			}
			backFlag = true;
			for (AdjacentNode oneNode : adjacentNodes) {
				if (oneNode.index == endPoint) {
					prevNode[oneNode.index] = curNode;
					// 拼接路径
					int index = endPoint;
					StringBuilder sb = new StringBuilder();
					while (index != 0) {
						sb.append(" <--- " + index);
						index = prevNode[index];
					}
					sb.append(" <--- 0");
					results.add(sb.toString());
					continue;
				}
				
				if (processedNode[oneNode.index] || stack.contains(oneNode.index)) {
					continue;
				}
				stack.push(oneNode.index);
				prevNode[oneNode.index] = curNode;
				backFlag = false;  					        // 找到后续节点，不需要回溯
				break;										// 这个是深度和广度的区别，深度找到一个就开始出栈试探，广度是找到所有周边
			}
			if (backFlag) {
				processedNode[stack.pop()] = true;		// 不再需要回溯到该点则该点出栈
			}
		}
		
		for (String path : results) {
			System.out.println(path);
		}
	}
}
