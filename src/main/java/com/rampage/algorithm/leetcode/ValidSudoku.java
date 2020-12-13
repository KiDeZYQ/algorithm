package com.rampage.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 *  Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

    Each row must contain the digits 1-9 without repetition.
    Each column must contain the digits 1-9 without repetition.
    Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    
    Note:
    A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    Only the filled cells need to be validated according to the mentioned rules.
    
    Input: board = 
    [["5","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: true
    
    Input: board = 
    [["8","3",".",".","7",".",".",".","."]
    ,["6",".",".","1","9","5",".",".","."]
    ,[".","9","8",".",".",".",".","6","."]
    ,["8",".",".",".","6",".",".",".","3"]
    ,["4",".",".","8",".","3",".",".","1"]
    ,["7",".",".",".","2",".",".",".","6"]
    ,[".","6",".",".",".",".","2","8","."]
    ,[".",".",".","4","1","9",".",".","5"]
    ,[".",".",".",".","8",".",".","7","9"]]
    Output: false
Explanation: 
    Same as Example 1, except with the 5 in the top left corner being modified to 8.
     Since there are two 8's in the top left 3x3 sub-box, it is invalid.
    
 * @author kidezi
 *   
 *   即判断9X9的数独是否合法 这个不用判断是否存在解法，只需要判断当前已经填的数字是否满足以下3个规则：
 *   1. 每一行的数字1到9不重复
 *   2. 每一列的数字1到9不重复
 *   3. 每个3X3的小正方形数字1到9不重复
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        if (board.length != 9 || board[0].length != 9) {
            // 不是9X9的数组直接判定不合法
            return false;
        }
        
        // 依次校验吧  好像也可以 时间复杂度可能就是 9*9*3  但是其实还有另一个利用Set不能添加重复字符串的算法来取巧实现时间复杂度为 9*9 
        Set<String> set = new HashSet<>();
        char ch = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ch = board[i][j];
                if (ch == '.') {
                    continue;   // 未填的不校验
                }
                if (!set.add("_" + i + ch)) {
                    return false;
                }
                if (!set.add("|" + j + ch)) {
                    return false;
                }
                if (!set.add("/" + (i/3) + (j/3) + ch)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        ValidSudoku vs = new ValidSudoku();
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' }, { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(vs.isValidSudoku(board));
        board = new char[][] { { '8', '3', '.', '.', '7', '.', '.', '.', '.' }, { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        System.out.println(vs.isValidSudoku(board));
    }
}
