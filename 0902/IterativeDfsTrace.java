import java.util.*;

public class IterativeDfsTrace {
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void traceDFS(String start) {
        System.out.println("=== 開始追蹤 Iterative DFS (Start: " + start + ") ===");
        if (start == null || !adjList.containsKey(start)) {
            System.out.println("[邊界/錯誤] 起點不存在或為空！\n");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.printf("[PUSH] 節點: %-3s | Stack: %-20s | Visited: %s\n", start, stack, visited);

        while (!stack.isEmpty()) {
            String curr = stack.pop();
            System.out.printf("[POP ] 節點: %-3s | Stack: %-20s | Visited: %s\n", curr, stack, visited);

            if (!visited.contains(curr)) {
                visited.add(curr);
                List<String> neighbors = new ArrayList<>(adjList.getOrDefault(curr, Collections.emptyList()));
                // 保持 DFS 順序一致反向 push
                Collections.reverse(neighbors);
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        System.out.printf("[PUSH] 節點: %-3s | Stack: %-20s | Visited: %s\n", neighbor, stack, visited);
                    }
                }
            }
        }
        System.out.println("遍歷結束，最終訪問順序: " + visited + "\n");
    }

    public static void main(String[] args) {
        IterativeDfsTrace graph = new IterativeDfsTrace();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        System.out.println("--- 測試 1: 一般連通案例 ---");
        graph.traceDFS("A");

        System.out.println("--- 測試 2: 邊界案例 (起點為 null 或不存在) ---");
        graph.traceDFS("Z");
        graph.traceDFS(null);
    }
}