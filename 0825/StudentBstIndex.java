public class StudentBstIndex {

    static class Student {
        int studentId;
        String name;
        double score;

        Student(int studentId, String name, double score) {
            this.studentId = studentId;
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return studentId + " " + name + " " + score;
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

    static boolean insert(Student student) {
        if (root == null) {
            root = new Node(student);
            return true;
        }

        Node current = root;

        while (true) {
            if (student.studentId == current.student.studentId) {
                return false;
            }

            if (student.studentId < current.student.studentId) {
                if (current.left == null) {
                    current.left = new Node(student);
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(student);
                    return true;
                }
                current = current.right;
            }
        }
    }

    static Student search(int studentId) {
        Node current = root;

        while (current != null) {
            if (studentId == current.student.studentId)
                return current.student;

            if (studentId < current.student.studentId)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    static void delete(int studentId) {
        root = delete(root, studentId);
    }

    static Node delete(Node node, int studentId) {
        if (node == null)
            return null;

        if (studentId < node.student.studentId) {
            node.left = delete(node.left, studentId);
        } else if (studentId > node.student.studentId) {
            node.right = delete(node.right, studentId);
        } else {
            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor = minNode(node.right);
            node.student = successor.student;
            node.right = delete(node.right, successor.student.studentId);
        }

        return node;
    }

    static Node minNode(Node node) {
        while (node.left != null)
            node = node.left;

        return node;
    }

    static void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.student);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        System.out.println("Insert:");

        System.out.println(insert(new Student(103, "Amy", 85)));
        System.out.println(insert(new Student(101, "Ben", 90)));
        System.out.println(insert(new Student(105, "Cindy", 78)));
        System.out.println(insert(new Student(102, "David", 92)));

        System.out.println("\nDuplicate ID:");
        System.out.println(insert(new Student(103, "Eric", 100)));

        System.out.println("\nSearch 102:");
        System.out.println(search(102));

        System.out.println("\nBefore delete:");
        inorder(root);

        delete(101);

        System.out.println("\nAfter delete 101:");
        inorder(root);
    }
}