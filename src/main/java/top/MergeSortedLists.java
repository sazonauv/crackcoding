package top;

/**
 * Created by slava on 30/01/18.
 */
public class MergeSortedLists {


    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode res = null;
        ListNode prev = null;
        ListNode n1 = l1;
        ListNode n2 = l2;
        while (n1 != null && n2 != null) {
            ListNode n;
            if (n1.val < n2.val) {
                n = new ListNode(n1.val);
                n1 = n1.next;
            } else {
                n = new ListNode(n2.val);
                n2 = n2.next;
            }
            if (res == null) {
                res = n;
            } else {
                prev.next = n;
            }
            prev = n;
        }
        if (n1 == null) {
            while (n2 != null) {
                ListNode n = new ListNode(n2.val);
                prev.next = n;
                prev = n;
                n2 = n2.next;
            }
        }
        if (n2 == null) {
            while (n1 != null) {
                ListNode n = new ListNode(n1.val);
                prev.next = n;
                prev = n;
                n1 = n1.next;
            }
        }
        return res;
    }


}
