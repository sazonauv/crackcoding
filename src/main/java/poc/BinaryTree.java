package poc;


import java.util.LinkedList;
import java.util.List;

/**
 * Given a tree that can have any number of child nodes,
 * write a function to trim the tree upto a given level k and
 * return the root where the trimmed level nodes now have
 * the sum of all their child nodes.
 *
 * Created by slava on 12/11/17.
 */
public class BinaryTree {

    class TreeNode {
        int value;
        int depth;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }

        private int sum(int sum) {
            if (left != null) {
                sum += left.sum(sum);
            }
            if (right != null) {
                sum += right.sum(sum);
            }
            return sum + value;
        }

        private void setDepth(int depth) {
            this.depth = depth;
            if (left != null) {
                left.setDepth(depth + 1);
            }
            if (right != null) {
                right.setDepth(depth + 1);
            }
        }
    }

    TreeNode root;

    public void setDepth() {
        root.setDepth(0);
    }

    public void trim(int depth) {
        if (depth < 0) {
            throw new IllegalArgumentException("depth must be >= 0");
        }
        List<TreeNode> nodes = traverse(root);
        for (TreeNode node : nodes) {
            if (node.depth == depth) {
                node.value = sum(node);
                node.left = null;
                node.right = null;
            }
        }
    }

    private int sum(TreeNode node) {
        return node.sum(0);
    }


    private List<TreeNode> traverse(TreeNode node) {
        LinkedList<TreeNode> prevNodes = new LinkedList<>();
        LinkedList<TreeNode> nextNodes = new LinkedList<>();
        nextNodes.add(node);
        while (!nextNodes.isEmpty()) {
            TreeNode current = nextNodes.pollLast();
            if (current.left != null) {
                nextNodes.add(current.left);
            }
            if (current.right != null) {
                nextNodes.add(current.right);
            }
            prevNodes.add(current);
        }
        return prevNodes;
    }


}
