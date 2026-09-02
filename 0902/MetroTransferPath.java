import java.util.*;

public class MetroTransferPath {
    private final Map<String, List<String>> adjList = new HashMap<>();

    public void addConnection(String s1, String s2) {
        adjList.computeIfAbsent(s1, k -> new ArrayList<>()).add(s2);
        adjList.computeIfAbsent(s2, k -> new ArrayList<>()).add(s1);
    }

    public static class PathResult {
        public final List<String> path;
        public final int edgeCount;
        public final String status;

        public PathResult(List<String> path, int edgeCount, String status) {
            this.path = path;
            this.edgeCount = edgeCount;
            this.status = status;
        }
    }

    public PathResult findShortestPath(String start, String end) {
        if (start == null || end == null || !adjList.containsKey(start) || !adjList.containsKey(end)) {
            return new PathResult(Collections.emptyList(), -1, "車站不存在 (Missing Vertex)");
        }
        if (start.equals(end)) {
            return new PathResult(Collections.singletonList(start), 0, "起迄站相同");
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(end)) {
                found = true;
                break;
            }
            for (String neighbor : adjList.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) {
            return new PathResult(Collections.emptyList(), -1, "兩站之間無連通路線");
        }

        LinkedList<String> path = new LinkedList<>();
        String step = end;
        while (step != null) {
            path.addFirst(step);
            step = parent.get(step);
        }

        return new PathResult(path, path.size() - 1, "成功尋獲路徑");
    }

    public static void main(String[] args) {
        MetroTransferPath metro = new MetroTransferPath();
        metro.addConnection("板橋", "台北車站");
        metro.addConnection("台北車站", "中山");
        metro.addConnection("台北車站", "西門");
        metro.addConnection("西門", "中正紀念堂");
        metro.addConnection("中正紀念堂", "東門");
        metro.addConnection("松山機場", "大直"); // 孤立路線

        System.out.println("--- 測試 1: 一般轉乘路徑 ---");
        PathResult r1 = metro.findShortestPath("板橋", "東門");
        System.out.println("狀態: " + r1.status + " | 站數: " + r1.path.size() + " | Edge Count: " + r1.edgeCount + " | 路徑: " + r1.path);

        System.out.println("\n--- 測試 2: 邊界案例 (無連通路徑) ---");
        PathResult r2 = metro.findShortestPath("板橋", "大直");
        System.out.println("狀態: " + r2.status + " | 路徑: " + r2.path);

        System.out.println("\n--- 測試 3: 邊界案例 (站點不存在) ---");
        PathResult r3 = metro.findShortestPath("台北車站", "高雄車站");
        System.out.println("狀態: " + r3.status + " | 路徑: " + r3.path);
    }
}