public class SkewedBstReport {

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

        int size() {
            return size(root);
        }

        int size(Node node) {
            if (node == null)
                return 0;

            return 1 + size(node.left) + size(node.right);
        }

        int height() {
            return height(root);
        }

        int height(Node node) {
            if (node == null)
                return 0;

            return 1 + Math.max(height(node.left), height(node.right));
        }

        int searchComparisons(int target) {
            Node current = root;
            int count = 0;

            while (current != null) {
                count++;

                if (target == current.key)
                    return count;

                if (target < current.key)
                    current = current.left;
                else
                    current = current.right;
            }

            return count;
        }
    }

    public static void main(String[] args) {
        BST skewedTree = new BST();
        BST balancedTree = new BST();

        int[] sortedData = {
            10, 20, 30, 40, 50, 60, 70
        };

        int[] balancedOrder = {
            40, 20, 60, 10, 30, 50, 70
        };

        for (int value : sortedData)
            skewedTree.insert(value);

        for (int value : balancedOrder)
            balancedTree.insert(value);

        int target = 70;

        System.out.println("=== Sorted Data Tree ===");
        System.out.println("Size: " + skewedTree.size());
        System.out.println("Height: " + skewedTree.height());
        System.out.println(
            "Search " + target + " comparison count: "
            + skewedTree.searchComparisons(target)
        );

        System.out.println();

        System.out.println("=== Balanced Order Tree ===");
        System.out.println("Size: " + balancedTree.size());
        System.out.println("Height: " + balancedTree.height());
        System.out.println(
            "Search " + target + " comparison count: "
            + balancedTree.searchComparisons(target)
        );
    }
}