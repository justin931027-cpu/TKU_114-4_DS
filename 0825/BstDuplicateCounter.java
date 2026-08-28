public class BstDuplicateCounter {

    static class Node {
        int key;
        int count;
        Node left, right;

        Node(int key) {
            this.key = key;
            count = 1;
        }
    }

    static Node root;

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            node.count++;
        }

        return node;
    }

    static void insert(int key) {
        root = insert(root, key);
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + "(" + node.count + ") ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        int[] values = {
            50, 30, 70, 20, 40, 60, 80,
            30, 30, 50, 60, 60, 60, 80
        };

        for (int value : values)
            insert(value);

        System.out.println("Inorder:");
        inorder(root);
        System.out.println();
    }
}