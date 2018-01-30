package top;

/**
 * Created by slava on 26/01/18.
 */
public class AddTwoNumbers {

    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.val == 0 && l1.next == null) {
            return l2;
        }
        if (l2.val == 0 && l2.next == null) {
            return l1;
        }
        ListNode d1 = l1;
        ListNode d2 = l2;
        ListNode dp = null;
        ListNode sum = null;
        int addOne = 0;
        while (d1 != null || d2 != null || addOne == 1) {
            int cur = (d1 == null ? 0 : d1.val)
                    + (d2 == null ? 0 : d2.val)
                    + addOne;
            ListNode d;
            if (cur < 10) {
                d = new ListNode(cur);
                addOne = 0;
            } else {
                d = new ListNode(cur % 10);
                addOne = 1;
            }
            if (sum == null) {
                sum = d;
            } else {
                dp.next = d;
            }
            dp = d;
            if (d1 != null) {
                d1 = d1.next;
            }
            if (d2 != null) {
                d2 = d2.next;
            }
        }
        return sum;
    }

}
