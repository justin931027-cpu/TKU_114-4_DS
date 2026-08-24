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

    public void printLeaves() {
        printLeaves(root);
        System.out.println();
    }

    private void printLeaves(TreeNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.print(node.value + " ");
            return;
        }

        printLeaves(node.left);
        printLeaves(node.right);
    }
}

public class BinaryTreeStructureReport {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);

        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);

        tree.root.right.left = new TreeNode(6);
        tree.root.right.right = new TreeNode(7);

        System.out.println("一般 Binary Tree：");
        System.out.println("root = " + tree.root.value);

        System.out.print("所有 leaf = ");
        tree.printLeaves();

        System.out.println("size = " + tree.size());
        System.out.println("leaf count = " + tree.leafCount());
        System.out.println("height = " + tree.height());

        System.out.println();

        BinaryTree emptyTree = new BinaryTree();

        System.out.println("Empty Tree：");
        System.out.println("root = " + emptyTree.root);
        System.out.print("所有 leaf = ");
        emptyTree.printLeaves();
        System.out.println("size = " + emptyTree.size());
        System.out.println("leaf count = " + emptyTree.leafCount());
        System.out.println("height = " + emptyTree.height());

        System.out.println();

        BinaryTree singleTree = new BinaryTree();
        singleTree.root = new TreeNode(100);

        System.out.println("Single-node Tree：");
        System.out.println("root = " + singleTree.root.value);
        System.out.print("所有 leaf = ");
        singleTree.printLeaves();
        System.out.println("size = " + singleTree.size());
        System.out.println("leaf count = " + singleTree.leafCount());
        System.out.println("height = " + singleTree.height());
    }
}