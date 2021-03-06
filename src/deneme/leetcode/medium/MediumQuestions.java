package deneme.leetcode.medium;

import deneme.leetcode.ListNode;
import deneme.leetcode.easy.EasyQuestions;

import java.util.Arrays;

/**
 * @author Furkan İlbahar
 * @dateCreated 28.06.2020
 */
public class MediumQuestions {


    /* 2. Add Two Numbers
    You are given two non-empty linked lists representing two non-negative integers.
    The digits are stored in reverse order and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
        Example:
            Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
            Output: 7 -> 0 -> 8
            Explanation: 342 + 465 = 807.*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode currentNode = new ListNode();
        boolean incrementOne = false;

        while (l1 != null || l2 != null || incrementOne) {
            int digit1 = l1 != null ? l1.val : 0;
            int digit2 = l2 != null ? l2.val : 0;
            int value = incrementOne ? digit1 + digit2 + 1 : digit1 + digit2;
            ListNode currentNodeResult = new ListNode();
            if (value >= 10) {
                value = value % 10;
                currentNodeResult.val = value;
                incrementOne = true;
            }
            else {
                currentNodeResult.val = value;
                incrementOne = false;
            }
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            if (result == null) {
                result = new ListNode(value, null);
                currentNode = result;
            }
            else {
                currentNode.next = currentNodeResult;
                currentNode = currentNode.next;
            }
        }
        return result;
    }


    /* 137. Single Number II
    Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
    Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? //todo
        Example 1:
            Input: [2,2,3,2]
            Output: 3
        Example 2:
            Input: [0,1,0,1,0,1,99]
            Output: 99*/
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);

        if (nums.length > 1) {
            if (nums[0] != nums[1])
                return nums[0];
            if (nums[nums.length - 1] != nums[nums.length - 2])
                return nums[nums.length - 1];

            for (int i = 2; i < nums.length - 1; i++) {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1])
                    return nums[i];
            }
        }
        return nums[0];
    }


    /* 151. Reverse Words in a String
    Given an input string, reverse the string word by word.
        Example 1:
            Input: "the sky is blue"
            Output: "blue is sky the"
        Example 2:
            Input: "  hello world!  "
            Output: "world! hello"
            Explanation: Your reversed string should not contain leading or trailing spaces.
        Example 3:
            Input: "a good   example"
            Output: "example good a"
            Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
    Note: A word is defined as a sequence of non-space characters.
    Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
    You need to reduce multiple spaces between two words to a single space in the reversed string.*/
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(" ");
            result.append(words[i]);
        }
        return result.substring(1);
    }


    /* 213. House Robber II
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
    All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
    Meanwhile, adjacent houses have security system connected and it will automatically contact the police
    if two adjacent houses were broken into on the same night. Given a list of non-negative integers representing the
    amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
        Example 1:
            Input: [2,3,2]
            Output: 3
            Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
        Example 2:
            Input: [1,2,3,1]
            Output: 4
            Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.*/
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] nums1 = new int[nums.length - 1];
        int[] nums2 = new int[nums.length - 1];

        for (int i = 0; i < nums.length - 1; i++) {
            nums1[i] = nums[i];
            nums2[i] = nums[i + 1];
        }
        return Math.max(EasyQuestions.rob(nums1), EasyQuestions.rob(nums2));
    }


    /* 338. Counting Bits
    Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
    in their binary representation and return them as an array.
        Example 1:
            Input: 2
            Output: [0,1,1]
        Example 2:
            Input: 5
            Output: [0,1,1,2,1,2]
    Follow up: It is very easy to come up with a solution with run time O(n*sizeof(integer)).
    But can you do it in linear time O(n) /possibly in a single pass? Space complexity should be O(n).*/
    public static int[] countBits(int num) {

        int[] resultArray = new int[num + 1];
        resultArray[0] = 0;

        for (int i = 1; i <= num; i++) {
            int pow = 0;
            int temp = i;

            while (temp > 1) {
                temp /= 2;
                pow++;
            }
            resultArray[i] = resultArray[i - (int) Math.pow(2, pow)] + 1;
        }
        return resultArray;
    }


    /* 547. Friend Circles
    There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature.
    For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
    And we defined a friend circle is a group of students who are direct or indirect friends.
    Given a N*N matrix M representing the friend relationship between students in the class.
    If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
    And you have to output the total number of friend circles among all the students.
        Example 1:
            Input:
                [[1,1,0],
                [1,1,0],
                [0,0,1]]
            Output: 2
            Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
            The 2nd student himself is in a friend circle. So return 2.
        Example 2:
            Input:
                [[1,1,0],
                [1,1,1],
                [0,1,1]]
            Output: 1
            Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
            so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.*/
    public static int findCircleNum(int[][] m) {

        //todo
        return 0;
    }

}
