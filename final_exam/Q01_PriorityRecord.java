package final_exam;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Q01_PriorityRecord {

    public record Job(String id, int priority, long sequence) {
    }

    public static List<String> processOrder(List<Job> jobs) {

        // null 或空 List，直接回傳空 List
        if (jobs == null || jobs.isEmpty()) {
            return new ArrayList<>();
        }

        // PriorityQueue：
        // 1. priority 小的優先
        // 2. priority 相同時 sequence 小的優先
        // 3. priority、sequence 相同時 id 字典序
        Comparator<Job> comparator = Comparator
                .comparingInt(Job::priority)
                .thenComparingLong(Job::sequence)
                .thenComparing(Job::id);

        PriorityQueue<Job> queue = new PriorityQueue<>(comparator);

        // 加入 PriorityQueue
        // null 的 Job 忽略
        for (Job job : jobs) {
            if (job != null) {
                queue.offer(job);
            }
        }

        // 依照 PriorityQueue 的優先順序取出
        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Job job = queue.poll();
            result.add(job.id());
        }

        return result;
    }
}