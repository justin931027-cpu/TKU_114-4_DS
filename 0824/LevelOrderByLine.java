import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    char value;
    TreeNode left;
    TreeNode right;

    TreeNode(char value) {
        this.value = value;
    }
}

public class LevelOrderByLine {

    public static void levelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("Level " + level + " (" + count + " nodes): ");

            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();

                System.out.print(node.value + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode('M');

        root.left = new TreeNode('F');
        root.right = new TreeNode('T');

        root.left.left = new TreeNode('B');
        root.left.right = new TreeNode('H');

        root.right.left = new TreeNode('R');
        root.right.right = new TreeNode('Z');

        System.out.println("一般 Binary Tree：");
        levelOrder(root);

        System.out.println();

        System.out.println("Empty Tree：");
        levelOrder(null);
    }
}