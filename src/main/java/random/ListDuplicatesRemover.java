package random;

/**
 * Created by slava on 17/01/18.
 */
public class ListDuplicatesRemover {

     // Definition for singly-linked list
     public class ListNode {
          int val;
          ListNode next;

          ListNode(int x) {
              val = x;
          }
     }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode current = head;
        ListNode next = head.next;
        while (next != null) {
            while (next != null && next.val == current.val) {
                next = next.next;
            }
            current.next = next;
            current = next;
            if (current == null) {
                break;
            }
            next = current.next;
        }
        return head;
    }


}
