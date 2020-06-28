package deneme.leetcode.hard;

import deneme.leetcode.ListNode;
import deneme.leetcode.easy.EasyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Furkan İlbahar
 * @dateCreated 28.06.2020
 */
public class HardQuestions {



    /* 23. Merge k Sorted Lists
    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
        Example:
    Input:
        [
            1->4->5,
            1->3->4,
            2->6
        ]
    Output: 1->1->2->3->4->4->5->6
    */
    public static ListNode mergeKLists(ListNode[] lists) {

        List<ListNode> listNodes = Arrays.asList(lists);
        List<ListNode> modifiableListNodes = new ArrayList<>(listNodes);
        List<ListNode> listNodesCopy = new ArrayList<>(modifiableListNodes);

        while (modifiableListNodes.size() > 1) {
            for (int i = 0; i < listNodesCopy.size() / 2; i++) {
                ListNode resultNode = EasyQuestions.mergeTwoLists(listNodesCopy.get(i), listNodesCopy.get(listNodesCopy.size() - 1 - i));
                modifiableListNodes.remove(listNodesCopy.get(i));
                modifiableListNodes.remove(listNodesCopy.get(listNodesCopy.size() - 1 - i));
                modifiableListNodes.add(resultNode);
            }
            listNodesCopy = new ArrayList<>(modifiableListNodes);
        }
        return !modifiableListNodes.isEmpty() ? modifiableListNodes.get(0) : null;
    }

}
