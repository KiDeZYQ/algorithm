package com.rampage.algorithm.graph;

public class Point implements Comparable<Point> {
	int x;		     // 当前x坐标
	int y;		     // 当前y坐标
	int targeDistance;     // 当前点离终点距离  为了方便取距离的平方
	int sourceDistance;   // 当前点离		为了方便取距离的平方
	Point parent;				// 上一步节点
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Point o) {
		// 因为优先队列为最大堆，所以要使距离小的排在前面，就应该取other - this
		return (o.sourceDistance + o.targeDistance) - (this.sourceDistance + this.targeDistance);
	}
}