package com.rampage.algorithm.graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A*寻路算法
 * 
 * @author ziyuqi
 *
 */
public class AStarSearch {

    public static void main(String[] args) {
        int[][] arr = { { 0, 0, 0, -1 }, { 0, 0, 0, 0 }, { 0, -1, 1, 0 }, { 0, 0, 0, 0 } };

        // 假设人当前在点(0, 0)处，已知终点在 (2, 2) 求最短路径
        Point startPoint = new Point(0, 0);
        Point endPoint = new Point(2, 2);
        startPoint.targeDistance = getDistance(startPoint, endPoint);
        doSearch(arr, startPoint, endPoint);

    }

    private static void doSearch(int[][] arr, Point startPoint, Point endPoint) {
        /**
         * A*寻路思想： 已知起点和终点，
         * 需要建立两个列表，selectablePoint和choosedPoint，分别存放还未探测可以用来作为下一次起点的记录以及已经探测过的点
         * 其中每个点有两个重要属性： targeDistance 当前点离目标点的距离 sourceDistance 当前点离初始点的距离
         * 每次选择 selectablePoint中 targeDistance + sourceDistance
         * 最小的点，直到selectablePoint为空或者找到目标节点终止
         * 
         * 这里为了简单起见， 在任何点只能够上下左右走 不能斜着走
         */
        PriorityQueue<Point> selectablePoint = new PriorityQueue<>();
        selectablePoint.offer(startPoint); // 可选节点中加入起始节点
        ArrayList<Point> choosedPoint = new ArrayList<>();
        Point curPoint = null;
        Point up = null;
        Point down = null;
        Point left = null;
        Point right = null;
        Point targetPoint = null;
        while (!selectablePoint.isEmpty()) {
            curPoint = selectablePoint.remove();
            // 上
            up = new Point(curPoint.x, curPoint.y - 1);
            // 下
            down = new Point(curPoint.x, curPoint.y + 1);
            // 左
            left = new Point(curPoint.x - 1, curPoint.y);
            // 右
            right = new Point(curPoint.x + 1, curPoint.y);

            // 判断是不是终点
            if (endPoint.equals(up)) {
                targetPoint = up;
                targetPoint.parent = curPoint;
                break;
            }
            if (endPoint.equals(down)) {
                targetPoint = down;
                targetPoint.parent = curPoint;
                break;
            }
            if (endPoint.equals(left)) {
                targetPoint = left;
                targetPoint.parent = curPoint;
                break;
            }
            if (endPoint.equals(right)) {
                targetPoint = right;
                targetPoint.parent = curPoint;
                break;
            }

            // 判断是否可选, 可选择加入列表
            if (selectablePoint(up, arr, choosedPoint, selectablePoint)) {
                up.parent = curPoint;
                up.sourceDistance = getDistance(up, startPoint);
                up.targeDistance = getDistance(up, endPoint);
                selectablePoint.add(up);
            }
            if (selectablePoint(down, arr, choosedPoint, selectablePoint)) {
                down.parent = curPoint;
                down.sourceDistance = getDistance(down, startPoint);
                down.targeDistance = getDistance(down, endPoint);
                selectablePoint.add(down);
            }
            if (selectablePoint(left, arr, choosedPoint, selectablePoint)) {
                left.parent = curPoint;
                left.sourceDistance = getDistance(left, startPoint);
                left.targeDistance = getDistance(left, endPoint);
                selectablePoint.add(left);
            }
            if (selectablePoint(right, arr, choosedPoint, selectablePoint)) {
                right.parent = curPoint;
                right.sourceDistance = getDistance(right, startPoint);
                right.targeDistance = getDistance(right, endPoint);
                selectablePoint.add(right);
            }
            choosedPoint.add(curPoint); // 加入到已探测列表
        }

        if (targetPoint == null) {
            System.out.println("起点无法到达终点！");
        } else {
            while (targetPoint != null) {
                System.out.print(String.format(" <--- (%s, %s)", targetPoint.x, targetPoint.y));
                targetPoint = targetPoint.parent;
            }
        }
    }

    private static boolean selectablePoint(Point point, int[][] arr, ArrayList<Point> choosedPoint, PriorityQueue<Point> selectablePoint) {
        return point.y >= 0 && point.y < arr.length && point.x >= 0 && point.x < arr[0].length && arr[point.y][point.x] != -1
                && !choosedPoint.contains(point) && !selectablePoint.contains(point); // 这个很关键，新的节点不应该再selectablePoint和choosedPoint集合内
    }

    private static int getDistance(Point startPoint, Point endPoint) {
        return (int) (Math.pow(Math.abs(startPoint.x - endPoint.x), 2) + Math.pow(Math.abs(startPoint.y - endPoint.y), 2));
    }
}
