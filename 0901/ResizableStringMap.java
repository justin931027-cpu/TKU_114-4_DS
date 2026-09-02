import java.util.ArrayList;
import java.util.List;

public class ResizableStringMap {

    // Hash Table 中每一筆資料
    private static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] buckets;
    private int size;
    private static final double LOAD_FACTOR = 0.75;

    // 建構子
    public ResizableStringMap() {
        buckets = new Entry[4];
        size = 0;
    }

    // 計算 Hash
    private int hash(String key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    // 新增或修改資料
    public void put(String key, String value) {

        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);

        Entry current = buckets[index];

        // 如果 key 已存在，就修改 value
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }

            current = current.next;
        }

        // 新增 Entry
        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;

        // Load Factor > 0.75 就擴充
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }

    // 查詢
    public String get(String key) {

        if (key == null) {
            return null;
        }

        int index = hash(key);

        Entry current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }

            current = current.next;
        }

        return null;
    }

    // 判斷是否存在
    public boolean containsKey(String key) {
        return get(key) != null;
    }

    // 刪除
    public String remove(String key) {

        if (key == null) {
            return null;
        }

        int index = hash(key);

        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {

            if (current.key.equals(key)) {

                if (previous == null) {
                    buckets[index] = current.next;
                } else {
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

    // 擴充 Hash Table
    private void resize() {

        Entry[] oldBuckets = buckets;

        // 原本容量加倍
        buckets = new Entry[oldBuckets.length * 2];

        // 重新配置所有 Entry
        for (Entry entry : oldBuckets) {

            Entry current = entry;

            while (current != null) {

                Entry next = current.next;

                int index = hash(current.key);

                current.next = buckets[index];
                buckets[index] = current;

                current = next;
            }
        }
    }

    // 取得目前資料數量
    public int size() {
        return size;
    }

    // 取得目前 Bucket 數量
    public int capacity() {
        return buckets.length;
    }

    // 顯示 Hash Table
    public void printTable() {

        System.out.println("===== Hash Table =====");

        for (int i = 0; i < buckets.length; i++) {

            System.out.print("Bucket " + i + ": ");

            Entry current = buckets[i];

            while (current != null) {
                System.out.print(
                    "(" + current.key + ", " + current.value + ") -> "
                );

                current = current.next;
            }

            System.out.println("null");
        }

        System.out.println("Size = " + size);
        System.out.println("Capacity = " + buckets.length);
        System.out.println(
            "Load Factor = " +
            String.format("%.2f", (double) size / buckets.length)
        );
    }

    // 測試
    public static void main(String[] args) {

        ResizableStringMap map = new ResizableStringMap();

        map.put("A", "Apple");
        map.put("B", "Banana");
        map.put("C", "Cat");
        map.put("D", "Dog");

        System.out.println("A = " + map.get("A"));
        System.out.println("B = " + map.get("B"));

        System.out.println();

        System.out.println("加入更多資料...");

        map.put("E", "Elephant");
        map.put("F", "Fish");
        map.put("G", "Grape");

        map.printTable();

        System.out.println();

        System.out.println("刪除 C:");
        System.out.println("被刪除的值 = " + map.remove("C"));

        System.out.println("C = " + map.get("C"));

        System.out.println();

        map.printTable();
    }
}