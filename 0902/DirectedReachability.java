import java.util.*;

public class DirectedReachability {
    private final Map<String, Set<String>> adjList = new HashMap<>();

    public void addEdge(String from, String to) {
        adjList.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        adjList.putIfAbsent(to, new HashSet<>());
    }

    public boolean isReachable(String src, String dest) {
        if (src == null || dest == null || !adjList.containsKey(src) || !adjList.containsKey(dest)) {
            return false;
        }
        if (src.equals(dest)) return true;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(src);
        visited.add(src);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String neighbor : adjList.get(curr)) {
                if (neighbor.equals(dest)) return true;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }

    public void batchQuery(List<String[]> queries) {
        System.out.println("=== 批次可達性 (Reachability) 查詢 ===");
        if (queries == null || queries.isEmpty()) {
            System.out.println("[邊界] 查詢清單為空。");
            return;
        }

        for (String[] q : queries) {
            if (q == null || q.length < 2) {
                System.out.println("查詢無效 (參數不足)");
                continue;
            }
            boolean res = isReachable(q[0], q[1]);
            System.out.printf("%s -> %s : %s\n", q[0], q[1], res ? "可到達 (Reachable)" : "不可到達 (Unreachable)");
        }
    }

    public static void main(String[] args) {
        DirectedReachability graph = new DirectedReachability();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("E", "F");

        List<String[]> queries = Arrays.asList(
                new String[]{"A", "D"},      // 一般案例: 可達
                new String[]{"D", "A"},      // 一般案例: 有向無反向
                new String[]{"A", "F"},      // 不同分量: 不可達
                new String[]{"A", "A"},      // 邊界: 自身
                new String[]{"A", "Z"},      // 邊界: 節點不存在
                new String[]{null, "B"}      // 邊界: null 查詢
        );

        graph.batchQuery(queries);
        graph.batchQuery(Collections.emptyList()); // 邊界: 空查詢
    }
}