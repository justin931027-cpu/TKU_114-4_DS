public class CourseBstIndex {

    static class Course {
        String courseCode;
        String courseName;
        int credit;

        Course(
            String courseCode,
            String courseName,
            int credit
        ) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.credit = credit;
        }

        public String toString() {
            return courseCode +
                   " | " +
                   courseName +
                   " | credit=" +
                   credit;
        }
    }

    static class Node {
        Course course;
        Node left;
        Node right;

        Node(Course course) {
            this.course = course;
        }
    }

    static Node root;

    static boolean add(Course course) {

        if (course.credit < 1 || course.credit > 6) {
            return false;
        }

        if (root == null) {
            root = new Node(course);
            return true;
        }

        Node current = root;

        while (true) {

            int compare =
                course.courseCode.compareTo(
                    current.course.courseCode
                );

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {

                if (current.left == null) {
                    current.left =
                        new Node(course);

                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right =
                        new Node(course);

                    return true;
                }

                current = current.right;
            }
        }
    }

    static Course find(String courseCode) {

        Node current = root;

        while (current != null) {

            int compare =
                courseCode.compareTo(
                    current.course.courseCode
                );

            if (compare == 0)
                return current.course;

            if (compare < 0)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    static boolean updateCredit(
        String courseCode,
        int newCredit
    ) {

        if (newCredit < 1 || newCredit > 6)
            return false;

        Course course = find(courseCode);

        if (course == null)
            return false;

        course.credit = newCredit;

        return true;
    }

    static boolean remove(String courseCode) {

        if (find(courseCode) == null)
            return false;

        root = remove(root, courseCode);

        return true;
    }

    static Node remove(
        Node node,
        String courseCode
    ) {

        if (node == null)
            return null;

        int compare =
            courseCode.compareTo(
                node.course.courseCode
            );

        if (compare < 0) {

            node.left =
                remove(
                    node.left,
                    courseCode
                );

        } else if (compare > 0) {

            node.right =
                remove(
                    node.right,
                    courseCode
                );

        } else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor =
                minNode(node.right);

            node.course =
                successor.course;

            node.right =
                remove(
                    node.right,
                    successor.course.courseCode
                );
        }

        return node;
    }

    static Node minNode(Node node) {

        while (node.left != null)
            node = node.left;

        return node;
    }

    static void inorder(Node node) {

        if (node == null)
            return;

        inorder(node.left);

        System.out.println(node.course);

        inorder(node.right);
    }

    static void rangeQuery(
        Node node,
        String low,
        String high
    ) {

        if (node == null)
            return;

        if (
            node.course.courseCode.compareTo(low) > 0
        ) {
            rangeQuery(
                node.left,
                low,
                high
            );
        }

        if (
            node.course.courseCode.compareTo(low) >= 0 &&
            node.course.courseCode.compareTo(high) <= 0
        ) {
            System.out.println(node.course);
        }

        if (
            node.course.courseCode.compareTo(high) < 0
        ) {
            rangeQuery(
                node.right,
                low,
                high
            );
        }
    }

    public static void main(String[] args) {

        System.out.println(
            add(new Course(
                "CS103",
                "Data Structure",
                3
            ))
        );

        System.out.println(
            add(new Course(
                "CS101",
                "Programming",
                3
            ))
        );

        System.out.println(
            add(new Course(
                "CS105",
                "Database",
                3
            ))
        );

        System.out.println(
            add(new Course(
                "CS102",
                "Java",
                2
            ))
        );

        System.out.println(
            add(new Course(
                "CS104",
                "Network",
                3
            ))
        );

        System.out.println("\n=== Duplicate Code ===");

        System.out.println(
            add(new Course(
                "CS103",
                "Duplicate",
                3
            ))
        );

        System.out.println("\n=== Invalid Credit ===");

        System.out.println(
            add(new Course(
                "CS106",
                "Invalid",
                8
            ))
        );

        System.out.println("\n=== Find ===");

        System.out.println(
            find("CS102")
        );

        System.out.println("\n=== Update Credit ===");

        System.out.println(
            updateCredit(
                "CS102",
                4
            )
        );

        System.out.println(
            find("CS102")
        );

        System.out.println("\n=== Sorted Report ===");

        inorder(root);

        System.out.println("\n=== Range CS102 ~ CS104 ===");

        rangeQuery(
            root,
            "CS102",
            "CS104"
        );

        System.out.println("\n=== Remove CS103 ===");

        System.out.println(
            remove("CS103")
        );

        inorder(root);
    }
}