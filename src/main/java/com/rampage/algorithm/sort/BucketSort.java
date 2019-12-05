package com.rampage.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序： 对计数排序做了增强，原理与计数排序有点类似，结构类似于HashMap的低版本结构，数组加链表
 * 时间复杂度：  最坏情况，所有数据集中到一个桶，最终复杂度就是归并排序复杂度 最好情况和平均情况 差不多均匀分布 则为 m *((n/m)long(n/m)) = nlog(n/m)
 * 空间复杂度：O(m)
 * 稳定排序
 * @author ziyuqi
 *		相比于计数排序的优势在于：可以排序浮点型数据  每一个桶中的数组单独排序，排序之后再按照bucket依次输出即可
 *		不足之处在于 如果数据全部集中在一个桶中，那么其实就相当于
 */
public class BucketSort {
	
	private static final int EACH_BUCKET_SIZE = 10;
	
	public static void main(String[] args) {
		int[] arr = {1, -1, 13, 5, 14, 17, 10, 19, 1, 0, 5, 1};
		bucketSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void bucketSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
		
		/**
		 * 排序思想：
		 * 	将大化小，桶整体从小到大，桶中元素通过归并排序也从小到大，依次遍历桶和桶中元素，即可得最终顺序  
		 */
		
		// 得出最大和最小值
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
		
		// 创建桶数组  
		Bucket[] buckets = new Bucket[range/EACH_BUCKET_SIZE];
		int j = min / EACH_BUCKET_SIZE;			// 从最小的元素开始创建桶
		for (int i = 0, len = buckets.length; i < len; i++) {
			buckets[i] = new Bucket(j++);
		}
		
		// 遍历数组，将元素放入桶中
		for (int i = low; i < high; i++) {
			for (Bucket oneBucket : buckets) {
				if (oneBucket.add(arr[i])) {
					break;
				}
			}
		}
		
		// 依次调用每个桶排序
		for (Bucket oneBucket : buckets) {
			oneBucket.sort();
		}
		
		// 将桶中元素复制到数组中来
		int i = 0;
		for (Bucket oneBucket : buckets) {
			for (int oneEle : oneBucket.getElements()) {
				arr[i++] = oneEle;
			}
		}
	} 

	/**
	 * 桶， 元素该放入哪个桶中有两个思路 一个是范围  一个是hash/除数 当然取模这些都可以
	 * @author ziyuqi
	 *
	 */
	private static class Bucket {
		/**
		 * 桶中的元素
		 */
		private List<Integer> elements;
		
		private int divisor;
		
		Bucket(int divisor) {
			this.divisor = divisor;
			this.elements = new ArrayList<Integer>();
		}

		public boolean add(int element) {
			if (element / EACH_BUCKET_SIZE == this.divisor) {
				this.elements.add(element);
				return true;
			}
			return false;
		}
		
		public void sort() {
			if (this.elements.isEmpty()) {
				return;
			}
			// Collections.sort(elements);
			// 为了配合前面自己写的归并排序，这里换做调用自己的 --- 其实Collections.sort底层也是做了归并排序
			int[] eleArr = new int[this.elements.size()];
			for (int i = 0, len = eleArr.length; i < len; i++) {
				eleArr[i] = this.elements.get(i);
			}
			MergeSort.mergeSort(eleArr, 0, eleArr.length);
			
			elements.clear();
			for (int oneEle : eleArr) {
				elements.add(oneEle);
			}
		}
		
		public List<Integer> getElements() {
			return elements;
		}
	}
}
