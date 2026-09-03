package final_exam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q07_AdjacencyListGraph {

    // Adjacency List
    // key = vertex
    // value = 從這個 vertex 出發的所有鄰居
    private Map<String, Set<String>> graph;

    // Edge 總數
    private int edges;

    // Constructor
    public Q07_AdjacencyListGraph() {
        graph = new HashMap<>();
        edges = 0;
    }

    // 新增 Vertex
    public boolean addVertex(String vertex) {

        // 不接受 null
        if (vertex == null) {
            return false;
        }

        // 已經存在
        if (graph.containsKey(vertex)) {
            return false;
        }

        // 使用 LinkedHashSet
        // 可以保持 Edge 加入順序
        graph.put(vertex, new LinkedHashSet<>());

        return true;
    }

    // 新增有向 Edge
    public boolean addEdge(String from, String to) {

        // from 或 to 不存在
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        // 不允許 self-loop
        if (from.equals(to)) {
            return false;
        }

        Set<String> outgoing = graph.get(from);

        // 不允許重複 Edge
        if (outgoing.contains(to)) {
            return false;
        }

        // 加入有向 Edge：from → to
        outgoing.add(to);

        edges++;

        return true;
    }

    // 移除有向 Edge
    public boolean removeEdge(String from, String to) {

        // from 或 to 不存在
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            return false;
        }

        Set<String> outgoing = graph.get(from);

        // Edge 不存在
        if (!outgoing.contains(to)) {
            return false;
        }

        // 移除 from → to
        outgoing.remove(to);

        edges--;

        return true;
    }

    // 查詢某 vertex 的 outgoing edges
    public List<String> outgoing(String vertex) {

        // missing vertex → 空 List
        if (!graph.containsKey(vertex)) {
            return new ArrayList<>();
        }

        // 回傳獨立的 List
        return new ArrayList<>(graph.get(vertex));
    }

    // 計算入度
    public int inDegree(String vertex) {

        // missing vertex → 0
        if (!graph.containsKey(vertex)) {
            return 0;
        }

        int count = 0;

        // 檢查所有 vertex 的 outgoing
        for (Set<String> neighbors : graph.values()) {

            if (neighbors.contains(vertex)) {
                count++;
            }
        }

        return count;
    }

    // Edge 總數
    public int edgeCount() {
        return edges;
    }
}