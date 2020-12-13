package com.rampage.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "barfoothefoobarman", words = ["foo","bar"] Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar"
 * respectively. The output order does not matter, returning [9,0] is fine too.
 * 
 * Example 2:
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 *
 */
public class SubstrOfConcatWord {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        // STEP1: 统计每一个单词出现的次数
        for (String oneWord : words) {
            if (wordCount.containsKey(oneWord)) {
                wordCount.put(oneWord, wordCount.get(oneWord) + 1);
            } else {
                wordCount.put(oneWord, 1);
            }
        }
        
        // STEP2: 将起始字符串依次向后递归，如果最后map中每个字符串出现的次数清零了，则证明满足条件
        int eachLen = words[0].length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - eachLen * words.length; i++) {
            Map<String, Integer> calCount = new HashMap<>(wordCount);
            int j = i;
            while (j < i + eachLen * words.length && calCount.containsKey(s.substring(j, j + eachLen))) {
                if (calCount.get(s.substring(j, j + eachLen)) == 1) {
                    calCount.remove(s.substring(j, j + eachLen));
                } else {
                    calCount.put(s.substring(j, j + eachLen), calCount.get(s.substring(j, j + eachLen)) - 1); 
                }
                j += eachLen;
            }
            if (j == i + eachLen * words.length) {
                result.add(i);
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        SubstrOfConcatWord sc = new SubstrOfConcatWord();
        System.out.println(sc.findSubstring("wordgoodgoodgoodbestword", new String[] {"good","good","best","good"}));
    }
}
