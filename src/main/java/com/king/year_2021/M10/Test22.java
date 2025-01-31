package com.king.year_2021.M10;

import com.king.util.Helper;
import com.king.util.MyPrint;

import java.util.*;

/**
 * @program: leetcode
 * @description: 229. 求众数 II
 * @author: King
 * @create: 2021-10-22 20:37
 */

public class Test22 {
    //给定一个大小为 n 的整数数组，找出其中所有出现超过 ? n/3 ? 次的元素。

    //执行用时： 11 ms , 在所有 Java 提交中击败了 28.12% 的用户 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 94.25% 的用户
    public List<Integer> majorityElement(int[] nums) {
        int length = nums.length;
        int n = length / 3;
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n) {
                list.add(entry.getKey());
            }

        }
        return list;
    }

    //执行用时： 2 ms , 在所有 Java 提交中击败了 53.04% 的用户 内存消耗： 42.6 MB , 在所有 Java 提交中击败了 6.66% 的用户
    public List<Integer> majorityElement1(int[] nums) {
        int element1 = 0;
        int element2 = 0;
        int vote1 = 0;
        int vote2 = 0;

        for (int num : nums) {
            if (vote1 > 0 && num == element1) { //如果该元素为第一个元素，则计数加1
                vote1++;
            } else if (vote2 > 0 && num == element2) { //如果该元素为第二个元素，则计数加1
                vote2++;
            } else if (vote1 == 0) { // 选择第一个元素
                element1 = num;
                vote1++;
            } else if (vote2 == 0) { // 选择第二个元素
                element2 = num;
                vote2++;
            } else { //如果三个元素均不相同，则相互抵消1次
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }
        // 检测元素出现的次数是否满足要求
        List<Integer> ans = new ArrayList<>();
        if (vote1 > 0 && cnt1 > nums.length / 3) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > nums.length / 3) {
            ans.add(element2);
        }

        return ans;
    }


    public static void main(String[] args) {
        Test22 test22 = new Test22();
        MyPrint.print(test22.majorityElement(Helper.getArrays(1, 1, 2, 3, 6, 1, 1)));
    }
}
