package deneme.leetcode.easy;

import deneme.leetcode.ListNode;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Furkan İlbahar
 * @dateCreated 28.06.2020
 */
public class EasyQuestions {


    /* 1. Two Sum
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
            Example:
    Given nums = [2, 7, 11, 15], target = 9,
    Because nums[0] + nums[1] = 2 + 7 = 9,  return [0, 1].*/
    public static int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int necessaryValue = target - nums[i];
            numsMap.remove(nums[i], i);
            if (numsMap.containsKey(necessaryValue)) {
                result[0] = i;
                result[1] = numsMap.get(necessaryValue);
                break;
            }
            numsMap.put(nums[i], i);
        }
        return result;
    }


    /* 7. Reverse Integer
    Given a 32-bit signed integer, reverse digits of an integer.
        Example 1:
    Input: 123
    Output: 321
        Example 2:
    Input: -123
    Output: -321
        Example 3:
    Input: 120
    Output: 21
    Note: Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1].
    For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.*/
    public static int reverse(int x) {

        int bolum = x;
        long tersi = 0;
        while (bolum != 0) {
            int kalan = bolum % 10;
            bolum = bolum / 10;
            if (bolum == 0)
                tersi = kalan + tersi;
            else
                tersi = (kalan + tersi) * 10;
        }

        if (tersi > Integer.MAX_VALUE || tersi < Integer.MIN_VALUE)
            return 0;

        return (int) tersi;
    }


    /* 9. Palindrome Number
    Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
        Example 1:
            Input: 121
            Output: true
        Example 2:
            Input: -121
            Output: false
            Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.*/
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int bolum = x;
        int tersi = 0;
        while (bolum > 0) {
            int kalan = bolum % 10;
            bolum = bolum / 10;
            if (bolum == 0)
                tersi = kalan + tersi;
            else
                tersi = (kalan + tersi) * 10;
        }
        if (x == tersi)
            return true;
        return false;
    }


    /* 21. Merge Two Sorted Lists
    Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
        Example:
    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
    */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode currentNode = new ListNode();

        while (l1 != null || l2 != null) {
            int value1 = l1 != null ? l1.val : Integer.MAX_VALUE;
            int value2 = l2 != null ? l2.val : Integer.MAX_VALUE;
            int minValue = Math.min(value1, value2);
            ListNode currentNodeResult = new ListNode(minValue);
            if (value1 != value2) {
                if (minValue == value1)
                    l1 = l1 != null ? l1.next : null;
                if (minValue == value2)
                    l2 = l2 != null ? l2.next : null;
            }
            else {
                l1 = l1 != null ? l1.next : null;
            }

            if (result == null) {
                result = new ListNode(minValue, null);
                currentNode = result;
            }
            else {
                if (minValue != Integer.MAX_VALUE) {
                    currentNode.next = currentNodeResult;
                    currentNode = currentNode.next;
                }
            }
        }
        return result;
    }


    /* 190. Reverse Bits
    Reverse bits of a given 32 bits unsigned integer.
        Example 1:
            Input: 00000010100101000001111010011100
            Output: 00111001011110000010100101000000
    Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
    so return 964176192 which its binary representation is 00111001011110000010100101000000.*/
    public static int reverseBits(int n) {
        int mask = 1;
        long result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                int value = 1;
                value <<= (31 - i);
                result = result + value;
            }
            mask <<= 1;
        }
        return (int) result;
    }


    /* 191. Number of 1 Bits
    Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).
        Example 1:
            Input: 00000000000000000000000000001011
            Output: 3
            Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.*/
    public static int hammingWeight1(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }

    public static int hammingWeight2(int n) {
        byte[] result = new byte[4];
        result[0] = (byte) ((n & 0xFF000000) >> 24);
        result[1] = (byte) ((n & 0x00FF0000) >> 16);
        result[2] = (byte) ((n & 0x0000FF00) >> 8);
        result[3] = (byte) ((n & 0x000000FF));

        BitSet bitSet = new BitSet(32);
        for (int byteIndex = 0; byteIndex < result.length; byteIndex++) {
            for (int i = 0; i < 8; i++) {
                bitSet.set((byteIndex * 8) + i, (result[byteIndex] & 1) == 1);
                result[byteIndex] >>= 1;
            }
        }
        return bitSet.cardinality();
    }


    /* 198. House Robber
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
    the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and
    it will automatically contact the police if two adjacent houses were broken into on the same night.
    Given a list of non-negative integers representing the amount of money of each house,
    determine the maximum amount of money you can rob tonight without alerting the police.
    Example:
        Input: nums = [2,7,9,3,1]
        Output: 12
            Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
            Total amount you can rob = 2 + 9 + 1 = 12.*/
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] resultArray = new int[nums.length];
        resultArray[0] = nums[0];
        resultArray[1] = Math.max(nums[0], nums[1]);
        if (nums.length == 2) {
            return resultArray[1];
        }
        resultArray[2] = resultArray[0] + nums[2];

        for (int i = 3; i < nums.length; i++) {
            resultArray[i] = Math.max(resultArray[i - 3], resultArray[i - 2]) + nums[i];
        }
        return Math.max(resultArray[nums.length - 2], resultArray[nums.length - 1]);
    }


    /* 231. Power of Two
    Given an integer, write a function to determine if it is a power of two.
        Example 1:
            Input: 1
            Output: true
            Explanation: 20 = 1
        Example 2:
            Input: 16
            Output: true
            Explanation: 24 = 16
        Example 3:
            Input: 218
            Output: false*/
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int bolum = n;
        int kalan = 0;
        while (bolum > 1) {
            kalan = bolum % 2;
            if (kalan != 0) {
                return false;
            }
            bolum = bolum / 2;
        }
        return true;
    }

}
