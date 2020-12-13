package com.rampage.algorithm.array;

/**
 * Write a function that add two numbers A and B.
 * There is no need to read data from standard input stream. Both parameters are given in function aplusb, your job is to calculate the sum and return it.
 * Students in the basic class of the algorithm only need to use the arithmetic operator ‘+’ to complete the problem, without considering the requirements of the bit operation.
 * 
 * Clarification
 *	Are a and b both 32-bit integers?
 *	Yes.
 *	Can I use bit operation?
 *	Sure you can.
 *
 *	Example
 *	Example 1:
 *	Input:  a = 1, b = 2
 *  Output: 3	
 *	Explanation: return the result of a + b.
 *	
 *  Example 2:
 *	Input:  a = -1, b = 1
 *	Output: 0
 *	
 *	Explanation: return the result of a + b.
 *
 *	Challenge
 *	Of course you can just return a + b to get accepted. But Can you challenge not do it like that?(You should not use + or any arithmetic operators.)
 *
 *  实现一个a+b的函数，但是不能使用 + - * / 运算符 可以使用位操作运算符
 * @author KiDe
 *
 */
public class AplusB {
	
	public static void main(String[] args) {
		AplusB apb = new AplusB();
		System.out.println(apb.aplusb(1, -2));
		
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-2));
		System.out.println(Integer.toBinaryString(2));
	}
	
	/**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b 
     */
    public int aplusb(int a, int b) {
        // write your code here
    	for (int i = 1; i < 32; i++) {
    		
    	}
        return -1;
    }
}
