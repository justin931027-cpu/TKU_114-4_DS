package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q08_BfsTraversal {

    // BFS Traversal
    public static List<String> bfs(
            Map<String, List<String>> graph,
            String start) {

        // graph == null 或 start 不存在
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }

        // BFS 使用 Queue
        Queue<String> queue = new LinkedList<>();

        // 記錄已經拜訪過的 vertex
        Set<String> visited = new HashSet<>();

        // 儲存 BFS 順序
        List<String> result = new ArrayList<>();

        // 起點加入 visited 和 queue
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {

            // 取出目前 vertex
            String current = queue.poll();

            // 加入 BFS 結果
            result.add(current);

            // 取得 adjacency list
            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            // 按照 adjacency list 原本順序走訪
            for (String neighbor : neighbors) {

                // 避免 null
                if (neighbor == null) {
                    continue;
                }

                // 尚未拜訪
                if (!visited.contains(neighbor)) {

                    // 標記 visited
                    visited.add(neighbor);

                    // 放入 Queue
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    // 計算從 start 到各 vertex 的最短距離
    public static Map<String, Integer> distanceFrom(
            Map<String, List<String>> graph,
            String start) {

        // graph == null 或 start 不存在
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new HashMap<>();
        }

        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        Map<String, Integer> distance = new HashMap<>();

        // start 距離為 0
        visited.add(start);
        distance.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            for (String neighbor : neighbors) {

                if (neighbor == null) {
                    continue;
                }

                // 只處理尚未拜訪的 vertex
                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    // 距離 = 父節點距離 + 1
                    distance.put(
                            neighbor,
                            distance.get(current) + 1
                    );

                    queue.offer(neighbor);
                }
            }
        }

        return distance;
    }
}