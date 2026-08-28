import java.util.ArrayList;

public class BstRangeStatistics {

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    static Node root;

    static Node insert(Node node, int key) {
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        return node;
    }

    static void insert(int key) {
        root = insert(root, key);
    }

    static ArrayList<Integer> valuesBetween(
        int low,
        int high
    ) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        ArrayList<Integer> result =
            new ArrayList<>();

        valuesBetween(
            root,
            low,
            high,
            result
        );

        return result;
    }

    static void valuesBetween(
        Node node,
        int low,
        int high,
        ArrayList<Integer> result
    ) {
        if (node == null)
            return;

        if (node.key > low) {
            valuesBetween(
                node.left,
                low,
                high,
                result
            );
        }

        if (
            node.key >= low &&
            node.key <= high
        ) {
            result.add(node.key);
        }

        if (node.key < high) {
            valuesBetween(
                node.right,
                low,
                high,
                result
            );
        }
    }

    static int countBetween(
        int low,
        int high
    ) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        return countBetween(
            root,
            low,
            high
        );
    }

    static int countBetween(
        Node node,
        int low,
        int high
    ) {
        if (node == null)
            return 0;

        if (node.key < low)
            return countBetween(
                node.right,
                low,
                high
            );

        if (node.key > high)
            return countBetween(
                node.left,
                low,
                high
            );

        return 1
            + countBetween(
                node.left,
                low,
                high
            )
            + countBetween(
                node.right,
                low,
                high
            );
    }

    static int sumBetween(
        int low,
        int high
    ) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        return sumBetween(
            root,
            low,
            high
        );
    }

    static int sumBetween(
        Node node,
        int low,
        int high
    ) {
        if (node == null)
            return 0;

        if (node.key < low)
            return sumBetween(
                node.right,
                low,
                high
            );

        if (node.key > high)
            return sumBetween(
                node.left,
                low,
                high
            );

        return node.key
            + sumBetween(
                node.left,
                low,
                high
            )
            + sumBetween(
                node.right,
                low,
                high
            );
    }

    static void report(
        int low,
        int high
    ) {
        System.out.println(
            "Range: " + low + " ~ " + high
        );

        System.out.println(
            "Values: " +
            valuesBetween(low, high)
        );

        System.out.println(
            "Count: " +
            countBetween(low, high)
        );

        System.out.println(
            "Sum: " +
            sumBetween(low, high)
        );

        System.out.println();
    }

    public static void main(String[] args) {
        int[] values = {
            50, 30, 70,
            20, 40, 60, 80,
            10, 25, 35, 45,
            55, 65, 75, 90
        };

        for (int value : values)
            insert(value);

        report(30, 70);

        report(20, 45);

        report(65, 90);

        report(70, 30);
    }
}