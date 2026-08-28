import java.util.ArrayList;

public class CompleteBstTestSuite {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root;

    static int pass = 0;
    static int fail = 0;

    static void check(
        String description,
        boolean condition
    ) {

        if (condition) {
            System.out.println(
                "PASS: " + description
            );
            pass++;
        } else {
            System.out.println(
                "FAIL: " + description
            );
            fail++;
        }
    }

    static boolean add(int key) {

        if (root == null) {
            root = new Node(key);
            return true;
        }

        Node current = root;

        while (true) {

            if (key == current.key)
                return false;

            if (key < current.key) {

                if (current.left == null) {
                    current.left =
                        new Node(key);

                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right =
                        new Node(key);

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

        if (!contains(key))
            return false;

        root = remove(root, key);

        return true;
    }

    static Node remove(
        Node node,
        int key
    ) {

        if (node == null)
            return null;

        if (key < node.key) {

            node.left =
                remove(node.left, key);

        } else if (key > node.key) {

            node.right =
                remove(node.right, key);

        } else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor =
                minNode(node.right);

            node.key = successor.key;

            node.right =
                remove(
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

        if (
            node.key <= min ||
            node.key >= max
        )
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

    static ArrayList<Integer> range(
        Node node,
        int low,
        int high
    ) {

        ArrayList<Integer> result =
            new ArrayList<>();

        range(
            node,
            low,
            high,
            result
        );

        return result;
    }

    static void range(
        Node node,
        int low,
        int high,
        ArrayList<Integer> result
    ) {

        if (node == null)
            return;

        if (node.key > low)
            range(
                node.left,
                low,
                high,
                result
            );

        if (
            node.key >= low &&
            node.key <= high
        )
            result.add(node.key);

        if (node.key < high)
            range(
                node.right,
                low,
                high,
                result
            );
    }

    public static void main(String[] args) {

        root = null;

        check(
            "1. Empty tree size",
            size(root) == 0
        );

        check(
            "2. Empty tree height",
            height(root) == 0
        );

        check(
            "3. Missing in empty tree",
            !contains(50)
        );

        check(
            "4. Remove missing from empty",
            !remove(50)
        );

        check(
            "5. Add root",
            add(50)
        );

        check(
            "6. Root exists",
            contains(50)
        );

        check(
            "7. Root size is 1",
            size(root) == 1
        );

        check(
            "8. Duplicate rejected",
            !add(50)
        );

        check(
            "9. Size unchanged after duplicate",
            size(root) == 1
        );

        check(
            "10. Add left child",
            add(30)
        );

        check(
            "11. Add right child",
            add(70)
        );

        check(
            "12. Add leaf 20",
            add(20)
        );

        check(
            "13. Add leaf 40",
            add(40)
        );

        check(
            "14. Add 60",
            add(60)
        );

        check(
            "15. Add 80",
            add(80)
        );

        check(
            "16. Tree size is 7",
            size(root) == 7
        );

        check(
            "17. Tree is valid",
            valid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
            )
        );

        check(
            "18. Missing key",
            !contains(999)
        );

        check(
            "19. Remove missing",
            !remove(999)
        );

        check(
            "20. Range count",
            range(
                root,
                30,
                70
            ).size() == 5
        );

        check(
            "21. Remove leaf",
            remove(20)
        );

        check(
            "22. Leaf removed",
            !contains(20)
        );

        root = null;

        add(50);
        add(30);

        check(
            "23. Remove root with one child",
            remove(50)
        );

        check(
            "24. Child becomes root",
            root != null &&
            root.key == 30
        );

        root = null;

        add(50);
        add(30);
        add(70);

        check(
            "25. Remove root with two children",
            remove(50)
        );

        check(
            "26. Removed root is gone",
            !contains(50)
        );

        check(
            "27. Tree still valid after delete",
            valid(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
            )
        );

        Node invalid = new Node(50);
        invalid.left = new Node(30);
        invalid.left.right =
            new Node(60);

        check(
            "28. Detect invariant violation",
            !valid(
                invalid,
                Long.MIN_VALUE,
                Long.MAX_VALUE
            )
        );

        System.out.println();
        System.out.println("=== Test Summary ===");

        System.out.println(
            "PASS = " + pass
        );

        System.out.println(
            "FAIL = " + fail
        );

        System.out.println(
            "TOTAL = " + (pass + fail)
        );
    }
}