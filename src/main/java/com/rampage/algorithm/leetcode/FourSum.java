package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? Find all unique
 * quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * The solution set must not contain duplicate quadruplets.
 * 
 * Example:
 * 
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
 * 
 * @author kidezi
 *
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        /**
         * 该题是threeSum的加强版，解题思路应该一样 只不过原来只固定头元素，现在固定首尾元素
         */
        // STEP1: 排序数组
        Arrays.parallelSort(nums);
        
        // STEP2: 分别取第i（0到size - 4）和第j（size - 1到i+3）个元素作为首尾两个元素，然后去不停地逼近
        List<List<Integer>> results = new ArrayList<>();
        int lowIndex = -1;
        int highIndex = -1;
        for (int i = 0, len = nums.length; i <= len - 4; i++) {
            // 用来防重
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = len - 1; j >= i + 3; j--) {
                lowIndex = i + 1;
                highIndex = j - 1;
                // 用来防重
                if (j != len - 1 && nums[j] == nums[j + 1]) {
                    continue;
                }
                while (lowIndex < highIndex) {
                    if (nums[i] + nums[j] + nums[lowIndex] + nums[highIndex] > target) {
                        highIndex--;
                    } else if (nums[i] + nums[j] + nums[lowIndex] + nums[highIndex] < target) {
                        lowIndex++;
                    } else {
                        List<Integer> oneResult = new ArrayList<>();
                        oneResult.add(nums[i]);
                        oneResult.add(nums[lowIndex]);
                        oneResult.add(nums[highIndex]);
                        oneResult.add(nums[j]);
                        results.add(oneResult);
                        while (lowIndex < highIndex && nums[lowIndex] == nums[lowIndex + 1]) {
                            lowIndex++;
                        }
                        while (lowIndex < highIndex && nums[highIndex] == nums[highIndex - 1]) {
                            highIndex--;
                        }
                        lowIndex++;
                        highIndex--;
                    }
                }
            }
        }
        return results;
    }
    
    
    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        int[] arr = new int[] {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum.fourSum(arr, 0));
    }
}
