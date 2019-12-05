package com.rampage.algorithm.util;

/**
 * 排序工具类
 * @author ziyuqi
 *
 */
public class SortUtils {
	
	private SortUtils() {
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
