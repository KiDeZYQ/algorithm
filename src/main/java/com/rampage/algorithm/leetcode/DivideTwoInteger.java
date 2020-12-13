package com.rampage.algorithm.leetcode;

/**
 * Given two integers dividend and divisor, divide two integers without using
 * multiplication, division and mod operator.
 * 
 * Return the quotient after dividing dividend by divisor.
 * 
 * The integer division should truncate toward zero, which means losing its
 * fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * 
 * Example 1:
 * Input: dividend = 10, divisor = 3 Output: 3 Explanation: 10/3 =
 * truncate(3.33333..) = 3. 
 * 
 * Example 2:
 * Input: dividend = 7, divisor = -3 Output: -2 Explanation: 7/-3 =
 * truncate(-2.33333..) = -2. Note:
 * 
 * Both dividend and divisor will be 32-bit signed integers. The divisor will
 * never be 0. Assume we are dealing with an environment which could only store
 * integers within the 32-bit signed integer range: [−231,  231 − 1]. For the
 * purpose of this problem, assume that your function returns 231 − 1 when the
 * division result overflows.
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/divide-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author kidezi
 * 
 * 2020-08
 *
 */
public class DivideTwoInteger {
    public int divide(int dividend, int divisor) {
        // STEP1: 首先确定参数结果正负
        boolean negative = (dividend ^ divisor) < 0;
        
        // STEP2: 将数修改为正数，这里需要注意Integer.MIN_VALUE直接转换为正整数会溢出，这里用long来接收转换后的效果
        long num1 = Math.abs((long)dividend);
        long num2 = Math.abs((long)divisor);
        
        /**
         * 核心思想： 参照10进制的两数相除的形式
         * 先从最高位开始，判断num1取左边前n位后是否比num2大，如果比num2大，10进制的除法上可能是1-9，对于2进制，
         * 则直接是1
         */
        long mask = 1l << 31;   // 这个mask其实是第32位为1，也就是整数的最高位 (需要注意1后的L不能少)
        long leftSplicing = 0;    // num1最左的几位数（高位）
        long result = 0;
        while (mask > 0) {
            leftSplicing = (leftSplicing << 1) | ((num1 & mask) == 0 ? 0 : 1) ;
            if (leftSplicing >= num2) {
                leftSplicing -= num2;
                result = (result << 1) | 1;     // 如果num1最左几位大于等于num2,其实相当于这时候商填1
            } else {
                result = (result << 1) | 0;     // 如果num1最左几位小于num2，其实相当于这时候商填0
            }
            mask = mask >> 1;   // 右移取的高位值
        }
        if (result >> 31 != 0 && !negative) {  // 如果是正数且超过Integer.MAX_VALUE 则直接取Integer.MAX_VALUE
            return Integer.MAX_VALUE;
        }
        return (int) (negative ? -result : result);
    }
    
    public static void main(String[] args) {
        DivideTwoInteger dividor = new DivideTwoInteger();
        //System.out.println(dividor.divide(Integer.MIN_VALUE, 1));
        //System.out.println(dividor.divide(Integer.MAX_VALUE, 1));
        // -1010369383 -2147483648
        //System.out.println(dividor.divide(-2147483648, -1010369383));
        // -2147483648   -1109186033
        System.out.println(dividor.divide(-2147483648, -1109186033));
        // System.out.println(Integer.MAX_VALUE );
    }
}
