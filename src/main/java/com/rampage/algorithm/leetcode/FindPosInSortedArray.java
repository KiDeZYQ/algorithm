package com.rampage.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?

 

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
 

Constraints:
    0 <= nums.length <= 105
    -109 <= nums[i] <= 109
    nums is a non-decreasing array.
    -109 <= target <= 109
 * @author kidezi
 *
 */
public class FindPosInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        /**
         * 考虑先通过二分法找到等于target的元素，然后再左右二分法找到同样数据
         */
        int[] result = new int[] {-1, -1};
        if (nums.length == 0 || (nums.length == 1 && nums[0] != target)) {
            return result;
        }
        
        int low = 0;
        int high = nums.length - 1;
        int medium = -1;
        while (high >= low) {
            medium = (high + low) >>> 1;
            if (nums[medium] == target) {
                break;
            }
            if (nums[medium] > target) {
                high = medium - 1;
            } else if (nums[medium] < target) {
                low = medium + 1;
            }
        }
        
        // 找到数组中某一个值等于target的下标后，再左右分别用二分查找法逼近找最小和最大下标
        if (high < low) {
            return result;
        }
        
        // 左侧找最小下标
        if (medium > 0) {
            low = 0;
            high = medium;
            medium = (low+high) >>> 1;
            while (medium > 0 && nums[medium] == target && high > low) {
                high = medium - 1;
                medium = (low+high) >>> 1;
            }
            
            while (nums[medium] != target) {
                medium++;
            }
            
            result[0] = medium;
        } else {
            result[0] = 0;
        }
        
        if (medium < nums.length - 1) {
            low = medium;
            high = nums.length - 1;
            medium = (low+high) >>> 1;
            while (nums[medium] == target && high > low) {
                low = medium + 1;
                medium = (low+high) >>> 1;
            }
            
            while (nums[medium] != target) {
                medium--;
            }
            
            result[1] = medium;
        } else {
            result[1] = nums.length - 1;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        FindPosInSortedArray fsa = new FindPosInSortedArray();
        System.out.println(Arrays.toString(fsa.searchRange(new int[] {1, 1, 2}, 1)));
    }
}
