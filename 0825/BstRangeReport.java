public class BstRangeReport {

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

    static int min() {
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        Node current = root;

        while (current.left != null)
            current = current.left;

        return current.key;
    }

    static int max() {
        if (root == null)
            throw new IllegalStateException("Tree is empty");

        Node current = root;

        while (current.right != null)
            current = current.right;

        return current.key;
    }

    static void printRange(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        System.out.print("Range [" + low + ", " + high + "]: ");
        printRange(root, low, high);
        System.out.println();
    }

    static void printRange(Node node, int low, int high) {
        if (node == null)
            return;

        if (node.key > low)
            printRange(node.left, low, high);

        if (node.key >= low && node.key <= high)
            System.out.print(node.key + " ");

        if (node.key < high)
            printRange(node.right, low, high);
    }

    public static void main(String[] args) {
        int[] values = {50, 30, 70, 20, 40, 60, 80, 10, 90};

        for (int value : values)
            insert(value);

        System.out.println("Min: " + min());
        System.out.println("Max: " + max());

        printRange(30, 70);
        printRange(20, 50);
        printRange(75, 35);
    }
}