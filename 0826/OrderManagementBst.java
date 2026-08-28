public class OrderManagementBst {

    static class Order {
        int orderId;
        String customer;
        double amount;
        String status;

        Order(int orderId, String customer, double amount, String status) {
            this.orderId = orderId;
            this.customer = customer;
            this.amount = amount;
            this.status = status;
        }

        public String toString() {
            return "OrderID=" + orderId +
                   ", Customer=" + customer +
                   ", Amount=" + amount +
                   ", Status=" + status;
        }
    }

    static class Node {
        Order order;
        Node left;
        Node right;

        Node(Order order) {
            this.order = order;
        }
    }

    static Node root;

    static boolean add(Order order) {
        if (order.amount < 0) {
            return false;
        }

        if (root == null) {
            root = new Node(order);
            return true;
        }

        Node current = root;

        while (true) {
            if (order.orderId == current.order.orderId) {
                return false;
            }

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
            if (orderId == current.order.orderId) {
                return current.order;
            }

            if (orderId < current.order.orderId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    static boolean updateStatus(int orderId, String newStatus) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (newStatus == null || newStatus.isBlank()) {
            return false;
        }

        order.status = newStatus;
        return true;
    }

    static boolean cancel(int orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        order.status = "CANCELLED";
        return true;
    }

    static boolean remove(int orderId) {
        Order order = find(orderId);

        if (order == null) {
            return false;
        }

        if (!order.status.equals("CANCELLED")) {
            return false;
        }

        root = remove(root, orderId);
        return true;
    }

    static Node remove(Node node, int orderId) {
        if (node == null) {
            return null;
        }

        if (orderId < node.order.orderId) {
            node.left = remove(node.left, orderId);

        } else if (orderId > node.order.orderId) {
            node.right = remove(node.right, orderId);

        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = minNode(node.right);

            node.order = successor.order;

            node.right = remove(
                node.right,
                successor.order.orderId
            );
        }

        return node;
    }

    static Node minNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    static void rangeReport(Node node, int low, int high) {
        if (node == null) {
            return;
        }

        if (node.order.orderId > low) {
            rangeReport(node.left, low, high);
        }

        if (node.order.orderId >= low &&
            node.order.orderId <= high) {

            System.out.println(node.order);
        }

        if (node.order.orderId < high) {
            rangeReport(node.right, low, high);
        }
    }

    static void rangeReport(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        System.out.println(
            "=== ID Range " + low + " ~ " + high + " ==="
        );

        rangeReport(root, low, high);
    }

    static double totalAmount(Node node) {
        if (node == null) {
            return 0;
        }

        return node.order.amount +
               totalAmount(node.left) +
               totalAmount(node.right);
    }

    static double totalAmount() {
        return totalAmount(root);
    }

    static void inorderReport(Node node) {
        if (node == null) {
            return;
        }

        inorderReport(node.left);

        System.out.println(node.order);

        inorderReport(node.right);
    }

    public static void main(String[] args) {

        System.out.println("=== Add Orders ===");

        System.out.println(
            add(new Order(1003, "Amy", 1500, "NEW"))
        );

        System.out.println(
            add(new Order(1001, "Ben", 800, "NEW"))
        );

        System.out.println(
            add(new Order(1005, "Cindy", 2300, "PAID"))
        );

        System.out.println(
            add(new Order(1002, "David", 1200, "NEW"))
        );

        System.out.println(
            add(new Order(1004, "Eric", 900, "SHIPPED"))
        );

        System.out.println("\n=== Negative Amount ===");

        System.out.println(
            add(new Order(1006, "Frank", -500, "NEW"))
        );

        System.out.println("\n=== Duplicate ID ===");

        System.out.println(
            add(new Order(1003, "Gary", 999, "NEW"))
        );

        System.out.println("\n=== Find 1002 ===");

        System.out.println(
            find(1002)
        );

        System.out.println("\n=== Update Status ===");

        System.out.println(
            updateStatus(1002, "PAID")
        );

        System.out.println(
            find(1002)
        );

        System.out.println("\n=== Range Report ===");

        rangeReport(1002, 1004);

        System.out.println("\n=== Total Amount ===");

        System.out.println(
            "Total Amount = " + totalAmount()
        );

        System.out.println("\n=== Try Remove Active Order ===");

        System.out.println(
            remove(1001)
        );

        System.out.println("\n=== Cancel Order 1001 ===");

        System.out.println(
            cancel(1001)
        );

        System.out.println(
            find(1001)
        );

        System.out.println("\n=== Remove Cancelled Order ===");

        System.out.println(
            remove(1001)
        );

        System.out.println("\n=== Final Inorder Report ===");

        inorderReport(root);

        System.out.println("\n=== Final Total Amount ===");

        System.out.println(
            "Total Amount = " + totalAmount()
        );
    }
}