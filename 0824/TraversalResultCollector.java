import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode(String value) {
        this.value = value;
    }
}

public class TraversalResultCollector {

    public static List<String> preorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode node, List<String> result) {
        if (node == null) {
            return;
        }

        result.add(node.value);
        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    public static List<String> inorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<String> result) {
        if (node == null) {
            return;
        }

        inorderHelper(node.left, result);
        result.add(node.value);
        inorderHelper(node.right, result);
    }

    public static List<String> postorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private static void postorderHelper(TreeNode node, List<String> result) {
        if (node == null) {
            return;
        }

        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.value);
    }

    public static List<String> levelOrder(TreeNode root) {
        List<String> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            result.add(node.value);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("Empty Tree：");
        TreeNode empty = null;
        System.out.println("Preorder: " + preorder(empty));
        System.out.println("Inorder: " + inorder(empty));
        System.out.println("Postorder: " + postorder(empty));
        System.out.println("Level-order: " + levelOrder(empty));

        System.out.println();

        System.out.println("Single-node Tree：");
        TreeNode single = new TreeNode("A");

        System.out.println("Preorder: " + preorder(single));
        System.out.println("Inorder: " + inorder(single));
        System.out.println("Postorder: " + postorder(single));
        System.out.println("Level-order: " + levelOrder(single));

        System.out.println();

        System.out.println("Left-skewed Tree：");

        TreeNode leftRoot = new TreeNode("A");
        leftRoot.left = new TreeNode("B");
        leftRoot.left.left = new TreeNode("C");
        leftRoot.left.left.left = new TreeNode("D");

        System.out.println("Preorder: " + preorder(leftRoot));
        System.out.println("Inorder: " + inorder(leftRoot));
        System.out.println("Postorder: " + postorder(leftRoot));
        System.out.println("Level-order: " + levelOrder(leftRoot));

        System.out.println();

        System.out.println("Complete Tree：");

        TreeNode root = new TreeNode("A");

        root.left = new TreeNode("B");
        root.right = new TreeNode("C");

        root.left.left = new TreeNode("D");
        root.left.right = new TreeNode("E");

        root.right.left = new TreeNode("F");
        root.right.right = new TreeNode("G");

        System.out.println("Preorder: " + preorder(root));
        System.out.println("Inorder: " + inorder(root));
        System.out.println("Postorder: " + postorder(root));
        System.out.println("Level-order: " + levelOrder(root));
    }
}