public class ScoreRangeBst {

    static class Student {
        int studentId;
        String name;
        int score;

        Student(int studentId, String name, int score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return studentId + " " + name + " score=" + score;
        }
    }

    static class Node {
        Student student;
        Node left, right;

        Node(Student student) {
            this.student = student;
        }
    }

    static Node root;

    static int compare(Student a, Student b) {
        if (a.score != b.score)
            return Integer.compare(a.score, b.score);

        return Integer.compare(a.studentId, b.studentId);
    }

    static Node insert(Node node, Student student) {
        if (node == null)
            return new Node(student);

        int result = compare(student, node.student);

        if (result < 0)
            node.left = insert(node.left, student);
        else if (result > 0)
            node.right = insert(node.right, student);

        return node;
    }

    static void insert(Student student) {
        root = insert(root, student);
    }

    static void printRange(Node node, int low, int high) {
        if (node == null)
            return;

        if (node.student.score >= low)
            printRange(node.left, low, high);

        if (node.student.score >= low &&
            node.student.score <= high) {

            System.out.println(node.student);
        }

        if (node.student.score <= high)
            printRange(node.right, low, high);
    }

    static void printRange(int low, int high) {
        if (low > high) {
            int temp = low;
            low = high;
            high = temp;
        }

        System.out.println(
            "Score Range: " + low + " ~ " + high
        );

        printRange(root, low, high);
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.student);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        insert(new Student(101, "Amy", 85));
        insert(new Student(102, "Ben", 90));
        insert(new Student(103, "Cindy", 85));
        insert(new Student(104, "David", 75));
        insert(new Student(105, "Eric", 95));
        insert(new Student(106, "Frank", 90));

        System.out.println("=== All Students ===");
        inorder(root);

        System.out.println("\n=== Range Report ===");
        printRange(80, 90);
    }
}