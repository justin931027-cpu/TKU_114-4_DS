import java.util.*;

public class NetworkComponents {
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addVertex(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(String u, String v) {
        addVertex(u);
        addVertex(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public static class ComponentAnalysis {
        public List<List<String>> components = new ArrayList<>();
        public List<String> largestComponent = new ArrayList<>();
        public int count = 0;
    }

    public ComponentAnalysis analyzeComponents() {
        ComponentAnalysis result = new ComponentAnalysis();
        if (adjList.isEmpty()) {
            return result; // 空圖邊界
        }

        Set<String> visited = new HashSet<>();
        for (String node : adjList.keySet()) {
            if (!visited.contains(node)) {
                List<String> comp = new ArrayList<>();
                bfsCollect(node, visited, comp);
                result.components.add(comp);
                if (comp.size() > result.largestComponent.size()) {
                    result.largestComponent = comp;
                }
            }
        }
        result.count = result.components.size();
        return result;
    }

    private void bfsCollect(String start, Set<String> visited, List<String> comp) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            comp.add(curr);
            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        NetworkComponents net = new NetworkComponents();
        net.addEdge("S1", "S2");
        net.addEdge("S2", "S3");
        net.addEdge("S4", "S5");
        net.addVertex("S6"); // 單一獨立節點

        System.out.println("--- 測試 1: 一般網路連通分析 ---");
        ComponentAnalysis ca = net.analyzeComponents();
        System.out.println("總 Component 數量: " + ca.count);
        for (int i = 0; i < ca.components.size(); i++) {
            System.out.println("  Component " + (i + 1) + ": " + ca.components.get(i));
        }
        System.out.println("最大 Component: " + ca.largestComponent + " (大小: " + ca.largestComponent.size() + ")");

        System.out.println("\n--- 測試 2: 邊界案例 (空圖) ---");
        ComponentAnalysis emptyCa = new NetworkComponents().analyzeComponents();
        System.out.println("空圖總 Component 數: " + emptyCa.count + " | 最大 Component: " + emptyCa.largestComponent);
    }
}