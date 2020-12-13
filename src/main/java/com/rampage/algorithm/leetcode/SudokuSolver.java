package com.rampage.algorithm.leetcode;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.

    A sudoku solution must satisfy all of the following rules:
    Each of the digits 1-9 must occur exactly once in each row.
    Each of the digits 1-9 must occur exactly once in each column.
    Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
    The '.' character indicates empty cells.
    
    
    Input: board = 
    [["5","3",".",".","7",".",".",".","."],
     ["6",".",".","1","9","5",".",".","."],
     [".","9","8",".",".",".",".","6","."],
     ["8",".",".",".","6",".",".",".","3"],
     ["4",".",".","8",".","3",".",".","1"],
     ["7",".",".",".","2",".",".",".","6"],
     [".","6",".",".",".",".","2","8","."],
     [".",".",".","4","1","9",".",".","5"],
     [".",".",".",".","8",".",".","7","9"]]
Output: 
    [["5","3","4","6","7","8","9","1","2"],
     ["6","7","2","1","9","5","3","4","8"],
     ["1","9","8","3","4","2","5","6","7"],
     ["8","5","9","7","6","1","4","2","3"],
     ["4","2","6","8","5","3","7","9","1"],
     ["7","1","3","9","2","4","8","5","6"],
     ["9","6","1","5","3","7","2","8","4"],
     ["2","8","7","4","1","9","6","3","5"],
     ["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:
 * @author kidezi
 * 解数独
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        /**
         * 思想应该是需要三个维度（当前行、当前列、当前3X3方阵）来存储当前坐标允许的取值，然后取交集，
         * 交集只有一个元素的时候则该值就是该点的应该取值 
         * 存在可能是遍历之后发现没有交集仅一个元素的情况，此时还需要选取最小的点来进行试错
         */
         // 发现这个有点难。最后考虑网上的一种实现方式 利用递归 + validSudoku的思想
        solve(board, 0, 0);
    }
    
    private boolean solve(char[][] board, int i, int j) {
        if (i == 9) return true;
        if (j == 9) return solve(board, i + 1, 0);
        if (board[i][j] != '.') {
            return solve(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!validNum(board, i, j, ch)) {
                continue;
            }
            board[i][j] = ch;
            if (solve(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    private boolean validNum(char[][] board, int i, int j, char ch) {
        for (int k=0; k<9; k++) {
            if (board[i][k] == ch) {
                return false;
            }
        }
        for (int k=0; k<9; k++) {
            if (board[k][j] == ch) {
                return false;
            }
        }
        int row = i - i % 3;
        int col = j - j % 3;
        for (int k=0; k<3; k++) {
            for (int l=0; l<3; l++) {
                if (board[k+row][l+col] == ch) {
                    return false;
                }
            }
        }
        return true;
    }
}
