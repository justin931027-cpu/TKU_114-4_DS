class FolderNode {
    String name;
    int ownSize;
    FolderNode left;
    FolderNode right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {

    public static int subtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        int leftSize = subtreeSize(node.left);
        int rightSize = subtreeSize(node.right);

        int total = node.ownSize + leftSize + rightSize;

        System.out.println(node.name + " subtree size = " + total);

        return total;
    }

    public static FolderNode largestSubtree(FolderNode node) {
        if (node == null) {
            return null;
        }

        FolderNode largest = node;

        FolderNode leftLargest = largestSubtree(node.left);
        FolderNode rightLargest = largestSubtree(node.right);

        if (leftLargest != null &&
                getSubtreeSize(leftLargest) > getSubtreeSize(largest)) {
            largest = leftLargest;
        }

        if (rightLargest != null &&
                getSubtreeSize(rightLargest) > getSubtreeSize(largest)) {
            largest = rightLargest;
        }

        return largest;
    }

    private static int getSubtreeSize(FolderNode node) {
        if (node == null) {
            return 0;
        }

        return node.ownSize
                + getSubtreeSize(node.left)
                + getSubtreeSize(node.right);
    }

    public static void printLeafFolders(FolderNode node) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            System.out.println(
                    node.name + " ownSize = " + node.ownSize);
            return;
        }

        printLeafFolders(node.left);
        printLeafFolders(node.right);
    }

    public static void main(String[] args) {

        FolderNode root = new FolderNode("Root", 10);

        root.left = new FolderNode("Documents", 20);
        root.right = new FolderNode("Media", 30);

        root.left.left = new FolderNode("School", 40);
        root.left.right = new FolderNode("Work", 50);

        root.right.left = new FolderNode("Photos", 60);
        root.right.right = new FolderNode("Videos", 100);

        System.out.println("Postorder subtree size：");
        int total = subtreeSize(root);

        System.out.println();
        System.out.println("總大小 = " + total);

        FolderNode largest = largestSubtree(root);

        System.out.println(
                "最大 subtree = "
                        + largest.name
                        + ", size = "
                        + getSubtreeSize(largest));

        System.out.println();
        System.out.println("Leaf folders：");
        printLeafFolders(root);
    }
}
