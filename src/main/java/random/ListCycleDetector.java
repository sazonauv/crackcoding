package random;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by slava on 07/11/17.
 */
public class ListCycleDetector {

    class Node {
        int data;
        Node next;
    }


    boolean hasCycle(Node head) {
        List<Node> visits = new LinkedList<>();
        Node current = head;
        boolean hasCycle = false;
        while (!hasCycle && current != null) {
            visits.add(current);
            current = current.next;
            hasCycle = visits.contains(current);
        }
        return hasCycle;
    }

}
