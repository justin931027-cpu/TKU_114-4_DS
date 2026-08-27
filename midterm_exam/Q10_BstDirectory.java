import java.util.ArrayList;
import java.util.List;

public class Q10_BstDirectory {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }

        Node current = root;

        while (true) {
            if (value == current.value) {
                return false;
            }

            if (value < current.value) {
                if (current.left == null) {
                    current.left = new Node(value);
                    size++;
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(value);
                    size++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public boolean contains(int value) {
        Node current = root;

        while (current != null) {
            if (value == current.value) {
                return true;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public List<Integer> searchPath(int target) {
        List<Integer> result = new ArrayList<>();

        Node current = root;

        while (current != null) {
            result.add(current.value);

            if (target == current.value) {
                return result;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        result.add(null);
        return result;
    }

    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.value);
        inorder(node.right, result);
    }

    public boolean isValid() {
        return isValid(root, null, null);
    }

    private boolean isValid(Node node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }

        if (low != null && node.value <= low) {
            return false;
        }

        if (high != null && node.value >= high) {
            return false;
        }

        return isValid(node.left, low, node.value)
                && isValid(node.right, node.value, high);
    }
}