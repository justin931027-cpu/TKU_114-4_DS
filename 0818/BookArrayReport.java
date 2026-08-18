class Book {
    private String isbn;
    private String title;
    private double price;
    private int stock;

    public Book(String isbn, String title, double price, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String toString() {
        return "書號：" + isbn +
               "，書名：" + title +
               "，價格：" + price +
               "，庫存：" + stock;
    }
}

public class BookArrayReport {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "Java程式設計", 500, 5),
            new Book("B002", "資料結構", 650, 2),
            new Book("B003", "資料庫系統", 580, 4),
            new Book("B004", "網頁程式設計", 720, 3)
        };

        System.out.println("=== 所有書籍 ===");
        for (Book book : books) {
            System.out.println(book);
        }

        double totalValue = 0;

        for (Book book : books) {
            totalValue += book.getPrice() * book.getStock();
        }

        System.out.println("\n庫存總價值：" + totalValue);

        Book mostExpensive = books[0];

        for (Book book : books) {
            if (book.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = book;
            }
        }

        System.out.println("\n=== 價格最高的書 ===");
        System.out.println(mostExpensive);

        System.out.println("\n=== 庫存小於或等於 3 的書 ===");
        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}