package com.rampage.algorithm.stack;

import java.util.Stack;

/**
 * 最大条形图中矩形面积
 * 	数组中每个值表示条形图的高，条形图的宽都是1 求该统计图中最大的矩形面积
 * @author ziyuqi
 *
 */
public class MaxBarArea {
	public static void main(String[] args) {
		int[] arr = {2, 4, 3, 4, 5, 2, 2, 7, 8, 6};
		
		/**
		 * 利用栈  当栈为空或者当前元素大于等于栈顶元素时入栈，否则出栈并且校验最大值
		 */
		Stack<Integer> stack = new Stack<>();
		int maxArea = 0;
		int h = 0;
		for (int i = 0; i <= arr.length; i++) {
			h = (i == arr.length ? 0 : arr[i]);
			if (stack.isEmpty() || h >= arr[stack.peek()]) {
				stack.push(i);
			} else {
				int index = stack.pop();
				maxArea = Math.max(maxArea, arr[index] * (stack.isEmpty() ? i : i - 1 - stack.peek()));
				i--;
			}
		}
		System.out.println(maxArea);
	}
}
