package poc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by slava on 12/11/17.
 */
public class DirectedAcyclicGraph {

    class Node {
        List<Node> children;

        List<Node> path;
        Set<List<Node>> pathSet;

        public void step() {
            if (children == null) {
                return;
            }
            for (Node child : children) {
                child.path = new LinkedList<>();
                if (path != null) {
                    child.path.addAll(path);
                }
                child.path.add(this);
                if (child.pathSet == null) {
                    child.pathSet = new HashSet<>();
                }
                child.pathSet.add(child.path);
                child.step();
            }
        }
    }

    public Set<List<Node>> getPaths(Node parent, Node child) {
        parent.step();
        return child.pathSet;
    }

    /*private LinkedList<Node> traverse(Node parent) {
        LinkedList<Node> prevNodes = new LinkedList<>();
        LinkedList<Node> nextNodes = new LinkedList<>();
        nextNodes.add(parent);
        while (!nextNodes.isEmpty()) {
            Node current = nextNodes.pollFirst();
            prevNodes.add(current);
            nextNodes.addAll(current.children);
        }
        return prevNodes;
    }*/

}
