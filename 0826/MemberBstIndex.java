public class MemberBstIndex {

    static class Member {
        int memberId;
        String name;
        String email;

        Member(int memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        public String toString() {
            return "ID=" + memberId +
                   ", Name=" + name +
                   ", Email=" + email;
        }
    }

    static class Node {
        Member member;
        Node left;
        Node right;

        Node(Member member) {
            this.member = member;
        }
    }

    static Node root;

    static boolean add(Member member) {
        if (member.email == null || member.email.isBlank()) {
            return false;
        }

        if (root == null) {
            root = new Node(member);
            return true;
        }

        Node current = root;

        while (true) {
            if (member.memberId == current.member.memberId) {
                return false;
            }

            if (member.memberId < current.member.memberId) {
                if (current.left == null) {
                    current.left = new Node(member);
                    return true;
                }

                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(member);
                    return true;
                }

                current = current.right;
            }
        }
    }

    static Member find(int memberId) {
        Node current = root;

        while (current != null) {
            if (memberId == current.member.memberId) {
                return current.member;
            }

            if (memberId < current.member.memberId) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    static boolean updateEmail(int memberId, String newEmail) {
        if (newEmail == null || newEmail.isBlank()) {
            return false;
        }

        Member member = find(memberId);

        if (member == null) {
            return false;
        }

        member.email = newEmail;
        return true;
    }

    static boolean remove(int memberId) {
        if (find(memberId) == null) {
            return false;
        }

        root = remove(root, memberId);
        return true;
    }

    static Node remove(Node node, int memberId) {
        if (node == null) {
            return null;
        }

        if (memberId < node.member.memberId) {
            node.left = remove(node.left, memberId);

        } else if (memberId > node.member.memberId) {
            node.right = remove(node.right, memberId);

        } else {
            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = minNode(node.right);

            node.member = successor.member;

            node.right = remove(
                node.right,
                successor.member.memberId
            );
        }

        return node;
    }

    static Node minNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    static void inorderReport(Node node) {
        if (node == null) {
            return;
        }

        inorderReport(node.left);

        System.out.println(node.member);

        inorderReport(node.right);
    }

    public static void main(String[] args) {

        System.out.println("=== Add Members ===");

        System.out.println(
            add(new Member(103, "Amy", "amy@gmail.com"))
        );

        System.out.println(
            add(new Member(101, "Ben", "ben@gmail.com"))
        );

        System.out.println(
            add(new Member(105, "Cindy", "cindy@gmail.com"))
        );

        System.out.println(
            add(new Member(102, "David", "david@gmail.com"))
        );

        System.out.println("\n=== Duplicate ID ===");

        System.out.println(
            add(new Member(103, "Eric", "eric@gmail.com"))
        );

        System.out.println("\n=== Blank Email ===");

        System.out.println(
            add(new Member(106, "Frank", ""))
        );

        System.out.println("\n=== Find Member 102 ===");

        System.out.println(find(102));

        System.out.println("\n=== Update Email ===");

        System.out.println(
            updateEmail(
                102,
                "david123@gmail.com"
            )
        );

        System.out.println(find(102));

        System.out.println("\n=== Invalid Email Update ===");

        System.out.println(
            updateEmail(102, "   ")
        );

        System.out.println("\n=== Before Remove ===");

        inorderReport(root);

        System.out.println("\n=== Remove Member 103 ===");

        System.out.println(remove(103));

        System.out.println("\n=== After Remove ===");

        inorderReport(root);
    }
}