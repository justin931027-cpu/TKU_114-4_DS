public class OrderBstSystem {

    static class Order {
        int orderId;
        String customer;
        double amount;

        Order(int orderId, String customer, double amount) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
        }

        public String toString() {
            return "OrderID=" + orderId +
                   ", Customer=" + customer +
                   ", Amount=" + amount;
        }
    }

    static class Node {
        Order order;
        Node left, right;

        Node(Order order) {
            this.order = order;
        }
    }

    static Node root;

    static boolean add(Order order) {
        if (root == null) {
            root = new Node(order);
            return true;
        }

        Node current = root;

        while (true) {
            if (order.orderId == current.order.orderId)
                return false;

            if (order.orderId < current.order.orderId) {

                if (current.left == null) {
                    current.left = new Node(order);
                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node(order);
                    return true;
                }

                current = current.right;
            }
        }
    }

    static Order find(int orderId) {
        Node current = root;

        while (current != null) {

            if (orderId == current.order.orderId)
                return current.order;

            if (orderId < current.order.orderId)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    static boolean updateAmount(
        int orderId,
        double newAmount
    ) {

        Order order = find(orderId);

        if (order == null || newAmount < 0)
            return false;

        order.amount = newAmount;
        return true;
    }

    static boolean cancel(int orderId) {
        if (find(orderId) == null)
            return false;

        root = delete(root, orderId);
        return true;
    }

    static Node delete(Node node, int orderId) {

        if (node == null)
            return null;

        if (orderId < node.order.orderId) {

            node.left = delete(
                node.left,
                orderId
            );

        } else if (
            orderId > node.order.orderId
        ) {

            node.right = delete(
                node.right,
                orderId
            );

        } else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor =
                minNode(node.right);

            node.order =
                successor.order;

            node.right =
                delete(
                    node.right,
                    successor.order.orderId
                );
        }

        return node;
    }

    static Node minNode(Node node) {

        while (node.left != null)
            node = node.left;

        return node;
    }

    static void rangeReport(
        Node node,
        int low,
        int high
    ) {

        if (node == null)
            return;

        if (node.order.orderId >= low)
            rangeReport(
                node.left,
                low,
                high
            );

        if (
            node.order.orderId >= low &&
            node.order.orderId <= high
        ) {

            System.out.println(node.order);
        }

        if (node.order.orderId <= high)
            rangeReport(
                node.right,
                low,
                high
            );
    }

    static void rangeReport(
        int low,
        int high
    ) {

        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        System.out.println(
            "Order Range: " +
            low + " ~ " + high
        );

        rangeReport(
            root,
            low,
            high
        );
    }

    static int count(Node node) {

        if (node == null)
            return 0;

        return 1 +
               count(node.left) +
               count(node.right);
    }

    static double totalAmount(Node node) {

        if (node == null)
            return 0;

        return node.order.amount +
               totalAmount(node.left) +
               totalAmount(node.right);
    }

    static void summary() {

        int count = count(root);
        double total = totalAmount(root);

        System.out.println("=== Summary ===");

        System.out.println(
            "Order Count: " + count
        );

        System.out.println(
            "Total Amount: " + total
        );

        if (count > 0) {
            System.out.println(
                "Average Amount: " +
                total / count
            );
        }
    }

    public static void main(String[] args) {

        add(new Order(1005, "Amy", 1500));
        add(new Order(1002, "Ben", 800));
        add(new Order(1008, "Cindy", 2200));
        add(new Order(1001, "David", 500));
        add(new Order(1003, "Eric", 1200));
        add(new Order(1006, "Frank", 1800));

        System.out.println("Find 1003:");
        System.out.println(find(1003));

        System.out.println(
            "\nUpdate 1003 Amount:"
        );

        updateAmount(1003, 2000);

        System.out.println(find(1003));

        System.out.println(
            "\nRange Report:"
        );

        rangeReport(1002, 1006);

        System.out.println(
            "\nCancel Order 1002"
        );

        cancel(1002);

        System.out.println();
        summary();
    }
}