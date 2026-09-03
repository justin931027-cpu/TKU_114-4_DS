package final_exam;
import java.util.ArrayList;
import java.util.List;

public class Q04_ChainedHashTable {

    // 每一個 bucket 都是一個 List
    private List<Entry>[] buckets;

    // Hash Table 中實際存放的 key 數量
    private int size;

    // Entry：儲存 key 和 value
    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    @SuppressWarnings("unchecked")
    public Q04_ChainedHashTable(int bucketCount) {

        // bucketCount 必須大於 0
        if (bucketCount <= 0) {
            throw new IllegalArgumentException();
        }

        buckets = (List<Entry>[]) new List<?>[bucketCount];

        // 每個 bucket 建立一個 ArrayList
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        size = 0;
    }

    // 計算 key 所在的 bucket
    private int index(int key) {

        // floorMod 可以正確處理負數 key
        return Math.floorMod(key, buckets.length);
    }

    // 新增或更新 key-value
    public void put(int key, String value) {

        int index = index(key);

        List<Entry> bucket = buckets[index];

        // 先尋找是否已經存在相同 key
        for (Entry entry : bucket) {

            if (entry.key == key) {

                // 相同 key → 更新 value
                entry.value = value;

                // size 不增加
                return;
            }
        }

        // 不存在相同 key → 新增
        bucket.add(new Entry(key, value));

        // size 增加
        size++;
    }

    // 取得 key 對應的 value
    public String get(int key) {

        int index = index(key);

        List<Entry> bucket = buckets[index];

        for (Entry entry : bucket) {

            if (entry.key == key) {
                return entry.value;
            }
        }

        // 找不到
        return null;
    }

    // 移除指定 key
    public boolean remove(int key) {

        int index = index(key);

        List<Entry> bucket = buckets[index];

        for (int i = 0; i < bucket.size(); i++) {

            if (bucket.get(i).key == key) {

                // 移除 Entry
                bucket.remove(i);

                // size 減少
                size--;

                return true;
            }
        }

        // 找不到
        return false;
    }

    // 回傳目前 key 的數量
    public int size() {
        return size;
    }

    // 回傳最長的 bucket chain 長度
    public int longestChain() {

        int max = 0;

        for (List<Entry> bucket : buckets) {

            if (bucket.size() > max) {
                max = bucket.size();
            }
        }

        return max;
    }
}