package com.rampage.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author ziyuqi
 * 最坏、平均 O(n*n)  最好 O(n)
 * 稳定
 * 空间复杂度 O(1)
 */
public class InsertionSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		insertionSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void insertionSort(int[] arr, int low, int high) {
		/**
		 * 排序思想： 将待排序的元素插入到已排序好的数组中
		 * [low, i) 是已排序好的
		 * [i, high) 是未排好序的
		 * i依次增大，直到high - 1
		 */
		if (low >= high - 1) {
			return;
		}
		int i = low + 1;
		int j = -1;
		int insertVal = -1;
		/*for (; i < high; i++) {
			insertVal = arr[i];
			j = i;
			while (j > 0 && arr[j - 1] >insertVal) {
				SortUtils.swap(arr, j, j - 1);
				j--;
			}
 		}*/
		
		// 优化版本，也是采用挖坑填坑法  当前待插入的元素作为坑，如果往左移发现有大于他的数则填坑，坑的位置左移 这样减少赋值  
		for (; i < high; i++) {
			insertVal = arr[i];
			j = i;
			while (j > 0 && arr[j - 1] >insertVal) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = insertVal;
 		}
	}
}
