package deneme.leetcode.medium;

import deneme.leetcode.ListNode;

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

}
