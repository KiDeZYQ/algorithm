package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * 
 * @author kidezi
 *
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return Arrays.asList("()");
        }
        List<String> preResult = generateParenthesis(n - 1);
        Set<String> results = new HashSet<>(preResult.size() << 1);
        for (String one : preResult) {
            results.addAll(generateAddOne(one));    
        }
        
        return new ArrayList<>(results);
    }

    private Collection<? extends String> generateAddOne(String one) {
        Set<String> result = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        boolean right = false;
        int leftCount = 0;
        char ch = 0;
        /**
         * 定义两个变量
         * i表示当前左括号插入到原来字符串的下标为i的位置
         * j表示当前遍历到的原字符串下标
         */
        for (int i = 0, len = one.length(); i < len; i++) {
            sb.setLength(0);
            right = false;
            for (int j = 0; j < len; j++) {
               if (i == j) {
                   sb.append("(");
                   leftCount++;
                   right = true;
               }
               
               if (leftCount == 1 && right) {
                   result.add(sb.toString() + ")" + one.substring(j));
               }
               ch = one.charAt(j);
               if (ch == '(') {
                   leftCount++;
               } else {
                   leftCount--;
               }
               sb.append(ch);
               if (leftCount == 1 && right) {
                   result.add(sb.toString() + ")" + one.substring(j+1));    // 注意这里是j+1 因为ch此时已经被添加了
               }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        GenerateParentheses generator = new GenerateParentheses();
        System.out.println(generator.generateParenthesis(10));
    }
}
