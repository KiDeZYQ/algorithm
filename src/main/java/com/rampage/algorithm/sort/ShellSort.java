package com.rampage.algorithm.sort;

import java.util.Arrays;

/**
 * 希尔排序： 
 * 	插入排序的优化版，每次采用不同步长将数组分割然后分别用插入排序
 * 时间复杂度： O(nlogn)
 * 稳定算法
 * @author ziyuqi
 * 
 *  还有一个binarySearchSort 其实也是插入排序的优化版，可以减少插入排序时的查找次数，但是元素的移动次数不会变
 *
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		shellSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void shellSort(int[] arr, int low, int high) {
		/**
		 * 排序思想：
		 * 		优化版的插入排序，每次采用不同的步长将数组分割，然后将各个子数组用插入排序
		 * 		接着再缩短步长，再次进行插入排序，直至步长为1，数组排序完成  
		 */
		if (low >= high - 1) {
			return;
		}
		int step = (high - low) >>> 1;
		int insertVal = -1;
		while (step > 0) {
			for (int i = low + step; i < high; i++) {
				int j = i;
				insertVal = arr[j];
				while (j >= step && insertVal < arr[j - step]) {
					arr[j] = arr[j - step];
					j = j - step;
				}
				arr[j] = insertVal;
			}
			step >>>= 1;		// 步长缩减
		}
	}
}
