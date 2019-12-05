package com.rampage.algorithm.sort;

import java.util.Arrays;

/**
 *  计数排序  
 *   时间复杂度  O(n+k)   k为用来存储元素出现次数的数组大小
 *   空间复杂度  O(n+k) 
 *   稳定性： 优化后的计数排序可以保证相同元素的出现顺序与原数组中顺序一致，是稳定的
 *   
 *   适应范围: 1. 数组元素都是整数 2. 最小值和最大值相差不大
 * @author ziyuqi
 *
 */
public class CountSort {
	/**
	 * 最大值和最小值相差的值
	 */
	private static final int MAX_RANGE_THREADHOLD = 100000;
	
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		countSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void countSort(int[] arr, int low, int high) {
		// 先找出最大值和最小值，以及两者的差值。如果差值大于阈值，则表示不适用计数排序
		if (low >= high  - 1) {
			return;
		}
		int min, max;
		min = max = arr[low];
		for (int i = low + 1; i < high; i++) {
			if (arr[i] < min) {
				min = arr[i];
			} else if (arr[i] > max) {
				max = arr[i];
			}
		}
		int range = max - min;
		if (range > MAX_RANGE_THREADHOLD) {
			throw new IllegalArgumentException("Range:" + range + " is large than:" + MAX_RANGE_THREADHOLD);
		}
		
		// 创建计数数组， 一旦命中，计数数组计数加1
		int[] count = new int[range + 1];		// 这里需要注意为 range + 1
		for (int i = low; i < high; i++) {
			count[(arr[i] - min)]++;
		}
		
		// 右侧计数累加左侧计数的值，最终计数表示命中数所在原数组的下标 --- 这个是为了实现稳定性问题
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		
		// 倒序原数组，命中计数数组的值表示所在原数组的位置
		int[] temp = new int[high - low];
		for (int i = high - 1; i >= low; i--) {
			temp[--count[(arr[i] - min)]] = arr[i];		// 这里需要注意在temp中的下标为 count的值减一
			// 此处也可以通过swap的方式进行值的交换，防止创建新的数组，这是空间复杂度降到 O(m)
		}
		
		// 拷贝临时数组到原数组
		System.arraycopy(temp, 0, arr, low, temp.length);
 	}
}
