package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author kidezi
 *  
 *  Given an array of distinct integers candidates and a target integer target, 
 *   return a list of all unique combinations of candidates where the chosen numbers sum to target.
 *   You may return the combinations in any order.
    The same number may be chosen from candidates an unlimited number of times. 
    Two combinations are unique if the frequency of at least one of the chosen numbers is different.
    It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:
    Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]
Explanation:
    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
    7 is a candidate, and 7 = 7.
    These are the only two combinations.
    
Example 2:
    Input: candidates = [2,3,5], target = 8
    Output: [[2,2,2,2],[2,3,3],[3,5]]
    
Example 3:
    Input: candidates = [2], target = 1
    Output: []
    
Example 4:
    Input: candidates = [1], target = 1
    Output: [[1]]
    
Example 5:
    Input: candidates = [1], target = 2
    Output: [[1,1]]
 

Constraints:
    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    All elements of candidates are distinct.
    1 <= target <= 500
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * 最粗暴的方法开始 从前往后依次用对应的元素来试探看能否接近 为了实现能够尽早终止试探对数组进行排序
         */
        Arrays.parallelSort(candidates);    // 先排序
        List<List<Integer>> results = new ArrayList<>();
        Stack<Integer> temp = new Stack<>();
        search(results, temp, candidates, target, 0);
        return results;
    }

    /**
     * 逐层搜索
     * @param results       返回结果
     * @param temp          临时搜索列表
     * @param candidates    排序后的数组
     * @param target        剩余的找到和为target还缺少的值
     * @param pos           当前搜索的数组起始值的下标
     */
    private void search(List<List<Integer>> results, Stack<Integer> temp, int[] candidates, int target, int pos) {
        if (target == 0) {
            // 如果距离target为0，说明此事temp中的就是所求的一个结果
            results.add(new ArrayList<>(temp));
        } else {
            for (int i = pos; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    // 因为数组已经是排过序了所以此时，如果起始下标元素的值都已经大于target，那么后续下标的更加不可能满足，这里可以实现剪枝操作
                    break;
                }
                temp.push(candidates[i]);
                search(results, temp, candidates, target - candidates[i], i);
                temp.pop();
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {2,3,5};
        CombinationSum cs = new CombinationSum();
        System.out.println(cs.combinationSum(arr, 8));
    }
}
