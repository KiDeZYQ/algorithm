package com.rampage.algorithm.stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 * 
 *  逆波兰表达式是栈的最佳应用
 * @author kidezi
 *
 */
public class RPNCal {
    
    private static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/", "(", ")");
    
    public int cal(String calStr) {
        /**
         * STEP1: 先将中缀算数表达式转换成后缀表达式
         * 遵循以下几个原则：
         * 1. 转换后的逆波兰表达式不包含括号，所以括号是不放入最后的list中的
         * 2. 如果遇到数字的话直接放入list列表
         * 3. 如果碰到符号（+、-、*、/）则需要判断当前栈的状态，如果栈为空或者栈顶元素的优先级小于当前符号优先级或者栈顶元素为左括号时才将当前操作符入栈
         * 4. 左括号无条件入栈
         * 5. 碰到右括号，则需要将最近一个左括号之前的所有操作符依次出栈入list
         */
        List<String> elementList = new LinkedList<>();      // 用来存储从字符串中分割得来的元素（每个数字和操作符都当做一个元素）
        Stack<Character> operatorStack = new Stack<>();          // 用来临时存储操作符
        char ch = 0;
        StringBuilder sb = new StringBuilder(calStr.length());   // 用来存储数字
        for (int i = 0, len = calStr.length(); i < len; i++) {
            ch = calStr.charAt(i);
            if (Character.isWhitespace(ch)) {
                continue;       // 忽略空白的字符
            }
            if (OPERATORS.contains(Character.toString(ch))) {
                if (sb.length() > 0) {
                    elementList.add(sb.toString());
                    sb.setLength(0);
                }
                if (ch == '+' || ch == '-') {
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(') { // 加减符号优先级最低，所以应该将栈中左括号之前所有的符号出栈再将当前符号入栈
                        elementList.add(Character.toString(operatorStack.pop()));
                    }
                    operatorStack.push(ch);
                } else if (ch == '(' || operatorStack.isEmpty()) {
                    operatorStack.push(ch);  // 如果是左括号或者符号栈为空的话直接将当前符号入栈
                } else if (ch == ')') {
                    // 如果是右括号，则将栈中左括号之前的元素都弹出入list
                    while (operatorStack.peek() != '(') {
                        elementList.add(Character.toString(operatorStack.pop()));
                    }
                    operatorStack.pop();     // 将做扩号出栈且不放入list中
                } else {
                    while (operatorStack.peek() == '*' || operatorStack.peek() == '/') {
                        elementList.add(Character.toString(operatorStack.pop()));
                    }
                    operatorStack.push(ch);
                }
            } else {
                sb.append(ch);
            }
        }
        if (sb.length() > 0) {
            elementList.add(sb.toString());
        }
        // 将符号栈中的符号依次出栈入元素列表
        while (!operatorStack.isEmpty()) {
            elementList.add(Character.toString(operatorStack.pop()));
        }
        System.out.println("算数表达式：" + calStr + "转换成逆波兰表达式为：" + elementList);
        
        /**
         *  STEP2:  利用逆波兰表达式来进行算数计算
         *  此时操作非常简单，碰到数字直接入栈  碰到操作符则从栈弹出两个元素，并且使用该操作符进行操作
         *  将结果再次入栈
         */
        Stack<Integer> resultStack = new Stack<>();
        for (String oneEle : elementList) {
            if (OPERATORS.contains(oneEle)) {
                Integer num2 = resultStack.pop();
                Integer num1 = 0;
                if (!resultStack.isEmpty()) {
                    num1 = resultStack.pop();
                }
                resultStack.push(doCal(num1, num2, oneEle));
            } else {
                resultStack.push(Integer.parseInt(oneEle));
            }
        }
        
        return resultStack.pop();
    }
    
    private Integer doCal(Integer num1, Integer num2, String operator) {
        if ("+".equals(operator)) {
            return num1 + num2;
        } else if ("-".equals(operator)) {
            return num1 - num2;
        } else if ("*".equals(operator)) {
            return num1 * num2;
        } else if ("/".equals(operator)) {
            return num1 / num2;
        }
        throw new IllegalArgumentException("不支持的操作符：" + operator + "!");
    }

    public static void main(String[] args) {
        RPNCal rpnCal = new RPNCal();
        System.out.println(rpnCal.cal("(-2+3)*4 + 10 - 7 * (8 - 7)"));
    }
}
