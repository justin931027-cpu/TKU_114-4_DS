public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        Node(String value) {
            this.value = value;
        }

        Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static void preorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null)
            return;

        if (node.left == null && node.right == null) {
            System.out.print(node.value);
            return;
        }

        System.out.print("(");
        inorder(node.left);
        System.out.print(" " + node.value + " ");
        inorder(node.right);
        System.out.print(")");
    }

    static void postorder(Node node) {
        if (node == null)
            return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        Node plus = new Node("+", a, b);
        Node minus = new Node("-", c, d);

        Node root = new Node("*", plus, minus);

        System.out.print("Prefix  : ");
        preorder(root);
        System.out.println();

        System.out.print("Infix   : ");
        inorder(root);
        System.out.println();

        System.out.print("Postfix : ");
        postorder(root);
        System.out.println();
    }
}