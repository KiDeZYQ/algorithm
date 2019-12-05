package com.rampage.algorithm.sort;

import java.util.Arrays;

import static com.rampage.algorithm.util.SortUtils.*;

/**
 * 快排
 * @author ziyuqi
 * 
 * 不稳定算法  最坏:O(n*n)  最快/平均 : O(nlog(n)) 空间复杂度：O(log(n))
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] arr = {1, -1, 3, 5, 4, 7, 0, 19, 1, 0, 5, 1};
		// twoSideSwitchSort(arr, 0, arr.length);			// 两端交换法
		// holeSort(arr, 0, arr.length);							// 挖坑填坑法
		// oneSideTwoScanSort(arr, 0, arr.length);			// 单端双扫描法	
		// threeDivideSort(arr, 0, arr.length);				// 三向切分法
		twoPivotSort(arr, 0, arr.length);						// 双轴快排
		System.out.println(Arrays.toString(arr));
	}

	private static void twoPivotSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
 		 /**
		  * 排序状态：
		  * 	a. (low, i) 小于 pivot1
		  * 	b. [i, k] 大于等于pivot1 小于等于pivot2
		  * 	c. (k, j) 不确定 
		  * 	d. [j, high) 大于pivot2
		  * 排序方法：
		  * 	k和j移动 取消不确定区间
		  * 注意点：
		  * 	排序的过程中 pivot1和pivot2的位置不动，这是为了最后进行位置替换
		  * 	后续需要将划分好的三个区域再分别进行快排操作
		  * 与单轴三段切分的区别在于一次可以确定两个节点，确定的两个节点不需要再次排序
		  */
		if (arr[low] > arr[high - 1]) {
			swap(arr, low, high - 1);
		}
		int pivot1 = arr[low];
		int pivot2 = arr[high - 1];
		int i = low + 1;
		int k =low + 1;
		int j = high - 2;
		while (k <= j) {
			if (arr[k] < pivot1) {
				swap(arr, i++, k++);
			} else if (arr[k] >= pivot1 && arr[k] <= pivot2) {
				k++; 
			} else {
				if (arr[j] > pivot2) {
					j--;
				} else if (arr[j] >= pivot1) {
					swap(arr, j--, k++);
				} else {
					swap(arr, j--, k);
					swap(arr, i++, k++);
				}
			}
		}
		// [i, k]  左右都固定的区间内值在pivot1和pivot2之间， 那么如果 i - 1 与 k + 1 分别放入pivot1 和 pivot2必然可以作为切分，所以此处有 i-- j++
		i--;
		j++;
		swap(arr, low, i);
		swap(arr, j, high - 1);
		twoPivotSort(arr, low, i);
		twoPivotSort(arr, i + 1, j);
		twoPivotSort(arr, j + 1, high);
		
	}

	private static void threeDivideSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
		/**
		 *  排序状态： 
		 * 		a. [low, i) 小于pivot 
		 * 		b. [i, k)  等于pivot
		 * 		c. [k, j]   不确定
		 * 		d. (j, high) 大于pivot
		 * 
		 * 	排序方法：
		 * 		k和j移动，消除不确定性
		 * 	注意点:
		 * 		最终 [i, k) 区间的可以不再进行排序，因为与pivot相等
		 */
		int pivot = arr[low];
		int i = low;
		int k = i + 1;
		int j = high - 1;
		while (k <= j) {
			if (arr[k] < pivot) {
				swap(arr, i++, k++);
			} else if (arr[k] == pivot) {
				k++;
			} else {
				if (arr[j] > pivot) {
					j--;
				} else if (arr[j] == pivot) {
					swap(arr, k++, j--);
				} else {
					swap(arr, k, j--);
					swap(arr, i++, k++);
				}
			}
		}
		
		threeDivideSort(arr, low, i);
		threeDivideSort(arr, k, high);
		
	}

	private static void oneSideTwoScanSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
		/**
		 *    排序状态：
		 *   	a.  [low, i] 小于等于pivot 
		 *    	b.  (i, k) 大于 pivot 
		 *     c.  [k, high)不确定  --- 
		 *    排序方法：
		 *    		k移动 消除不确定性  
		 *    注意点： 
		 *    	最后还需交换 pivot和i的值
		 */
		int pivot = arr[low];
		int i = low;
		int k = i + 1;
		while (k < high) {
			if (arr[k] > pivot) {
				k++;
			} else {
				i++;
				swap(arr, i, k);
				k++;
			}
		} 
		swap(arr, i, low);			// 这一步很关键，排序状态只能保证 [low, i] 的值小于等于pivot，但不能保证 [low, i)的值都小于arr[i] 所以此时还需交换pivot和arr[i]的值 
		oneSideTwoScanSort(arr, low, i);
		oneSideTwoScanSort(arr, i + 1, high);
	}

	private static void holeSort(int[] arr, int low, int high) {
		 if (low >= high - 1) {
			 return;
		 }
		 /**
		  *  排序状态： 
		  *  	a. [low, i] 一定小于等于pivot  
		  *  	b. (i, j] 不确定  
		  *  	c. (j, high)一定大于pivot
		  *  排序方法：
		  *  	i和j移动 消除不确定性
		  *  注意点:  
		  *  	最终需用pivot来填i坑
		  */
		 int pivot = arr[low];
		 int i = low;
		 int j = high - 1;
		 while (i < j) {
			 while (i < j && arr[j] > pivot) {
				 j--;
			 }
			 arr[i] = arr[j];
			 while (i < j && arr[i] <= pivot) {
				 i++;
			 }
			 arr[j] = arr[i];
		 }
		 arr[i] = pivot;
		 holeSort(arr, low, i);
		 holeSort(arr, i + 1, high);
	}

	private static void twoSideSwitchSort(int[] arr, int low, int high) {
		if (low >= high - 1) {
			return;
		}
		/**
		 *  排序状态： 
		 *  	a. [low, i) 一定小于等于pivot  
		 *  	b. [i, j] 不确定  
		 *  	c. (j, high) 一定大于pivot
		 *  排序方式： 
		 *  	i和j移动，消除不确定性
		 *  注意点： 
		 *  	最终需取j与pivot交换
		 */
		int pivot = arr[low];	
		int i = low + 1;
		int j = high - 1;
		while (i <= j) {
			while (i <= j && arr[i] <= pivot) {
				i++;
			}
			while (i <= j && arr[j] > pivot) {
				j--;
			}
			if (i < j) {
				swap(arr, i, j);
			}
 		}
		swap(arr, low, j);
		twoSideSwitchSort(arr, low, j);
		twoSideSwitchSort(arr, j + 1, high);
	}
}
