package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Q10_UnweightedShortestPath {

    public static List<String> shortestPath(
            Map<String, List<String>> graph,
            String start,
            String target) {

        // graph 無效
        if (graph == null) {
            return new ArrayList<>();
        }

        // start 或 target 無效
        if (start == null || target == null) {
            return new ArrayList<>();
        }

        // start 或 target 不存在
        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return new ArrayList<>();
        }

        // start == target
        if (start.equals(target)) {
            List<String> result = new ArrayList<>();
            result.add(start);
            return result;
        }

        // BFS Queue
        Queue<String> queue = new LinkedList<>();

        // 記錄已拜訪節點
        Set<String> visited = new HashSet<>();

        // predecessor：
        // key = 目前節點
        // value = 前一個節點
        Map<String, String> predecessor = new HashMap<>();

        // 起點
        visited.add(start);
        queue.offer(start);

        // BFS
        while (!queue.isEmpty()) {

            String current = queue.poll();

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            // 按照 adjacency list 原本順序
            for (String neighbor : neighbors) {

                if (neighbor == null) {
                    continue;
                }

                // 尚未拜訪
                if (!visited.contains(neighbor)) {

                    // 標記 visited
                    visited.add(neighbor);

                    // 記錄 predecessor
                    predecessor.put(neighbor, current);

                    // 放入 Queue
                    queue.offer(neighbor);

                    // 找到 target
                    if (neighbor.equals(target)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        // target 沒有被找到
        if (!visited.contains(target)) {
            return new ArrayList<>();
        }

        // =========================
        // Path Reconstruction
        // =========================

        List<String> path = new ArrayList<>();

        String current = target;

        // 從 target 往回找 predecessor
        while (current != null) {

            path.add(current);

            if (current.equals(start)) {
                break;
            }

            current = predecessor.get(current);
        }

        // 反轉，變成 start → target
        java.util.Collections.reverse(path);

        // 確認路徑真的從 start 開始
        if (path.isEmpty() || !path.get(0).equals(start)) {
            return new ArrayList<>();
        }

        return path;
    }
}