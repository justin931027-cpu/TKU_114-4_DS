public class TreeBugLab {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static boolean buggySearch(
        Node node,
        int key
    ) {
        while (node != null) {
            if (key == node.key)
                return true;

            if (key < node.key)
                node = node.right;
            else
                node = node.left;
        }

        return false;
    }

    static boolean fixedSearch(
        Node node,
        int key
    ) {
        while (node != null) {
            if (key == node.key)
                return true;

            if (key < node.key)
                node = node.left;
            else
                node = node.right;
        }

        return false;
    }

    static void buggyInorder(Node node) {
        if (node == null)
            return;

        buggyInorder(node.right);
        System.out.print(node.key + " ");
        buggyInorder(node.left);
    }

    static void fixedInorder(Node node) {
        if (node == null)
            return;

        fixedInorder(node.left);
        System.out.print(node.key + " ");
        fixedInorder(node.right);
    }

    static Node buggyDelete(
        Node node,
        int key
    ) {
        if (node == null)
            return null;

        if (key < node.key) {
            node.left =
                buggyDelete(node.left, key);

        } else if (key > node.key) {
            node.right =
                buggyDelete(node.right, key);

        } else {
            if (
                node.left == null ||
                node.right == null
            ) {
                return null;
            }
        }

        return node;
    }

    static Node fixedDelete(
        Node node,
        int key
    ) {
        if (node == null)
            return null;

        if (key < node.key) {
            node.left =
                fixedDelete(node.left, key);

        } else if (key > node.key) {
            node.right =
                fixedDelete(node.right, key);

        } else {
            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor =
                minNode(node.right);

            node.key =
                successor.key;

            node.right =
                fixedDelete(
                    node.right,
                    successor.key
                );
        }

        return node;
    }

    static Node minNode(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    static boolean buggyValidation(
        Node node
    ) {
        if (node == null)
            return true;

        if (
            node.left != null &&
            node.left.key >= node.key
        )
            return false;

        if (
            node.right != null &&
            node.right.key <= node.key
        )
            return false;

        return buggyValidation(node.left)
            && buggyValidation(node.right);
    }

    static boolean fixedValidation(
        Node node
    ) {
        return fixedValidation(
            node,
            Long.MIN_VALUE,
            Long.MAX_VALUE
        );
    }

    static boolean fixedValidation(
        Node node,
        long min,
        long max
    ) {
        if (node == null)
            return true;

        if (
            node.key <= min ||
            node.key >= max
        )
            return false;

        return fixedValidation(
                   node.left,
                   min,
                   node.key
               )
               &&
               fixedValidation(
                   node.right,
                   node.key,
                   max
               );
    }

    static void testSearchBug() {
        System.out.println(
            "=== Bug 1: Search Direction ==="
        );

        Node root = new Node(10);
        root.left = new Node(5);

        System.out.println(
            "Buggy search 5: " +
            buggySearch(root, 5)
        );

        System.out.println(
            "Fixed search 5: " +
            fixedSearch(root, 5)
        );

        System.out.println();
    }

    static void testInorderBug() {
        System.out.println(
            "=== Bug 2: Inorder Order ==="
        );

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);

        System.out.print("Buggy: ");
        buggyInorder(root);
        System.out.println();

        System.out.print("Fixed: ");
        fixedInorder(root);
        System.out.println();

        System.out.println();
    }

    static void testDeleteBug() {
        System.out.println(
            "=== Bug 3: Delete Loses Child ==="
        );

        Node buggyRoot = new Node(10);
        buggyRoot.right = new Node(20);

        buggyRoot =
            buggyDelete(buggyRoot, 10);

        System.out.print("Buggy after delete 10: ");

        if (buggyRoot == null)
            System.out.println("empty");
        else {
            fixedInorder(buggyRoot);
            System.out.println();
        }

        Node fixedRoot = new Node(10);
        fixedRoot.right = new Node(20);

        fixedRoot =
            fixedDelete(fixedRoot, 10);

        System.out.print("Fixed after delete 10: ");

        if (fixedRoot == null)
            System.out.println("empty");
        else {
            fixedInorder(fixedRoot);
            System.out.println();
        }

        System.out.println();
    }

    static void testValidationBug() {
        System.out.println(
            "=== Bug 4: Validation ==="
        );

        Node root = new Node(10);

        root.left = new Node(5);
        root.right = new Node(20);

        root.left.right =
            new Node(15);

        System.out.println(
            "Buggy validation: " +
            buggyValidation(root)
        );

        System.out.println(
            "Fixed validation: " +
            fixedValidation(root)
        );

        System.out.println();
    }

    public static void main(String[] args) {
        testSearchBug();
        testInorderBug();
        testDeleteBug();
        testValidationBug();
    }
}