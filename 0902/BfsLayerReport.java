import java.util.*;

public class BfsLayerReport {
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String u, String v, boolean directed) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        if (!directed) {
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        } else {
            adjList.putIfAbsent(v, new ArrayList<>());
        }
    }

    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public Map<String, Integer> calculateDistances(String start) {
        Map<String, Integer> distances = new LinkedHashMap<>();
        if (start == null || !adjList.containsKey(start)) {
            return distances; // 起點不存在或圖為空
        }

        for (String node : adjList.keySet()) {
            distances.put(node, -1); // -1 代表無法到達 (unreachable)
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currentDist = distances.get(curr);

            for (String neighbor : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (distances.get(neighbor) == -1) {
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }

    public void printReport(String start) {
        System.out.println("=== BFS Layer Report (Start: " + start + ") ===");
        Map<String, Integer> res = calculateDistances(start);
        if (res.isEmpty()) {
            System.out.println("[警告/邊界] 起點不存在或圖為空。");
            return;
        }
        res.forEach((k, v) -> System.out.println("節點: " + k + " | 距離(Edges): " + (v == -1 ? "Unreachable" : v)));
    }

    public static void main(String[] args) {
        BfsLayerReport graph = new BfsLayerReport();
        graph.addEdge("A", "B", false);
        graph.addEdge("A", "C", false);
        graph.addEdge("B", "D", false);
        graph.addEdge("C", "E", false);
        graph.addVertex("Isolated"); // 孤立節點

        System.out.println("--- 測試 1: 正常案例 ---");
        graph.printReport("A");

        System.out.println("\n--- 測試 2: 邊界案例 (節點不存在) ---");
        graph.printReport("NonExistent");

        System.out.println("\n--- 測試 3: 邊界案例 (空圖) ---");
        new BfsLayerReport().printReport("A");
    }
}