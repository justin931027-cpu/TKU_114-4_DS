import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Q09_TreeTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.value);
        preorderHelper(root.left, result);
        preorderHelper(root.right, result);
    }

    public static List<Integer> inorder(Node root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        inorderHelper(root.left, result);
        result.add(root.value);
        inorderHelper(root.right, result);
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private static void postorderHelper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }

        postorderHelper(root.left, result);
        postorderHelper(root.right, result);
        result.add(root.value);
    }

    public static List<Integer> levelOrder(Node root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }
}