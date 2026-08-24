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

public class TraversalTestReport {

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

    public static void test(
            String name,
            TreeNode root,
            List<String> expectedPreorder,
            List<String> expectedInorder,
            List<String> expectedPostorder,
            List<String> expectedLevelOrder) {

        System.out.println("===== " + name + " =====");

        printResult(
                "Preorder",
                expectedPreorder,
                preorder(root));

        printResult(
                "Inorder",
                expectedInorder,
                inorder(root));

        printResult(
                "Postorder",
                expectedPostorder,
                postorder(root));

        printResult(
                "Level-order",
                expectedLevelOrder,
                levelOrder(root));

        System.out.println();
    }

    public static void printResult(
            String traversal,
            List<String> expected,
            List<String> actual) {

        System.out.println(traversal);
        System.out.println("預期：" + expected);
        System.out.println("實際：" + actual);
        System.out.println("相同：" + expected.equals(actual));
    }

    public static void main(String[] args) {

        TreeNode empty = null;

        test(
                "Empty Tree",
                empty,
                List.of(),
                List.of(),
                List.of(),
                List.of());

        TreeNode single = new TreeNode("A");

        test(
                "Single-node Tree",
                single,
                List.of("A"),
                List.of("A"),
                List.of("A"),
                List.of("A"));

        TreeNode onlyLeft = new TreeNode("A");
        onlyLeft.left = new TreeNode("B");
        onlyLeft.left.left = new TreeNode("C");

        test(
                "Only-left Tree",
                onlyLeft,
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));

        TreeNode onlyRight = new TreeNode("A");
        onlyRight.right = new TreeNode("B");
        onlyRight.right.right = new TreeNode("C");

        test(
                "Only-right Tree",
                onlyRight,
                List.of("A", "B", "C"),
                List.of("A", "B", "C"),
                List.of("C", "B", "A"),
                List.of("A", "B", "C"));

        TreeNode complete = new TreeNode("A");
        complete.left = new TreeNode("B");
        complete.right = new TreeNode("C");
        complete.left.left = new TreeNode("D");
        complete.left.right = new TreeNode("E");
        complete.right.left = new TreeNode("F");
        complete.right.right = new TreeNode("G");

        test(
                "Complete Tree",
                complete,
                List.of("A", "B", "D", "E", "C", "F", "G"),
                List.of("D", "B", "E", "A", "F", "C", "G"),
                List.of("D", "E", "B", "F", "G", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F", "G"));

        TreeNode irregular = new TreeNode("A");
        irregular.left = new TreeNode("B");
        irregular.right = new TreeNode("C");
        irregular.left.right = new TreeNode("D");
        irregular.right.left = new TreeNode("E");
        irregular.right.left.right = new TreeNode("F");

        test(
                "Irregular Tree",
                irregular,
                List.of("A", "B", "D", "C", "E", "F"),
                List.of("B", "D", "A", "E", "F", "C"),
                List.of("D", "B", "F", "E", "C", "A"),
                List.of("A", "B", "C", "D", "E", "F"));
    }
}