package com.rampage.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author ziyuqi
 * 
 * 最坏、最好、平均 都为 O(nlog(n))
 * 空间复杂度： O(n)
 *  稳定
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		mergeSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void mergeSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
		int mid = (low + high) >>> 1;
		mergeSort(arr, low, mid);
		mergeSort(arr, mid, high);
		
		// 如果前后数组已经是排序好的，不需要再做merge操作
		if (arr[mid - 1] < arr[mid]) {
			return;
		}
		
		// 新增一个中间数组，用来存储临时结果
		int[] dest = new int[high - low];
		for (int i = low,  j = mid, destIndex = 0; destIndex < high - low; destIndex++) {
			if (i >= mid || (j < high && arr[i] >= arr[j])) {
				dest[destIndex] = arr[j];
				j++;
			} else {
				dest[destIndex] = arr[i];
				i++;
			}
		}
		// 将排序好的中间数组拷贝至原数组
		System.arraycopy(dest, 0, arr, low, high - low);
	}
}
