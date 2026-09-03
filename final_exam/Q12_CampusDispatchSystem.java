package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Q12_CampusDispatchSystem {

    // Request
    public record Request(
            String id,
            String location,
            int priority,
            long sequence) {
    }

    // undirected adjacency list
    private final Map<String, List<String>> graph;

    // request id -> Request
    // 用來防止重複 request id
    private final Map<String, Request> requests;

    // 待處理 Request
    private final PriorityQueue<Request> pending;

    // Constructor
    public Q12_CampusDispatchSystem() {

        graph = new HashMap<>();
        requests = new HashMap<>();

        // priority 小的優先
        // priority 相同時 sequence 小的優先
        pending = new PriorityQueue<>(
                (a, b) -> {
                    int result =
                            Integer.compare(a.priority(), b.priority());

                    if (result != 0) {
                        return result;
                    }

                    return Long.compare(
                            a.sequence(),
                            b.sequence()
                    );
                }
        );
    }

    // 新增地點到 Graph
    private void addVertex(String location) {
        graph.putIfAbsent(location, new ArrayList<>());
    }

    // 新增無向道路
    public boolean addRoad(String first, String second) {

        // 無效輸入
        if (first == null || second == null) {
            return false;
        }

        first = first.trim();
        second = second.trim();

        if (first.isEmpty() || second.isEmpty()) {
            return false;
        }

        // 不允許 self-loop
        if (first.equals(second)) {
            return false;
        }

        // 確保兩個地點存在
        addVertex(first);
        addVertex(second);

        // 重複道路
        if (graph.get(first).contains(second)) {
            return false;
        }

        // Undirected Graph
        graph.get(first).add(second);
        graph.get(second).add(first);

        return true;
    }

    // 提交 Request
    public boolean submit(Request request) {

        // Request 不可為 null
        if (request == null) {
            return false;
        }

        // 檢查 Request 欄位
        if (request.id() == null
                || request.location() == null) {
            return false;
        }

        String id = request.id().trim();
        String location = request.location().trim();

        // id / location 不可空白
        if (id.isEmpty() || location.isEmpty()) {
            return false;
        }

        // 重複 request id
        if (requests.containsKey(id)) {
            return false;
        }

        // 建立整理後的 Request
        Request normalized = new Request(
                id,
                location,
                request.priority(),
                request.sequence()
        );

        // 加入 HashMap
        requests.put(id, normalized);

        // 確保 request location 存在於 Graph
        addVertex(location);

        // 加入 PriorityQueue
        pending.offer(normalized);

        return true;
    }

    // 取得目前 serviceCenter 可以到達的最高優先 Request
    public Request nextReachable(String serviceCenter) {

        // serviceCenter 無效
        if (serviceCenter == null) {
            return null;
        }

        serviceCenter = serviceCenter.trim();

        if (serviceCenter.isEmpty()) {
            return null;
        }

        // serviceCenter 不存在
        if (!graph.containsKey(serviceCenter)) {
            return null;
        }

        Request selected = null;

        // 暫時保存目前尚未選到的 Request
        List<Request> temp = new ArrayList<>();

        // 從 PriorityQueue 依優先順序取出
        while (!pending.isEmpty()) {

            Request request = pending.poll();

            // 判斷 serviceCenter 是否能到 request.location
            if (reachable(serviceCenter, request.location())) {

                selected = request;

                // 找到最高優先且可達的 Request
                break;

            } else {

                // 不可達 Request 必須保留
                temp.add(request);
            }
        }

        // 把不可達的 Request 放回 PriorityQueue
        for (Request request : temp) {
            pending.offer(request);
        }

        // 如果沒有可達 Request
        return selected;
    }

    // BFS 最短路徑
    public List<String> route(String start, String target) {

        List<String> result = new ArrayList<>();

        // 無效輸入
        if (start == null || target == null) {
            return result;
        }

        start = start.trim();
        target = target.trim();

        if (start.isEmpty() || target.isEmpty()) {
            return result;
        }

        // start / target 不存在
        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return result;
        }

        // start == target
        if (start.equals(target)) {
            result.add(start);
            return result;
        }

        // BFS
        Queue<String> queue = new LinkedList<>();

        Set<String> visited = new HashSet<>();

        // predecessor
        Map<String, String> predecessor = new HashMap<>();

        // 起點
        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            List<String> neighbors = graph.get(current);

            if (neighbors == null) {
                continue;
            }

            // 按照 adjacency list 加入順序
            for (String neighbor : neighbors) {

                if (neighbor == null) {
                    continue;
                }

                // 尚未拜訪
                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);

                    // 記錄前驅
                    predecessor.put(neighbor, current);

                    queue.offer(neighbor);

                    // 找到 target
                    if (neighbor.equals(target)) {
                        queue.clear();
                        break;
                    }
                }
            }
        }

        // target 不可達
        if (!visited.contains(target)) {
            return result;
        }

        // ==========================
        // Path Reconstruction
        // ==========================

        String current = target;

        while (current != null) {

            result.add(current);

            if (current.equals(start)) {
                break;
            }

            current = predecessor.get(current);
        }

        // 目前是 target -> start
        // 需要反轉成 start -> target
        java.util.Collections.reverse(result);

        // 確認真的從 start 開始
        if (result.isEmpty()
                || !result.get(0).equals(start)) {
            return new ArrayList<>();
        }

        return result;
    }

    // 判斷是否可達
    private boolean reachable(String start, String target) {

        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return false;
        }

        if (start.equals(target)) {
            return true;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        visited.add(start);
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

                if (!visited.contains(neighbor)) {

                    if (neighbor.equals(target)) {
                        return true;
                    }

                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }

    // 尚未處理的 Request 數量
    public int pendingCount() {
        return pending.size();
    }
}