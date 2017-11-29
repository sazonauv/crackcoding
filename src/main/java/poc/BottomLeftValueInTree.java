package poc;

/**
 * Created by slava on 29/11/17.
 */
public class BottomLeftValueInTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public int findBottomLeftValue(TreeNode root) {
        goLeft(root, 1);
        return leftMost;
    }

    int leftMost;
    int maxDepth;

    private void goLeft(TreeNode node, int depth) {
        if (maxDepth < depth) {
            maxDepth = depth;
            leftMost = node.val;
        }
        if (node.left != null) {
            goLeft(node.left, depth + 1);
        }
        if (node.right != null) {
            goLeft(node.right, depth + 1);
        }
    }

}
