public class BstDeleteTestSuite {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
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

    static Node delete(Node node, int key) {
        if (node == null)
            return null;

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
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

    static Node minNode(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    static void report(Node root) {
        if (root == null) {
            System.out.println("Tree: empty");
        } else {
            System.out.print("Tree: ");
            inorder(root);
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Test 1: Empty Tree ===");
        Node root = null;
        root = delete(root, 10);
        report(root);

        System.out.println("\n=== Test 2: Missing Key ===");
        root = null;
        root = insert(root, 50);
        root = insert(root, 30);
        root = insert(root, 70);

        root = delete(root, 100);
        report(root);

        System.out.println("\n=== Test 3: Single Root ===");
        root = null;
        root = insert(root, 50);
        report(root);

        root = delete(root, 50);
        report(root);

        System.out.println("\n=== Test 4: Root With One Child ===");
        root = null;
        root = insert(root, 50);
        root = insert(root, 30);

        report(root);

        root = delete(root, 50);
        report(root);

        System.out.println("\n=== Test 5: Root With Two Children ===");
        root = null;

        int[] values = {50, 30, 70, 20, 40, 60, 80};

        for (int value : values)
            root = insert(root, value);

        report(root);

        root = delete(root, 50);
        report(root);

        System.out.println("\n=== Test 6: Delete Until Empty ===");

        int[] deleteOrder = {
            20, 40, 30, 60, 80, 70
        };

        for (int value : deleteOrder) {
            System.out.println("Delete: " + value);
            root = delete(root, value);
            report(root);
        }
    }
}