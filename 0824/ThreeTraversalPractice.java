class TreeNode {
    char value;
    TreeNode left;
    TreeNode right;

    TreeNode(char value) {
        this.value = value;
    }
}

public class ThreeTraversalPractice {

    public static void preorder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public static void postorder(TreeNode node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode('M');

        root.left = new TreeNode('F');
        root.right = new TreeNode('T');

        root.left.left = new TreeNode('B');

        root.right.left = new TreeNode('R');
        root.right.right = new TreeNode('Z');

        System.out.print("Preorder: ");
        preorder(root);
        System.out.println();

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        System.out.print("Postorder: ");
        postorder(root);
        System.out.println();

        System.out.println();

        System.out.print("null 測試 preorder: ");
        preorder(null);
        System.out.println();

        System.out.print("null 測試 inorder: ");
        inorder(null);
        System.out.println();

        System.out.print("null 測試 postorder: ");
        postorder(null);
        System.out.println();
    }
}
