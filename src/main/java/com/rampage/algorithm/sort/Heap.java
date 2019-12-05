package com.rampage.algorithm.sort;

import java.util.Arrays;


/**
 * 二叉堆： 用来作为堆排序的入门类
 * 
 * @author ziyuqi
 *
 */
public class Heap {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		buildMaxHeap(arr, 0, arr.length);
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(addValueToHeap(arr, 20)));
	}

	private static int[] addValueToHeap(int[] arr, int addVal) {
		if (arr == null || arr.length == 0) {
			return arr;
		}
		// 往heap中添加元素，相当于是先加在最后，然后将最后的元素上浮
		int[] result = new int[arr.length + 1];
		System.arraycopy(arr, 0, result, 0, arr.length);			// 将原数组拷贝进来
		result[result.length - 1] = addVal;									// 将待添加的元素放在最末尾
		shifUp(result, result.length - 1);
		return result;
	}

	/**
	 * 上浮节点
	 * @param arr 数组
	 * @param i  元素下标
	 */
	private static void shifUp(int[] arr, int i) {
		int temp = arr[i];
		int parentIndex = (i - 1) >>> 1;
		while (i >= 1) {
			if (arr[parentIndex] >= temp) {
				break;
			}
			arr[i] = arr[parentIndex];
			i = parentIndex;
			parentIndex = (i - 1) >>> 1;;
		}
		arr[i] = temp;
	}

	/**
	 * 构建最大堆
	 * 
	 * @param arr
	 */
	private static void buildMaxHeap(int[] arr, int low, int high) {
		/**
		 * 构建堆的过程： 从非叶子节点开始，依次从下至上下沉来实现
		 */
		for (int i = ((high - low) >>> 1); i >= low; i--) {
			shifDown(arr, i, high);
		}
	}

	private static void shifDown(int[] arr, int i, int len) {
		/**
		 * 下沉思想: 
		 * 		如果当前节点小于左子节点或者右子节点，则将当前节点下沉至一个更大的节点位置与该位置元素替换
		 */
		/*int curIndex = i; // 表示当前需要下沉节点的下标
		int leftChildIndex = (curIndex << 1) + 1;
		int rightChildIndex = leftChildIndex + 1;
		int switchToIndex = -1;
		while (leftChildIndex < len && rightChildIndex < len
		        && (arr[curIndex] < arr[leftChildIndex] || arr[curIndex] < arr[rightChildIndex])) {
			
			// 最大堆，需下沉到左右两个子节点中较大节点的位置，即与较大的节点进行交换,将较大的节点上浮，构建最大堆 
			switchToIndex = rightChildIndex;
			if (arr[leftChildIndex] > arr[rightChildIndex]) {
				switchToIndex = leftChildIndex;
			} 
			SortUtils.swap(arr, switchToIndex, curIndex);
			
			// 此时需要判断切换位置之后的当前节点是否还需要下沉
			curIndex = switchToIndex;
			leftChildIndex = (curIndex << 1) + 1;
			rightChildIndex = leftChildIndex + 1;
		}*/
		
		// 这个优化版的求出来的堆 利用挖坑法进行优化, 当前待下沉的节点（坑的值不变）挖坑，找到比他大的就来填坑  最后循环结束的话用最开始的待下沉的节点填坑
		int half = len >>> 1;        // loop while a non-leaf
		int temp = arr[i], curIndex = i;
        while (curIndex < half) {
            int child = (curIndex << 1) + 1; // assume left child is least
            int right = child + 1;
            if (right < len && arr[right] > arr[child]) {
            	child = right;
            }
            if (temp >= arr[child]) {			// 需要注意的是此处始终是temp的值
            	break;
            }
            arr[curIndex] = arr[child];
            curIndex = child;
        }
        arr[curIndex] = temp;
	}
}
