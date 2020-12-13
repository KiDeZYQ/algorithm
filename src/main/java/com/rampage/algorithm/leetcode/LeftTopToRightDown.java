package com.rampage.algorithm.leetcode;

/**
 * 给定一个非负整数的m x n网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和值最小。
 * 说明： 每次只能向下或者向右移动一步
 * 
 * 示例：
 * 输入：
 * [
 *  [1, 3, 1],
 *  [1, 5, 1],
 *  [4, 2, 1]
 * ]
 * 输出：
 *  7
 * 解释： 路径 1->3->1->1->1的总和最小
 * 要求用代码实现，并说明时间复杂度和空间复杂度
 * @author kidezi
 *
 */
public class LeftTopToRightDown {
    public int minLen(int[][] path) {
        if (path == null || path.length == 0) {
            return 0;
        }
        /**
         * 采用动态规划来实现：
         *  最优子结构：假设dp[i][j] 表示到达点path[i][j]的最小路径长度
         *  状态转移方程：其实dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + path[i][j];
         *  
         */
        int [][]dp = new int[path.length][path[0].length];
        /**
         * 状态初始化：
         * 第一行的最小距离就是当前值本身
         * 第一列的最小距离就是该上面的值的累加和
         */
        for (int i=0; i<path.length; i++) {
            dp[i][0] = path[i][0];
        }
        for (int i=0; i<path[0].length; i++) {
            for (int j=0; j<=i; j++) {
                dp[0][i] += dp[0][j];
            }
        }
        for (int i=1; i<path.length; i++) {
            for (int j=1; j<path[0].length; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + path[i][j];
            }
        }
        return dp[path.length - 1][path[0].length -1];
    }
    
    public static void main(String[] args) {
        LeftTopToRightDown left2Right = new LeftTopToRightDown();
        int [][] path = new int[][] {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 1}
        };
        System.out.println(left2Right.minLen(path));
    }
}
