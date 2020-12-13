package com.rampage.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers
 * in nums such that the sum is closest to target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * Example 1: Input: nums = [-1,2,1,-4], target = 1 Output: 2 Explanation: The
 * sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * @author kidezi
 *
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // STEP1: 先排序
        Arrays.sort(nums);
        
        // STEP2: 依次取第i个元素为三个元素中最开始的元素，所以后续两个元素都要从该元素右侧取
        int lowIndex = -1;
        int highIndex = -1;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int result = target;
        for (int i = 0, len = nums.length; i < len - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { // 去掉重复的元素
                continue;
            }
            lowIndex = i + 1;
            highIndex = len - 1;
            while (lowIndex < highIndex) {
                sum = nums[i] + nums[lowIndex] + nums[highIndex];
                if (sum  - target > 0) {
                    if (Math.abs(sum - target) < min) {
                        min = Math.abs(sum - target);
                        result = sum;
                    }
                    highIndex--;
                } else if (sum  - target < 0) {
                    if (Math.abs(sum - target) < min) {
                        min = Math.abs(sum - target);
                        result = sum;
                    }
                    lowIndex++;
                } else {
                    return target;
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {-1,2,1,-4};
        ThreeSumClosest sum = new ThreeSumClosest();
        System.out.println(sum.threeSumClosest(arr, 3));
    }
}
