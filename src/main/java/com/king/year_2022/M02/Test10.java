package com.king.year_2022.M02;

import com.king.util.MyPrint;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: leetcode_diary
 * @description: 1447. 最简分数
 * @author: King
 * @create: 2022-02-10 23:33
 */
public class Test10 {
    //给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。
    // 分数可以以 任意 顺序返回。
    int gcd(int a, int b) {
        return b == 0 ? gcd(b, a % b) : a;
    }

    public List<String> simplifiedFractions(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) != 1) continue ;
                list.add(i + "/" + j);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Test10 test10 = new Test10();

        MyPrint.printObs(test10.simplifiedFractions(4),test10.simplifiedFractions(3));
    }
}
