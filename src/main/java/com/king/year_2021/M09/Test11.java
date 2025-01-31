package com.king.year_2021.M09;

import com.king.util.MyPrint;

import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: leetcode
 * @description: 678. 有效的括号字符串
 * https://leetcode-cn.com/problems/valid-parenthesis-string/
 * @author: King
 * @create: 2021-09-12 20:54
 */
public class Test11 {
    //执行用时： 1 ms , 在所有 Java 提交中击败了 52.53% 的用户 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 74.51% 的用户
    public boolean checkValidString(String s) {
        Deque<Integer> leftStack = new LinkedList<Integer>();
        Deque<Integer> asteriskStack = new LinkedList<Integer>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                asteriskStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!asteriskStack.isEmpty()) {
                    asteriskStack.pop();
                } else {
                    return false;
                }
            }
        }
        while (!leftStack.isEmpty() && !asteriskStack.isEmpty()) {
            int leftIndex = leftStack.pop();
            int asteriskIndex = asteriskStack.pop();
            if (leftIndex > asteriskIndex) {
                return false;
            }
        }
        return leftStack.isEmpty();
    }

    public static void main(String[] args) {
        Test11 test11 = new Test11();

        MyPrint.print(test11.checkValidString("(*))"));
    }

    public void a( Integer[] cards) {

        Arrays.sort(cards, Collections.reverseOrder());
        Arrays.sort(cards, Collections.reverseOrder());
        Arrays.sort(cards, (Integer a, Integer b) -> {
            return b - a;
        });
        Arrays.sort(cards, (o1, o2) -> o2 - o1);
    }


    //给定一个只包含三种字符的字符串：（?，）?和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
    //
    //任何左括号 (?必须有相应的右括号 )。
    //任何右括号 )?必须有相应的左括号 (?。
    //左括号 ( 必须在对应的右括号之前 )。
    //*?可以被视为单个右括号 )?，或单个左括号 (?，或一个空字符串。
    //一个空字符串也被视为有效字符串。
    //示例 1:
    //
    //输入: "()"
    //输出: True
    //示例 2:
    //
    //输入: "(*)"
    //输出: True
    //示例 3:
    //
    //输入: "(*))"
    //输出: True
    //注意:
    //
    //字符串大小将在 [1，100] 范围内。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/valid-parenthesis-string
    //著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
}
