class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }
}

class BinaryTree {
    TreeNode root;

    public int size() {
        return size(root);
    }

    private int size(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + size(node.left) + size(node.right);
    }

    public int sum() {
        return sum(root);
    }

    private int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.value + sum(node.left) + sum(node.right);
    }

    public int maximum() {
        if (root == null) {
            throw new IllegalStateException("Tree is empty");
        }

        return maximum(root);
    }

    private int maximum(TreeNode node) {
        int max = node.value;

        if (node.left != null) {
            max = Math.max(max, maximum(node.left));
        }

        if (node.right != null) {
            max = Math.max(max, maximum(node.right));
        }

        return max;
    }

    public int leafCount() {
        return leafCount(root);
    }

    private int leafCount(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return leafCount(node.left) + leafCount(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(TreeNode node, int value) {
        if (node == null) {
            return false;
        }

        if (node.value == value) {
            return true;
        }

        return contains(node.left, value)
                || contains(node.right, value);
    }
}

public class BinaryTreeStatistics {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.root = new TreeNode(10);
        tree.root.left = new TreeNode(5);
        tree.root.right = new TreeNode(20);

        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(7);

        tree.root.right.left = new TreeNode(15);
        tree.root.right.right = new TreeNode(30);

        System.out.println("size = " + tree.size());
        System.out.println("sum = " + tree.sum());
        System.out.println("maximum = " + tree.maximum());
        System.out.println("leaf count = " + tree.leafCount());
        System.out.println("height = " + tree.height());

        System.out.println("contains 15 = " + tree.contains(15));
        System.out.println("contains 99 = " + tree.contains(99));

        System.out.println();

        BinaryTree negativeTree = new BinaryTree();

        negativeTree.root = new TreeNode(-10);
        negativeTree.root.left = new TreeNode(-20);
        negativeTree.root.right = new TreeNode(-5);

        System.out.println("負數樹 maximum = "
                + negativeTree.maximum());

        System.out.println();

        BinaryTree emptyTree = new BinaryTree();

        System.out.println("Empty tree：");
        System.out.println("size = " + emptyTree.size());
        System.out.println("sum = " + emptyTree.sum());
        System.out.println("leaf count = " + emptyTree.leafCount());
        System.out.println("height = " + emptyTree.height());
        System.out.println("contains 10 = " + emptyTree.contains(10));

        try {
            System.out.println("maximum = " + emptyTree.maximum());
        } catch (IllegalStateException e) {
            System.out.println("maximum：無法取得，因為 tree 為 empty");
        }
    }
}