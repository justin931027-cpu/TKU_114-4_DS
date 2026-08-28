public class BstShapeExperiment {

    static class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    static class BST {
        Node root;

        void insert(int key) {
            root = insert(root, key);
        }

        Node insert(Node node, int key) {
            if (node == null)
                return new Node(key);

            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);

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
        String name,
        int[] insertOrder,
        int[] searchValues
    ) {

        BST tree = new BST();

        for (int value : insertOrder)
            tree.insert(value);

        System.out.println("=== " + name + " ===");

        System.out.println("Height: " + tree.height());

        int total = 0;

        for (int value : searchValues) {
            int count = tree.searchComparisons(value);

            System.out.println(
                "Search " + value +
                ": " + count +
                " comparisons"
            );

            total += count;
        }

        System.out.println(
            "Total comparisons: " + total
        );

        System.out.println(
            "Average comparisons: " +
            (double) total / searchValues.length
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] values = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] sortedOrder = {
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15
        };

        int[] reverseOrder = {
            15, 14, 13, 12, 11,
            10, 9, 8, 7, 6,
            5, 4, 3, 2, 1
        };

        int[] balancedOrder = {
            8,
            4, 12,
            2, 6, 10, 14,
            1, 3, 5, 7,
            9, 11, 13, 15
        };

        report(
            "Sorted Order",
            sortedOrder,
            values
        );

        report(
            "Reverse Order",
            reverseOrder,
            values
        );

        report(
            "Balanced Order",
            balancedOrder,
            values
        );
    }
}