package com.rampage.algorithm.leetcode;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place and use only constant extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2 3,2,1 → 1,2,3 1,1,5 → 1,5,1
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *  这个题目意思英文不好不太好理解，其实就是求当前这个序列的全排列的下一个序列 以123的全排列举例如下，如果按照字段升序，那么序列应该如下：
 *  1 2 3
 *  1 3 2
 *  2 1 3
 *  2 3 1
 *  3 1 2
 *  3 2 1
 *  
 *  
 *  1 2 3 4
 *  1 2 4 3
 *  1 3 2 4
 *  1 3 4 2
 *  1 4 2 3 
 *  1 4 3 2
 *  
 *  4 3 2 1
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        /**
         * 主要思想： 从最后一个数字向前遍历，遇到比该数字小的则两者替换位置
         */
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        int i = len - 1;
        int leftStep = 1;
        int j = len - 2;
        for (; j >= 0; j--) {
            i = len - 1;
            leftStep = 1;
            for (; i > j; ) {
                if (nums[i - leftStep] < nums[i]) {
                    swap(nums, i-leftStep, i);
                    break;
                } 
                leftStep++;
                if (i - leftStep < j) {
                    leftStep = 1;
                    i--;
                }
            }
            if (i - leftStep >= j) {
                break;
            }
        }
        
        // 表示没有找到， 此时把数组逆序就是全排序最初始的数组
        if (j < 0) {
            for (i = 0, j = len - 1; i < j; i++,j--) {
                swap(nums, i, j);
            }
            return;
        }
        
        // 此时已经做了第一次替换，第二次就是要将i-leftStep左边规划成从小到大排列
        for (i = i - leftStep + 1, j = len - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = new int[] {1, 2, 4, 3};
        np.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
