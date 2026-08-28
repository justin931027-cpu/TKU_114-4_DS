public class LibraryBookBst {

    static class Book {

        String isbn;
        String title;
        String author;
        boolean available;

        Book(
            String isbn,
            String title,
            String author
        ) {
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String toString() {

            return "ISBN=" + isbn +
                   ", Title=" + title +
                   ", Author=" + author +
                   ", Available=" + available;
        }
    }

    static class Node {

        Book book;

        Node left;
        Node right;

        Node(Book book) {
            this.book = book;
        }
    }

    static Node root;

    static boolean add(Book book) {

        if (root == null) {
            root = new Node(book);
            return true;
        }

        Node current = root;

        while (true) {

            int compare =
                book.isbn.compareTo(
                    current.book.isbn
                );

            if (compare == 0)
                return false;

            if (compare < 0) {

                if (current.left == null) {

                    current.left =
                        new Node(book);

                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {

                    current.right =
                        new Node(book);

                    return true;
                }

                current = current.right;
            }
        }
    }

    static Book find(String isbn) {

        Node current = root;

        while (current != null) {

            int compare =
                isbn.compareTo(
                    current.book.isbn
                );

            if (compare == 0)
                return current.book;

            if (compare < 0)
                current = current.left;
            else
                current = current.right;
        }

        return null;
    }

    static boolean borrow(String isbn) {

        Book book = find(isbn);

        if (book == null)
            return false;

        if (!book.available)
            return false;

        book.available = false;

        return true;
    }

    static boolean returnBook(String isbn) {

        Book book = find(isbn);

        if (book == null)
            return false;

        if (book.available)
            return false;

        book.available = true;

        return true;
    }

    static boolean remove(String isbn) {

        Book book = find(isbn);

        if (book == null)
            return false;

        if (!book.available)
            return false;

        root = remove(root, isbn);

        return true;
    }

    static Node remove(
        Node node,
        String isbn
    ) {

        if (node == null)
            return null;

        int compare =
            isbn.compareTo(
                node.book.isbn
            );

        if (compare < 0) {

            node.left =
                remove(
                    node.left,
                    isbn
                );

        } else if (compare > 0) {

            node.right =
                remove(
                    node.right,
                    isbn
                );

        } else {

            if (node.left == null)
                return node.right;

            if (node.right == null)
                return node.left;

            Node successor =
                minNode(node.right);

            node.book =
                successor.book;

            node.right =
                remove(
                    node.right,
                    successor.book.isbn
                );
        }

        return node;
    }

    static Node minNode(Node node) {

        while (node.left != null)
            node = node.left;

        return node;
    }

    static void inorderReport(Node node) {

        if (node == null)
            return;

        inorderReport(node.left);

        System.out.println(node.book);

        inorderReport(node.right);
    }

    static void rangeQuery(
        Node node,
        String low,
        String high
    ) {

        if (node == null)
            return;

        if (
            node.book.isbn.compareTo(low) > 0
        ) {
            rangeQuery(
                node.left,
                low,
                high
            );
        }

        if (
            node.book.isbn.compareTo(low) >= 0 &&
            node.book.isbn.compareTo(high) <= 0
        ) {
            System.out.println(node.book);
        }

        if (
            node.book.isbn.compareTo(high) < 0
        ) {
            rangeQuery(
                node.right,
                low,
                high
            );
        }
    }

    public static void main(String[] args) {

        add(
            new Book(
                "978003",
                "Java Programming",
                "Amy"
            )
        );

        add(
            new Book(
                "978001",
                "Data Structures",
                "Ben"
            )
        );

        add(
            new Book(
                "978005",
                "Database Systems",
                "Cindy"
            )
        );

        add(
            new Book(
                "978002",
                "Algorithms",
                "David"
            )
        );

        add(
            new Book(
                "978004",
                "Computer Networks",
                "Eric"
            )
        );

        System.out.println("=== Inorder Report ===");

        inorderReport(root);

        System.out.println("\n=== Find 978002 ===");

        System.out.println(
            find("978002")
        );

        System.out.println("\n=== Borrow 978002 ===");

        System.out.println(
            borrow("978002")
        );

        System.out.println(
            find("978002")
        );

        System.out.println(
            "\n=== Try Remove Borrowed Book ==="
        );

        System.out.println(
            remove("978002")
        );

        System.out.println("\n=== Return Book ===");

        System.out.println(
            returnBook("978002")
        );

        System.out.println("\n=== Remove After Return ===");

        System.out.println(
            remove("978002")
        );

        System.out.println(
            "\n=== Range 978001 ~ 978004 ==="
        );

        rangeQuery(
            root,
            "978001",
            "978004"
        );

        System.out.println(
            "\n=== Final Inorder Report ==="
        );

        inorderReport(root);
    }
}