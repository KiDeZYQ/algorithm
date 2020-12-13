package com.rampage.algorithm.array;

import java.util.Arrays;

/**
 * Description
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are zero-based.
 * Example1:
 * numbers=[2, 7, 11, 15], target=9
 * return [0, 1]
 * 
 * Example2:
 * numbers=[15, 2, 7, 11], target=9
 * return [1, 2]
 * 
 * Challenge
 * Either of the following solutions are acceptable:
 *
 * O(n) Space, O(nlogn) Time
 * O(n) Space, O(n) Time
 * 
 * 难点在于： 时间复杂度 
 * @author KiDe
 *
 */
public class TwoSum {
	public static void main(String[] args) {
		int[] arr = {1, 2, 7, 9, 8, 0};
		int target = 9;
		System.out.println(Arrays.toString(twoSumIndex(arr, target)));
	}

	private static long[] twoSumIndex(int[] arr, int target) {
		/**
		 * 先排序后双指针算法
		 */
		
		return null;
	}
}
