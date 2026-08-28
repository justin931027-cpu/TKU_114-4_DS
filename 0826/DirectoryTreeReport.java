import java.util.ArrayList;

public class DirectoryTreeReport {

    static class Node {
        String name;
        boolean isFile;
        int size;
        ArrayList<Node> children;

        Node(String name, boolean isFile, int size) {
            this.name = name;
            this.isFile = isFile;
            this.size = size;
            this.children = new ArrayList<>();
        }

        void add(Node child) {
            children.add(child);
        }
    }

    static int totalNodes = 0;
    static int fileCount = 0;
    static int directoryCount = 0;
    static Node largestFile = null;

    static int postorder(Node node) {
        if (node == null)
            return 0;

        totalNodes++;

        if (node.isFile) {
            fileCount++;

            if (largestFile == null || node.size > largestFile.size) {
                largestFile = node;
            }

            return node.size;
        }

        directoryCount++;

        int totalSize = 0;

        for (Node child : node.children) {
            totalSize += postorder(child);
        }

        System.out.println(
            "Directory: " + node.name +
            ", Total Size: " + totalSize
        );

        return totalSize;
    }

    static int height(Node node) {
        if (node == null)
            return 0;

        if (node.children.isEmpty())
            return 1;

        int maxHeight = 0;

        for (Node child : node.children) {
            maxHeight = Math.max(
                maxHeight,
                height(child)
            );
        }

        return 1 + maxHeight;
    }

    public static void main(String[] args) {

        Node root = new Node("root", false, 0);

        Node documents =
            new Node("Documents", false, 0);

        Node pictures =
            new Node("Pictures", false, 0);

        Node school =
            new Node("School", false, 0);

        Node file1 =
            new Node("report.pdf", true, 500);

        Node file2 =
            new Node("homework.docx", true, 300);

        Node file3 =
            new Node("photo1.jpg", true, 1200);

        Node file4 =
            new Node("photo2.jpg", true, 800);

        Node file5 =
            new Node("project.zip", true, 2500);

        root.add(documents);
        root.add(pictures);

        documents.add(file1);
        documents.add(school);

        school.add(file2);
        school.add(file5);

        pictures.add(file3);
        pictures.add(file4);

        System.out.println("=== Postorder Directory Report ===");

        int totalSize = postorder(root);

        System.out.println();
        System.out.println("=== Summary ===");

        System.out.println(
            "Total Size: " + totalSize
        );

        System.out.println(
            "Total Nodes: " + totalNodes
        );

        System.out.println(
            "File Count: " + fileCount
        );

        System.out.println(
            "Directory Count: " + directoryCount
        );

        System.out.println(
            "Height: " + height(root)
        );

        System.out.println(
            "Largest File: " +
            largestFile.name +
            " (" + largestFile.size + ")"
        );
    }
}