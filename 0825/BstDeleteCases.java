public class BstDeleteCases {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root;

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        return node;
    }

    static void insert(int key) {
        root = insert(root, key);
    }

    static Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null && node.right == null)
                return null;

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor = minNode(node.right);
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }

        return node;
    }

    static void delete(int key) {
        root = delete(root, key);
    }

    static Node minNode(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    static int size(Node node) {
        if (node == null)
            return 0;

        return 1 + size(node.left) + size(node.right);
    }

    static boolean isValid(Node node, long min, long max) {
        if (node == null)
            return true;

        if (node.key <= min || node.key >= max)
            return false;

        return isValid(node.left, min, node.key)
                && isValid(node.right, node.key, max);
    }

    static boolean isValid() {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    static void report() {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.println("Size: " + size(root));
        System.out.println("Valid: " + isValid());
        System.out.println();
    }

    public static void main(String[] args) {
        int[] values = {50, 30, 70, 20, 40, 60, 80, 65};

        for (int value : values)
            insert(value);

        System.out.println("=== Original Tree ===");
        report();

        System.out.println("=== Delete Leaf: 20 ===");
        delete(20);
        report();

        System.out.println("=== Delete Single-Child Node: 60 ===");
        delete(60);
        report();

        System.out.println("=== Delete Two-Child Node: 70 ===");
        delete(70);
        report();
    }
}