package com.rampage.algorithm.leetcode;

/**
 *  You are given an integer array nums sorted in ascending order, and an integer target.
    Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
    If target is found in the array return its index, otherwise, return -1.

Example 1:
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4

Example 2:
    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1

Example 3:
    Input: nums = [1], target = 0
    Output: -1
 

Constraints:
    1 <= nums.length <= 5000
    -10^4 <= nums[i] <= 10^4
    All values of nums are unique.
    nums is guranteed to be rotated at some pivot.
    -10^4 <= target <= 10^4 
 * @author kidezi
 *
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        /**
         * 其实就是从旋转后的数组中找到对应的数字下标
         * 最简单的方法 就是 直接从前向后遍历数组，这样的时间复杂度必然是O(n)
         * 当然我们知道二分查找法能够大大缩短查找时间 所以关键要素在于寻找旋转点
         *  旋转点的一个特色在于该点左侧数据从小到大依次递增，右侧数据也从小到大依次递增
         *  可以考虑用二分法来找旋转点
         */
        if (nums.length == 0 || (nums.length == 1 && nums[0] != target)) {
            return -1;
        }
        
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high) >>> 1;
        int rotateIndex = 0;
        while (high >= low) {
            mid = (low + high) >>> 1;
            if (nums[mid] >= nums[0]) {
                low = mid + 1;
            } else {
                if ((mid - 1) >= 0 && nums[mid] >= nums[mid - 1]) {
                    high = mid - 1;
                } else {
                    rotateIndex = mid;
                    break;
                }
            }
        }
        System.out.println("rotate index:" + rotateIndex);
        /**
         * 找到旋转点之后可以根据target的值来决定在旋转点哪边找
         */
        int index = searchTarget(nums, rotateIndex, nums.length - 1, target);
        if (index != -1) {
            return index;
        }
        
        return searchTarget(nums, 0, Math.max(rotateIndex - 1, 0), target);
    }
    
    /**
     * 二分查找法查找对应的值， 如果未找到返回-1
     */
    private int searchTarget(int[] nums, int low, int high, int target) {
        int mid = -1;
        while (high >= low) {
            mid = (high + low) >>> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray sa = new SearchInRotatedSortedArray();
        System.out.println(sa.search(new int[] {1, 3}, 3));
    }
}
