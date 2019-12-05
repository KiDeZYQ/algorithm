package com.rampage.algorithm.sort;

import java.util.Arrays;

import com.rampage.algorithm.util.SortUtils;

/**
 * 选择排序
 * @author ziyuqi
 * 最坏、平均、最好 O(n*n) 
 * 不稳定
 *	空间复杂度 O(1) 
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		selectionSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void selectionSort(int[] arr, int low, int high) {
		/**
		 * 选择排序算法思想: 
		 * 每次选择最大或者最小的，放入数组头或者尾
		 * [low, i] 排好序
		 * (i, j] 未排好序
		 */
		if (low >= high - 1) {
			return;
		}
		int i = low;
		int j = -1;
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (; i < high; i++) {
			min = arr[i];
			j = high - 1;
			for (; j > i && j < high; j-- ) {
				if (arr[j] < min) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (min != arr[i]) {
				SortUtils.swap(arr, i, minIndex);
			}
		}
	}
}
