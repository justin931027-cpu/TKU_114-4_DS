public class BstOperationAudit {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root;

    static boolean add(int key) {
        if (root == null) {
            root = new Node(key);
            audit("add " + key, true);
            return true;
        }

        Node current = root;

        while (true) {
            if (key == current.key) {
                audit("add " + key + " (duplicate)", false);
                return false;
            }

            if (key < current.key) {
                if (current.left == null) {
                    current.left = new Node(key);
                    audit("add " + key, true);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(key);
                    audit("add " + key, true);
                    return true;
                }

                current = current.right;
            }
        }
    }

    static boolean contains(int key) {
        Node current = root;

        while (current != null) {
            if (key == current.key)
                return true;

            if (key < current.key)
                current = current.left;
            else
                current = current.right;
        }

        return false;
    }

    static boolean remove(int key) {
        if (!contains(key)) {
            audit("remove " + key + " (missing)", false);
            return false;
        }

        root = delete(root, key);
        audit("remove " + key, true);

        return true;
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

            node.right = delete(
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

    static int size(Node node) {
        if (node == null)
            return 0;

        return 1 +
               size(node.left) +
               size(node.right);
    }

    static int height(Node node) {
        if (node == null)
            return 0;

        return 1 +
               Math.max(
                   height(node.left),
                   height(node.right)
               );
    }

    static boolean valid(
        Node node,
        long min,
        long max
    ) {
        if (node == null)
            return true;

        if (node.key <= min || node.key >= max)
            return false;

        return valid(
                   node.left,
                   min,
                   node.key
               )
               &&
               valid(
                   node.right,
                   node.key,
                   max
               );
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    static void audit(
        String operation,
        boolean result
    ) {
        System.out.println(
            "Operation: " + operation
        );

        System.out.println(
            "Result: " + result
        );

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.println(
            "Size: " + size(root)
        );

        System.out.println(
            "Height: " + height(root)
        );

        System.out.println(
            "Valid: " +
            valid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
            )
        );

        System.out.println();
    }

    public static void main(String[] args) {
        add(50);
        add(30);
        add(70);
        add(20);
        add(40);
        add(60);
        add(80);
        add(65);

        add(30);

        remove(999);

        remove(20);

        remove(60);

        remove(70);
    }
}