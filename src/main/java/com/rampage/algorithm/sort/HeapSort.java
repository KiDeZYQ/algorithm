package com.rampage.algorithm.sort;

import java.util.Arrays;

import com.rampage.algorithm.util.SortUtils;

/**
 * 堆排序
 *  * 最坏、最好、平均 都为 O(nlog(n))
 * 空间复杂度： O(1)
 *  不稳定
 * @author ziyuqi
 * 
 *  需要知道 什么是二叉堆  --- 也是一种树形结构，与二叉搜索树的不同之处在于，
 *  1. 二叉搜索树的根节点大于左子树所有节点，小于右子树的所有节点  
 *    		而二叉堆分为大堆和小堆， 大堆表示根节点大于所有子树节点，小堆表示根节点小于所有子树节点
 *  2. 二叉搜素树采用链式结构存储，而二叉堆一般采用顺序存储（数组存储）
 *  二叉堆的特性：
 *  	添加节点： 默认先直接添加到二叉树的最后一个位置，然后通过上浮使得二叉堆平衡
 *  	删除节点： 与添加正好相反，先取二叉树的最后一个节点替换删除的节点位置，然后通过下沉使得二叉堆平衡
 *  	构建二叉堆： 让所有非叶子节点下沉
 *  	二叉堆
 *
 */
public class HeapSort {
	
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		heapSort(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
	}

	private static void heapSort(int[] arr, int low, int high) {
		/**
		 * 排序思想： 
		 * 		类似于优先队列的出队顺序，先将整个数组构建一个最大堆。
		 * 然后依次交换堆顶和未排序部分最后一个元素，然后下层堆顶使其成为新的最大堆
		 * 直到最大堆只有两个元素为止
		 * 		第一次构建完最大堆之后
		 * 		(low, j] 表示未排序的部分
		 * 		 通过j不断左移实现未排序区间逐渐减少
		 */
		if (low >= high - 1) {
			return;
		}
		buildMaxHeap(arr, low, high);			// 构建最小堆
		System.out.println("Max heap is:" + Arrays.toString(arr));
		
		for (int j = high - 1; j > low; j--) {
			// 交换堆顶元素和未排序数组的最后一个元素
			SortUtils.swap(arr, low, j);
			downAjust(arr, low, j);		
		}
	}

	private static void buildMaxHeap(int[] arr, int low, int high) {
		for (int i = (high - low) >>> 1; i >= 0; i--) {
			downAjust(arr, i, high);
		}
	}

	private static void downAjust(int[] arr, int i, int len) {
		int curIndex = i;
		int leftChildIndex = (curIndex << 1) + 1;
		int rightChildIndex = (curIndex << 1) + 2;
		int switchIndex = - 1;
		while ((leftChildIndex < len && rightChildIndex < len)
		        && (arr[curIndex] < arr[leftChildIndex] || arr[curIndex] < arr[rightChildIndex])) {
			switchIndex = leftChildIndex;
			if (arr[rightChildIndex] > arr[leftChildIndex]) {
				switchIndex = rightChildIndex;
			}
			SortUtils.swap(arr, curIndex, switchIndex);
			curIndex = switchIndex;
			leftChildIndex = (curIndex << 1) + 1;
			rightChildIndex = (curIndex << 1) + 2;
 		}
	}
}
