package com.rampage.algorithm.graph;

/**
 * 邻接点
 * @author ziyuqi
 *
 */
public class AdjacentNode {
	int index;			// 表示第几个节点
	int weight = 1;		//  表示邻接表中初始点到该节点的距离，默认为不带权重的，即为1
	
	public AdjacentNode(int index) {
		this.index = index;
	}
	
	public AdjacentNode(int index, int weight) {
		this.index = index;
		this.weight = weight;
	}
}
