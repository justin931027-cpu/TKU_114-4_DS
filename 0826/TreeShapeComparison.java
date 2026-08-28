public class TreeShapeComparison {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static class BST {

        Node root;

        void add(int key) {
            root = add(root, key);
        }

        Node add(Node node, int key) {

            if (node == null)
                return new Node(key);

            if (key < node.key)
                node.left =
                    add(node.left, key);

            else if (key > node.key)
                node.right =
                    add(node.right, key);

            return node;
        }

        int height() {
            return height(root);
        }

        int height(Node node) {

            if (node == null)
                return 0;

            return 1 +
                   Math.max(
                       height(node.left),
                       height(node.right)
                   );
        }

        int searchComparisons(int key) {

            Node current = root;

            int count = 0;

            while (current != null) {

                count++;

                if (key == current.key)
                    return count;

                if (key < current.key)
                    current = current.left;
                else
                    current = current.right;
            }

            return count;
        }
    }

    static void report(
        String title,
        int[] order,
        int[] keys,
        int missingKey
    ) {

        BST tree = new BST();

        for (int value : order)
            tree.add(value);

        int total = 0;

        for (int key : keys) {
            total +=
                tree.searchComparisons(key);
        }

        System.out.println(
            "=== " + title + " ==="
        );

        System.out.println(
            "Height: " +
            tree.height()
        );

        System.out.println(
            "All Key Search Comparison Total: " +
            total
        );

        System.out.println(
            "Missing Key " +
            missingKey +
            " Comparison Count: " +
            tree.searchComparisons(
                missingKey
            )
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] keys = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] ascending = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] descending = {
            15, 14, 13, 12, 11,
            10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };

        int[] nearlyBalanced = {
            8,
            4, 12,
            2, 6, 10, 14,
            1, 3, 5, 7,
            9, 11, 13, 15
        };

        int missingKey = 16;

        report(
            "Ascending",
            ascending,
            keys,
            missingKey
        );

        report(
            "Descending",
            descending,
            keys,
            missingKey
        );

        report(
            "Nearly Balanced",
            nearlyBalanced,
            keys,
            missingKey
        );
    }
}