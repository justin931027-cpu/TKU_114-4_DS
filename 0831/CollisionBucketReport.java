import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    private ArrayList<Integer>[] buckets;
    private int bucketCount;

    @SuppressWarnings("unchecked")
    public CollisionBucketReport(int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException("bucketCount must be greater than 0");
        }

        this.bucketCount = bucketCount;
        buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    // 計算 key 所在的 bucket
    private int hash(int key) {
        return Math.floorMod(key, bucketCount);
    }

    // 加入 key
    public void add(int key) {

        int index = hash(key);

        // 重複 key 不重複加入
        if (buckets[index].contains(key)) {
            return;
        }

        buckets[index].add(key);
    }

    // 取得某個 bucket 的 key 清單
    public List<Integer> getBucket(int index) {

        if (index < 0 || index >= bucketCount) {
            throw new IndexOutOfBoundsException("Invalid bucket index");
        }

        return new ArrayList<>(buckets[index]);
    }

    // 計算 collision 數量
    //
    // 一個 bucket 如果有 n 個不同的 key，
    // 第一個不算 collision，後面的 n - 1 個算 collision。
    public int getCollisionCount() {

        int collisions = 0;

        for (ArrayList<Integer> bucket : buckets) {

            if (bucket.size() > 1) {
                collisions += bucket.size() - 1;
            }
        }

        return collisions;
    }

    // 取得最長 chain
    public int getLongestChain() {

        int longest = 0;

        for (ArrayList<Integer> bucket : buckets) {

            if (bucket.size() > longest) {
                longest = bucket.size();
            }
        }

        return longest;
    }

    // 輸出完整報告
    public void report() {

        System.out.println("=== Collision Bucket Report ===");

        for (int i = 0; i < bucketCount; i++) {

            System.out.println(
                "bucket[" + i + "] = " + buckets[i]
            );
        }

        System.out.println(
            "collision count = " + getCollisionCount()
        );

        System.out.println(
            "longest chain = " + getLongestChain()
        );
    }

    // 測試
    public static void main(String[] args) {

        CollisionBucketReport table =
                new CollisionBucketReport(5);

        int[] keys = {
            10, 15, 20, 7, 12, 17, 22, 10, 15
        };

        for (int key : keys) {
            table.add(key);
        }

        table.report();
    }
}