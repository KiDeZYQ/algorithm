package com.rampage.algorithm.leetcode;

/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * 
 * Input: [1,3,5,6], 5 Output: 2 
 * Example 2:
 * 
 * Input: [1,3,5,6], 2 Output: 1 
 * Example 3:
 * 
 * Input: [1,3,5,6], 7 Output: 4 
 * Example 4:
 * 
 * Input: [1,3,5,6], 0 Output: 0
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 1) {
            if (target > nums[0]) {
                return 1;
            }
            return 0;
        }
        /**
         * 思想应该是2分查找法
         * 左右逼近，如果找到直接返回。 否则
         * 当high = low的时候暂停，此时如果 target < 结束值 则target的下标应该为结束值减1 否则为结束值加1
         */
        int low = 0;
        int high = nums.length - 1;
        int mid = -1;
        while (high > low) {
            mid = (high + low) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        System.out.println("mid = " + mid + ", low = " + low + ", high = " + high);
        if (high >= 0 && nums[high] >= target) {
            return Math.max(0, high);
        }
        
        return high + 1;
    }
    
    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        /*
         * System.out.println(searchInsertPosition.searchInsert(new int[] {1,3,5,6},
         * 5)); System.out.println(searchInsertPosition.searchInsert(new int[]
         * {1,3,5,6}, 2)); System.out.println(searchInsertPosition.searchInsert(new
         * int[] {1,3,5,6}, 7));
         * System.out.println(searchInsertPosition.searchInsert(new int[] {1,3,5,6},
         * 0));
         */
        System.out.println(searchInsertPosition.searchInsert(new int[] {1, 3}, 3));
        
    }
}
