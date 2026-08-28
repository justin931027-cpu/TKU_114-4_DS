public class BstSearchTrace {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root;

    static void insert(int key) {
        root = insert(root, key);
    }

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        return node;
    }

    static boolean searchTrace(int target) {
        Node current = root;
        int count = 0;

        System.out.println("Search: " + target);

        while (current != null) {
            count++;

            System.out.print("Current value: " + current.key);

            if (target == current.key) {
                System.out.println(" -> FOUND");
                System.out.println("Comparison count: " + count);
                return true;
            } else if (target < current.key) {
                System.out.println(" -> LEFT");
                current = current.left;
            } else {
                System.out.println(" -> RIGHT");
                current = current.right;
            }
        }

        System.out.println("Missing value");
        System.out.println("Comparison count: " + count);
        return false;
    }

    public static void main(String[] args) {
        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values)
            insert(value);

        System.out.println("=== Root ===");
        searchTrace(50);

        System.out.println("\n=== Leaf ===");
        searchTrace(20);

        System.out.println("\n=== Internal Node ===");
        searchTrace(70);

        System.out.println("\n=== Missing Value ===");
        searchTrace(65);
    }
}