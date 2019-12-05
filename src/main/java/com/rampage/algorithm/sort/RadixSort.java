package com.rampage.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 	时间复杂度： 最快、最好、平均： O(d(r + n))，其中r为所采取的基数（计数数组的长度），而d为数字的最大位数
 * 空间复杂度： O(r + n)		--- 排序数组 + 临时数组
 * 稳定
 * @author ziyuqi
 *
 */
public class RadixSort {
	private static final int RADIX = 10;
	
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		radixSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void radixSort(int[] arr, int low, int high) {
		/**
		 * 排序思想，计数排序的增强版 
		 *   从个位数开始，根据数字大小，放入计数数组中
		 *   然后从计数数组取出数据更新原数组，再依次类推，直至某一位所有数字都为0
		 */
		// 为了防止负数导致的问题，这里也做range  先将所有数改成 原数 - 最小数
		int min =  arr[low];
		for (int i = low + 1; i < high; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		
		// 替换原数组中的值
		for (int i = low; i < high; i++) {
			arr[i] -= min;
		}
		
		// 循环遍历，从个位数开始，得到当前数值并做相应处理
		boolean existDigit = true;
		int[] count = new int[RADIX];		// 某一位数字只可能是0到9
		int digit = 0;		// 表示第几位，从0开始，从数字右侧开始
		int[] temp = new int[high - low]; 
		while (existDigit) {
			existDigit = false;
			// 累加计数
			for (int i = 1; i < RADIX; i++) {
				count[i] = 0;				// 这一不一定要做， 清零
			}
			
			for (int i = low; i <high; i++) {
				if (arr[i] >= Math.pow(RADIX, digit)) {		// 这里做个判断是为了终止循环，当所有数不超过10的n次方的时候，就不需要再循环了
					count[getDigitNumber(arr[i], digit)]++;
					existDigit = true;
				} else {
					count[0]++;			// 小于则证明该位肯定为0
				}
			}
			
			// 累加计数
			for (int i = 1; i < RADIX; i++) {
				count[i] += count[i - 1];
			}
			
			// 更新桶数据
			for (int i = high - 1; i >= low; i--) {
				temp[--count[getDigitNumber(arr[i], digit)]] = arr[i];
			}
			
			System.arraycopy(temp, 0, arr, low, temp.length);
			digit++;
			if (!existDigit) {
				break;
			}
		}
		
		// 最后还原arr中的值，加上min
		for (int i = low; i < high; i++) {
			arr[i] += min;
		}
	}

	private static int getDigitNumber(int val, int digit) {
		return (int) (val / Math.pow(RADIX, digit)) % RADIX;
	}
}
