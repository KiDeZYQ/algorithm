package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author kidezi
 *  Given a collection of candidate numbers (candidates) and a target number (target), 
 *  find all unique combinations in candidates where the candidate numbers sum to target.

    Each number in candidates may only be used once in the combination.
    Note: The solution set must not contain duplicate combinations.

 

Example 1:
    Input: candidates = [10,1,2,7,6,1,5], target = 8
    Output: 
    [
    [1,1,6],
    [1,2,5],
    [1,7],
    [2,6]
    ]

Example 2:
    Input: candidates = [2,5,2,1,2], target = 5
    Output: 
    [
    [1,2,2],
    [5]
    ]
 */
public class CombinationSumTwo {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /**
         * 思想上与CombinationSum一样，这里考虑不通过递归实现
         */
        Arrays.parallelSort(candidates);        // 排序
        List<List<Integer>> results = new ArrayList<>();
        Stack<Integer> temp = new Stack<>();
        
        // 非递归方式
        int startIndex = 0;                 // 标识最终结果的起始元素下标
        int len = candidates.length;        
        int curTarget = 0;
        while (startIndex < len) {
            curTarget = target;
            temp.clear();
            
            for (int i = startIndex; i < len; ) {
                if (curTarget < candidates[i]) {
                    if (temp.size() > 1) {
                        curTarget += temp.pop();
                        continue;
                    }
                    break;
                } else {
                    curTarget -= temp.push(candidates[i]);
                    if (curTarget == 0) {
                        results.add(new ArrayList<>(temp));
                    } else {
                        
                    }
                }
                
            }
        }
        
        
        // 递归方式
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
            if (!results.contains(temp)) {
                results.add(new ArrayList<>(temp));
            }
        } else {
            for (int i = pos; i < candidates.length; i++) {
                if (candidates[i] > target) {
                    // 因为数组已经是排过序了所以此时，如果起始下标元素的值都已经大于target，那么后续下标的更加不可能满足，这里可以实现剪枝操作
                    break;
                }
                temp.push(candidates[i]);
                search(results, temp, candidates, target - candidates[i], i + 1);
                temp.pop();
            }
        }
    }
    
    public static void main(String[] args) {
        CombinationSumTwo cst = new CombinationSumTwo();
        System.out.println(cst.combinationSum2(new int[] {2,5,2,1,2}, 7));
    }
}
