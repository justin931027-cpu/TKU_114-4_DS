public class ProductInventoryBst {

    static class Product {
        int id;
        String name;
        int stock;

        Product(int id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }

        public String toString() {
            return "ID=" + id +
                   ", Name=" + name +
                   ", Stock=" + stock;
        }
    }

    static class Node {
        Product product;
        Node left, right;

        Node(Product product) {
            this.product = product;
        }
    }

    static Node root;

    static boolean add(Product product) {
        if (root == null) {
            root = new Node(product);
            return true;
        }

        Node current = root;

        while (true) {
            if (product.id == current.product.id)
                return false;

            if (product.id < current.product.id) {
                if (current.left == null) {
                    current.left = new Node(product);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(product);
                    return true;
                }

                current = current.right;
            }
        }
    }

    static Product find(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.product.id)
                return current.product;

            if (id < current.product.id)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    static boolean restock(int id, int amount) {
        Product product = find(id);

        if (product == null || amount <= 0)
            return false;

        product.stock += amount;
        return true;
    }

    static boolean reduceStock(int id, int amount) {
        Product product = find(id);

        if (product == null || amount <= 0)
            return false;

        if (product.stock < amount)
            return false;

        product.stock -= amount;
        return true;
    }

    static void delete(int id) {
        root = delete(root, id);
    }

    static Node delete(Node node, int id) {
        if (node == null)
            return null;

        if (id < node.product.id) {
            node.left = delete(node.left, id);
        } else if (id > node.product.id) {
            node.right = delete(node.right, id);
        } else {
            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor = minNode(node.right);
            node.product = successor.product;
            node.right = delete(node.right, successor.product.id);
        }

        return node;
    }

    static Node minNode(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    static void inorderReport(Node node) {
        if (node != null) {
            inorderReport(node.left);
            System.out.println(node.product);
            inorderReport(node.right);
        }
    }

    public static void main(String[] args) {
        add(new Product(1003, "Keyboard", 20));
        add(new Product(1001, "Mouse", 30));
        add(new Product(1005, "Monitor", 10));
        add(new Product(1002, "USB", 50));

        System.out.println("=== Initial Inventory ===");
        inorderReport(root);

        System.out.println("\nFind 1001:");
        System.out.println(find(1001));

        System.out.println("\nRestock 1001 + 20");
        restock(1001, 20);
        System.out.println(find(1001));

        System.out.println("\nReduce 1002 - 10");
        reduceStock(1002, 10);
        System.out.println(find(1002));

        System.out.println("\nDelete 1003");
        delete(1003);

        System.out.println("\n=== Final Inventory ===");
        inorderReport(root);
    }
}