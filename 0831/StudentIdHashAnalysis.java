import java.util.*;

public class StudentIdHashAnalysis {

    // Hash 分析結果
    public static class AnalysisResult {

        private int bucketCount;
        private int totalStudents;
        private int totalCollisions;
        private int maxChain;
        private double averageChain;

        public AnalysisResult(
                int bucketCount,
                int totalStudents,
                int totalCollisions,
                int maxChain,
                double averageChain) {

            this.bucketCount = bucketCount;
            this.totalStudents = totalStudents;
            this.totalCollisions = totalCollisions;
            this.maxChain = maxChain;
            this.averageChain = averageChain;
        }

        public void print() {

            System.out.println("Bucket Count = " + bucketCount);
            System.out.println("Total Students = " + totalStudents);
            System.out.println("Total Collisions = " + totalCollisions);
            System.out.println("Max Chain = " + maxChain);
            System.out.printf(
                    "Average Chain Length = %.2f%n",
                    averageChain
            );
        }
    }

    // 執行 Hash 分析
    public static AnalysisResult analyze(
            int[] studentIds,
            int bucketCount) {

        if (studentIds == null) {
            throw new IllegalArgumentException(
                    "studentIds cannot be null"
            );
        }

        if (bucketCount <= 0) {
            throw new IllegalArgumentException(
                    "bucketCount must be greater than 0"
            );
        }

        // 每個 bucket 的數量
        int[] bucketCounts = new int[bucketCount];

        // 將學號放入 bucket
        for (int studentId : studentIds) {

            int index =
                    Math.floorMod(studentId, bucketCount);

            bucketCounts[index]++;
        }

        // 總 collision
        int totalCollisions = 0;

        // 最大 chain
        int maxChain = 0;

        for (int count : bucketCounts) {

            // 如果一個 bucket 有 n 筆
            // 第一筆不算 collision
            // 後面的 n - 1 筆算 collision
            if (count > 1) {
                totalCollisions += count - 1;
            }

            if (count > maxChain) {
                maxChain = count;
            }
        }

        // 平均 chain 長度
        double averageChain;

        if (bucketCount == 0) {
            averageChain = 0;
        } else {
            averageChain =
                    (double) studentIds.length / bucketCount;
        }

        // 顯示每個 bucket
        System.out.println();
        System.out.println("Bucket Details:");

        for (int i = 0; i < bucketCount; i++) {

            System.out.println(
                    "bucket[" + i + "] = "
                            + bucketCounts[i]
                            + " students"
            );
        }

        return new AnalysisResult(
                bucketCount,
                studentIds.length,
                totalCollisions,
                maxChain,
                averageChain
        );
    }

    // 比較兩種 bucket count
    public static void compare(
            int[] studentIds,
            int bucketCount1,
            int bucketCount2) {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println("第一種 Bucket Count");
        System.out.println(
                "================================"
        );

        AnalysisResult result1 =
                analyze(studentIds, bucketCount1);

        System.out.println();
        result1.print();

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println("第二種 Bucket Count");
        System.out.println(
                "================================"
        );

        AnalysisResult result2 =
                analyze(studentIds, bucketCount2);

        System.out.println();
        result2.print();

        // 比較
        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println("比較結果");
        System.out.println(
                "================================"
        );

        if (result1.totalCollisions
                < result2.totalCollisions) {

            System.out.println(
                    bucketCount1
                            + " 的 collision 較少"
            );

        } else if (result1.totalCollisions
                > result2.totalCollisions) {

            System.out.println(
                    bucketCount2
                            + " 的 collision 較少"
            );

        } else {

            System.out.println(
                    "兩種 bucket count 的 collision 數相同"
            );
        }

        if (result1.maxChain < result2.maxChain) {

            System.out.println(
                    bucketCount1
                            + " 的最大 chain 較短"
            );

        } else if (result1.maxChain > result2.maxChain) {

            System.out.println(
                    bucketCount2
                            + " 的最大 chain 較短"
            );

        } else {

            System.out.println(
                    "兩種 bucket count 的最大 chain 相同"
            );
        }

        System.out.printf(
                "平均 chain：%d = %.2f，%d = %.2f%n",
                bucketCount1,
                result1.averageChain,
                bucketCount2,
                result2.averageChain
        );
    }

    // 主程式
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 輸入學號數量
        System.out.print("請輸入學號數量：");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println(
                    "學號數量不能小於 0"
            );
            scanner.close();
            return;
        }

        int[] studentIds = new int[n];

        // 輸入學號
        System.out.println("請輸入 " + n + " 個學號：");

        for (int i = 0; i < n; i++) {

            studentIds[i] = scanner.nextInt();
        }

        // 第一種 bucket count
        System.out.print(
                "請輸入第一個 bucket count："
        );

        int bucketCount1 = scanner.nextInt();

        // 第二種 bucket count
        System.out.print(
                "請輸入第二個 bucket count："
        );

        int bucketCount2 = scanner.nextInt();

        // 比較兩種 bucket count
        compare(
                studentIds,
                bucketCount1,
                bucketCount2
        );

        scanner.close();
    }
}