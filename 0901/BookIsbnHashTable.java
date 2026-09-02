import java.util.LinkedList;

public class BookIsbnHashTable {
    private static class Entry {
        String isbn;
        String title;

        Entry(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public BookIsbnHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.buckets[i] = new LinkedList<>();
        }
        this.size = 0;
    }

    private int getBucketIndex(String isbn) {
        return Math.abs(isbn.hashCode()) % capacity;
    }

    // 新增或更新書籍
    public void put(String isbn, String title) {
        int idx = getBucketIndex(isbn);
        for (Entry entry : buckets[idx]) {
            if (entry.isbn.equals(isbn)) {
                entry.title = title; // 更新
                return;
            }
        }
        buckets[idx].add(new Entry(isbn, title));
        size++;
    }

    // 搜尋書籍
    public String get(String isbn) {
        int idx = getBucketIndex(isbn);
        for (Entry entry : buckets[idx]) {
            if (entry.isbn.equals(isbn)) {
                return entry.title;
            }
        }
        return null;
    }

    // 刪除書籍
    public boolean remove(String isbn) {
        int idx = getBucketIndex(isbn);
        for (Entry entry : buckets[idx]) {
            if (entry.isbn.equals(isbn)) {
                buckets[idx].remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public double getLoadFactor() {
        return (double) size / capacity;
    }

    // Bucket Report
    public void printBucketReport() {
        System.out.println("=== Bucket Report ===");
        System.out.printf("Total Size: %d, Capacity: %d, Load Factor: %.2f\n", size, capacity, getLoadFactor());
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + " (" + buckets[i].size() + " items): ");
            for (Entry e : buckets[i]) {
                System.out.print("[" + e.isbn + ": " + e.title + "] ");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }

    public static void main(String[] args) {
        BookIsbnHashTable table = new BookIsbnHashTable(5);
        table.put("978-0134685991", "Effective Java");
        table.put("978-0596009205", "Head First Design Patterns");
        table.put("978-0132350884", "Clean Code");
        table.put("978-0134685991", "Effective Java (3rd Edition)"); // 更新測試

        table.printBucketReport();

        System.out.println("Search '978-0132350884': " + table.get("978-0132350884"));
        table.remove("978-0596009205");
        System.out.println("After removing Head First Design Patterns:");
        table.printBucketReport();
    }
}