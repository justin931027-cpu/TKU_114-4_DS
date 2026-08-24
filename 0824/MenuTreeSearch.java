class MenuNode {
    String name;
    MenuNode left;
    MenuNode right;

    MenuNode(String name) {
        this.name = name;
    }
}

public class MenuTreeSearch {

    public static boolean contains(MenuNode node, String target) {
        if (node == null) {
            return false;
        }

        if (node.name.equals(target)) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    public static int findDepth(MenuNode node, String target) {
        return findDepth(node, target, 0);
    }

    private static int findDepth(MenuNode node, String target, int depth) {
        if (node == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftDepth = findDepth(node.left, target, depth + 1);

        if (leftDepth != -1) {
            return leftDepth;
        }

        return findDepth(node.right, target, depth + 1);
    }

    public static int countLeaves(MenuNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    public static void preorder(MenuNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.name + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void main(String[] args) {

        MenuNode root = new MenuNode("Main");

        root.left = new MenuNode("File");
        root.right = new MenuNode("Edit");

        root.left.left = new MenuNode("New");
        root.left.right = new MenuNode("Open");

        root.right.left = new MenuNode("Copy");
        root.right.right = new MenuNode("Paste");

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.println("contains Open = "
                + contains(root, "Open"));

        System.out.println("contains Save = "
                + contains(root, "Save"));

        System.out.println("Depth of Main = "
                + findDepth(root, "Main"));

        System.out.println("Depth of Open = "
                + findDepth(root, "Open"));

        System.out.println("Depth of Save = "
                + findDepth(root, "Save"));

        System.out.println("Leaf count = "
                + countLeaves(root));
    }
}