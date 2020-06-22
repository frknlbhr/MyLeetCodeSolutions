import deneme.ListNode;
import deneme.SynchronizedTrial;

import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedTrial synchronizedTrial = new SynchronizedTrial(0);

        IntStream.range(0, 1000).forEach(count -> service.submit(synchronizedTrial::calculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        System.out.println(synchronizedTrial.getSum());*/


        //System.out.println(reverse(-123));

        System.out.println(reverseBits(1));
    }



    /*Given a 32-bit signed integer, reverse digits of an integer.
        Example 1:
    Input: 123
    Output: 321
        Example 2:
    Input: -123
    Output: -321
        Example 3:
    Input: 120
    Output: 21
    Note: Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
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



    /*Given an array of integers, return indices of the two numbers such that they add up to a specific target.
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


    /*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list. You may assume the two numbers do not contain any leading zero, except the number 0 itself.
            Example:
    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.
    */
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


    /*Merge two sorted linked lists and return it as a new sorted list. The new list should be made by splicing together the nodes of the first two lists.
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


    public static int hammingWeight(int n) {
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

    public static int hammingWeight2(int n) {
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


}
