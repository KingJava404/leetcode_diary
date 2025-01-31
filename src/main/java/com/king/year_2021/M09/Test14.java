package com.king.year_2021.M09;

import com.king.util.MyPrint;
import com.king.util.LeetcodeUtil;

import java.util.Arrays;

/**
 * @program: leetcode
 * @description: 162. 寻找峰值
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-peak-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: King
 * @create: 2021-09-15 00:42
 */
public class Test14 {
    //执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
    //内存消耗： 38 MB , 在所有 Java 提交中击败了 65.63% 的用户
    public int findPeakElement(int[] nums) {
        int length = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        for (int i = 0; i < length; ++i) {
            if (max == nums[i]) {
                return i;
            }
        }
        return 0;
    }

    //执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
    //内存消耗： 37.8 MB , 在所有 Java 提交中击败了 93.88% 的用户
    public int findPeakElement1(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l >> 1);
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
    //内存消耗： 38.1 MB , 在所有 Java 提交中击败了 52.09% 的用户
    public int findPeakElement2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        // 二分法 [l, r] 永远表示查询之后仍然可能的范围
        while (l < r) {
            int mid = (l + r) / 2;

            // nums[-1] = nums[n] = -∞
            if (nums[mid] < nums[mid + 1]) {
                // 如果 mid + 1 更大， 说明 mid 之后肯定还在爬升，mid+1 之后有峰
                l = mid + 1;
            } else {
                // 如果 mid 更大， 说明 mid 之前有峰
                r = mid;
            }
        }

        // 条件退出的时候 l 和 r 相等， 而我们始终保持 [l, r] 内有峰。 所以，r就是峰所在的位置。

        return r;
    }


    public static void main(String[] args) {
        Test14 test14 = new Test14();
        MyPrint.print(test14.findPeakElement(LeetcodeUtil.stringToIntegerArray("[1,2,1,3,5,6,4]")));
        MyPrint.print(test14.findPeakElement(LeetcodeUtil.stringToIntegerArray("[1,2,3]")));
        MyPrint.print(test14.findPeakElement(LeetcodeUtil.stringToIntegerArray("[3,2,1]")));
        MyPrint.print(test14.findPeakElement1(LeetcodeUtil.stringToIntegerArray("[1,2,3]")));
        MyPrint.print(test14.findPeakElement2(LeetcodeUtil.stringToIntegerArray("[1,2,1,3,5,6,4]")));
    }
    //峰值元素是指其值严格大于左右相邻值的元素。
    //
    //给你一个整数数组nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
    //
    //你可以假设nums[-1] = nums[n] = -∞ 。
    //
    //你必须实现时间复杂度为 O(log n) 的算法来解决此问题。

}
