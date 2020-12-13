package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * Example:
 * 
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 * 
 * @author kidezi
 *
 */
public class ThreeSum {
    
    public List<List<Integer>> threeSum(int[] nums) {
        // STEP1: 先排序
        Arrays.sort(nums);
        
        // STEP2: 依次取第i个元素为三个元素中最开始的元素，所以后续两个元素都要从该元素右侧取
        int lowIndex = -1;
        int highIndex = -1;
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0, len = nums.length; i < len - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) { // 去掉重复的元素
                continue;
            }
            lowIndex = i + 1;
            highIndex = len - 1;
            while (lowIndex < highIndex) {
                if (nums[i] + nums[lowIndex] + nums[highIndex] > 0) {
                    highIndex--;
                } else if (nums[i] + nums[lowIndex] + nums[highIndex] < 0) {
                    lowIndex++;
                } else {
                    List<Integer> newList = new ArrayList<>(3);
                    newList.add(nums[i]);
                    newList.add(nums[lowIndex]);
                    newList.add(nums[highIndex]);
                    results.add(newList);
                    // 这两个语句是防止重复添加
                    while (lowIndex < highIndex && nums[lowIndex] == nums[lowIndex+1]) {
                            lowIndex++;
                    }
                    while (lowIndex < highIndex && nums[highIndex] == nums[highIndex - 1]) {
                        highIndex--;
                    }
                    lowIndex++;
                    highIndex--; // 默认就将lowIndex往上加
                }
            }
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {0, 0, 0, 0, 0};
        ThreeSum sum = new ThreeSum();
        System.out.println(sum.threeSum(arr));
    }
}
 