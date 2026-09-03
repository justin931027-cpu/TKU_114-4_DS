package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q09_DfsPathSearch {

    // DFS
    public static List<String> dfs(
            Map<String, List<String>> graph,
            String start) {

        // graph == null 或 start 不存在
        if (graph == null || start == null || !graph.containsKey(start)) {
            return new ArrayList<>();
        }

        // 記錄已拜訪的 vertex
        Set<String> visited = new HashSet<>();

        // 儲存 DFS 結果
        List<String> result = new ArrayList<>();

        // 使用 recursion
        dfsRecursive(graph, start, visited, result);

        return result;
    }

    // Recursive DFS
    private static void dfsRecursive(
            Map<String, List<String>> graph,
            String current,
            Set<String> visited,
            List<String> result) {

        // 已經拜訪過就停止
        if (visited.contains(current)) {
            return;
        }

        // 標記 visited
        visited.add(current);

        // 加入 DFS 結果
        result.add(current);

        // 取得 adjacency list
        List<String> neighbors = graph.get(current);

        if (neighbors == null) {
            return;
        }

        // 按照 adjacency list 原本順序走訪
        for (String neighbor : neighbors) {

            if (neighbor == null) {
                continue;
            }

            // Recursive DFS
            dfsRecursive(
                    graph,
                    neighbor,
                    visited,
                    result
            );
        }
    }

    // 判斷 start 是否可以到達 target
    public static boolean reachable(
            Map<String, List<String>> graph,
            String start,
            String target) {

        // graph 無效
        if (graph == null) {
            return false;
        }

        // start 或 target 無效
        if (start == null || target == null) {
            return false;
        }

        // start 或 target 不存在
        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return false;
        }

        // start == target
        if (start.equals(target)) {
            return true;
        }

        // visited 防止 cycle
        Set<String> visited = new HashSet<>();

        // 使用 recursive search
        return reachableRecursive(
                graph,
                start,
                target,
                visited
        );
    }

    // Recursive Reachability
    private static boolean reachableRecursive(
            Map<String, List<String>> graph,
            String current,
            String target,
            Set<String> visited) {

        // 如果目前就是 target
        if (current.equals(target)) {
            return true;
        }

        // 已拜訪過，避免 cycle
        if (visited.contains(current)) {
            return false;
        }

        // 標記 visited
        visited.add(current);

        List<String> neighbors = graph.get(current);

        if (neighbors == null) {
            return false;
        }

        // 按照 adjacency list 順序搜尋
        for (String neighbor : neighbors) {

            if (neighbor == null) {
                continue;
            }

            // Recursive search
            if (reachableRecursive(
                    graph,
                    neighbor,
                    target,
                    visited)) {

                return true;
            }
        }

        return false;
    }
}