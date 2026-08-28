public class BstInvariantChecker {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static boolean isValidBST(Node root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean isValidBST(Node node, long min, long max) {
        if (node == null)
            return true;

        if (node.key <= min || node.key >= max)
            return false;

        return isValidBST(node.left, min, node.key)
                && isValidBST(node.right, node.key, max);
    }

    static Node createValidTree() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.left = new Node(20);
        root.left.right = new Node(40);

        root.right.left = new Node(60);
        root.right.right = new Node(80);

        return root;
    }

    static Node createInvalidTree1() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.left.right = new Node(60);

        return root;
    }

    static Node createInvalidTree2() {
        Node root = new Node(50);

        root.left = new Node(30);
        root.right = new Node(70);

        root.right.left = new Node(40);

        return root;
    }

    static Node createInvalidTree3() {
        Node root = new Node(100);

        root.left = new Node(50);
        root.right = new Node(150);

        root.left.left = new Node(25);
        root.left.right = new Node(75);

        root.left.right.right = new Node(120);

        return root;
    }

    public static void main(String[] args) {
        Node validTree = createValidTree();
        Node invalidTree1 = createInvalidTree1();
        Node invalidTree2 = createInvalidTree2();
        Node invalidTree3 = createInvalidTree3();

        System.out.println(
            "Valid Tree: " + isValidBST(validTree)
        );

        System.out.println(
            "Invalid Tree 1: " + isValidBST(invalidTree1)
        );

        System.out.println(
            "Invalid Tree 2: " + isValidBST(invalidTree2)
        );

        System.out.println(
            "Invalid Tree 3: " + isValidBST(invalidTree3)
        );
    }
}