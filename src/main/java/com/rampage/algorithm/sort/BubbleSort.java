package com.rampage.algorithm.sort;

import java.util.Arrays;

import com.rampage.algorithm.util.SortUtils;

/**
 * 冒泡排序
 * @author ziyuqi
 * 平均、最坏： O(n*n) 最好 O(n)： 使用优化的标志位来标识是否已经排好序，如果不使用优化的标志位，最好的情况下也是 O(n*n)
 * 稳定
 * 空间复杂度 O(1)
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		bubbleSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void bubbleSort(int[] arr, int low, int high) {
		/**
		 * 排序思想：
		 *  从底至上或者从上至底依次比较两个元素，如果需要换位则交换两个元素
		 *  [low, i] 已排序好的
		 *  (i, j] 未排序好的
		 */
		if (low >= high - 1) {
			return ;
		}
		int i = 0, j = -1;
		
		for (boolean sorted = true; i < high; i++) {
			j = high - 1;
			for (; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					SortUtils.swap(arr, j, j - 1);
					sorted = false;
				}
			}
			if (sorted) {
				break;
			}
 		}
	}
}
