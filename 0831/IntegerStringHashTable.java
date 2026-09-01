import java.util.ArrayList;
import java.util.List;

public class IntegerStringHashTable {

    // Hash Table 每個 bucket 的節點
    private static class Entry {

        int key;
        String value;
        Entry next;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry[] buckets;
    private int size;

    public IntegerStringHashTable(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "capacity must be greater than 0"
            );
        }

        buckets = new Entry[capacity];
        size = 0;
    }

    // Hash function
    private int hash(int key) {

        return Math.floorMod(key, buckets.length);
    }

    // put
    public void put(int key, String value) {

        int index = hash(key);

        Entry current = buckets[index];

        // 檢查是否已有相同 key
        while (current != null) {

            if (current.key == key) {

                // 相同 key 更新 value
                current.value = value;

                // size 不增加
                return;
            }

            current = current.next;
        }

        // 新 key
        Entry newEntry = new Entry(key, value);

        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;
    }

    // get
    public String get(int key) {

        int index = hash(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key == key) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // containsKey
    public boolean containsKey(int key) {

        int index = hash(key);

        Entry current = buckets[index];

        while (current != null) {

            if (current.key == key) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    // remove
    public String remove(int key) {

        int index = hash(key);

        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {

            if (current.key == key) {

                if (previous == null) {
                    // 移除 bucket 第一個節點
                    buckets[index] = current.next;
                } else {
                    // 移除中間或最後
                    previous.next = current.next;
                }

                size--;

                return current.value;
            }

            previous = current;
            current = current.next;
        }

        return null;
    }

    // size
    public int size() {
        return size;
    }

    // bucketReport
    public String bucketReport() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < buckets.length; i++) {

            result.append("bucket[")
                  .append(i)
                  .append("] = ");

            Entry current = buckets[i];

            if (current == null) {

                result.append("[]");

            } else {

                result.append("[");

                boolean first = true;

                while (current != null) {

                    if (!first) {
                        result.append(", ");
                    }

                    result.append(current.key);

                    first = false;
                    current = current.next;
                }

                result.append("]");
            }

            if (i < buckets.length - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    // 測試
    public static void main(String[] args) {

        IntegerStringHashTable table =
                new IntegerStringHashTable(5);

        // 新增
        table.put(1, "Apple");
        table.put(6, "Banana");
        table.put(11, "Orange");
        table.put(2, "Cat");
        table.put(7, "Dog");

        System.out.println("size = " + table.size());

        // 查詢
        System.out.println("get(1) = " + table.get(1));
        System.out.println(
            "containsKey(6) = " + table.containsKey(6)
        );

        // 相同 key 更新
        table.put(1, "Updated Apple");

        System.out.println(
            "get(1) = " + table.get(1)
        );

        System.out.println(
            "size after update = " + table.size()
        );

        // remove
        System.out.println(
            "remove(6) = " + table.remove(6)
        );

        System.out.println(
            "size after remove = " + table.size()
        );

        // Bucket Report
        System.out.println("\nBucket Report:");
        System.out.println(table.bucketReport());
    }
}