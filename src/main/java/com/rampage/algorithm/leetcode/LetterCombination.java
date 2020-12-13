package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent. A mapping of digit to
 * letters (just like on the telephone buttons) is given below. Note that 1 does
 * not map to any letters. Example:
 * 
 * Input: "23" Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note: Although the above answer is in lexicographical order, your answer
 * could be in any order you want.
 * 
 * @author kidezi
 *
 */
public class LetterCombination {
    public List<String> letterCombinations(String digits) {
        // leetCode竟然还会判断为空字符串的情况。。
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        List<String> list = new ArrayList<>();
        String popStr = null;
        for (int i = 0; i < digits.length(); i++) {
            if (list.isEmpty()) {
                for (Character oneChar : map.get(digits.charAt(i))) {
                    list.add(oneChar.toString());
                }
            } else {
                while (!list.isEmpty()) {
                    popStr = list.get(0);
                    list.remove(0);
                    if (popStr.equals("SEP")) {
                        break;
                    }
                    for (Character oneChar : map.get(digits.charAt(i))) {
                        list.add(popStr + oneChar);
                    }
                }
            }
            list.add("SEP");
        }
        return list.subList(0, list.size() - 1);
    }

    public static void main(String[] args) {
        LetterCombination letterCombination = new LetterCombination();
        System.out.println(letterCombination.letterCombinations("234"));
    }
}
