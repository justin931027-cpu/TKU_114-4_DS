import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class OrgNode {
    String name;
    OrgNode left;
    OrgNode right;

    OrgNode(String name) {
        this.name = name;
    }
}

public class OrganizationTreeReport {

    public static OrgNode findParent(OrgNode root, String target) {
        if (root == null) {
            return null;
        }

        if ((root.left != null && root.left.name.equals(target)) ||
            (root.right != null && root.right.name.equals(target))) {
            return root;
        }

        OrgNode leftResult = findParent(root.left, target);

        if (leftResult != null) {
            return leftResult;
        }

        return findParent(root.right, target);
    }

    public static int findDepth(OrgNode root, String target) {
        return findDepth(root, target, 0);
    }

    private static int findDepth(OrgNode node, String target, int depth) {
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

    public static List<String> pathFromRoot(OrgNode root, String target) {
        List<String> path = new ArrayList<>();

        if (findPath(root, target, path)) {
            return path;
        }

        path.clear();
        return path;
    }

    private static boolean findPath(
            OrgNode node,
            String target,
            List<String> path) {

        if (node == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (findPath(node.left, target, path) ||
            findPath(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void printByLevel(OrgNode root) {
        if (root == null) {
            System.out.println("Empty organization");
            return;
        }

        Queue<OrgNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int count = queue.size();

            System.out.print("Level " + level + ": ");

            for (int i = 0; i < count; i++) {
                OrgNode node = queue.poll();

                System.out.print(node.name + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {

        OrgNode root = new OrgNode("總公司");

        root.left = new OrgNode("資訊部");
        root.right = new OrgNode("業務部");

        root.left.left = new OrgNode("開發組");
        root.left.right = new OrgNode("維運組");

        root.right.left = new OrgNode("國內業務");
        root.right.right = new OrgNode("海外業務");

        System.out.println("組織架構：");
        printByLevel(root);

        System.out.println();

        OrgNode parent = findParent(root, "維運組");

        if (parent != null) {
            System.out.println("維運組的 parent = " + parent.name);
        } else {
            System.out.println("找不到 parent");
        }

        System.out.println(
                "維運組的 depth = "
                        + findDepth(root, "維運組"));

        System.out.println(
                "總公司到維運組的 path = "
                        + pathFromRoot(root, "維運組"));

        System.out.println();

        System.out.println("測試不存在的單位：");

        OrgNode notFoundParent =
                findParent(root, "人資部");

        System.out.println(
                "parent = "
                        + (notFoundParent == null
                        ? "null"
                        : notFoundParent.name));

        System.out.println(
                "depth = "
                        + findDepth(root, "人資部"));

        System.out.println(
                "path = "
                        + pathFromRoot(root, "人資部"));
    }
}