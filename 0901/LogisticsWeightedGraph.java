import java.util.*;

public class LogisticsWeightedGraph {
    // 存儲結構: fromNode -> (toNode -> weight)
    private final Map<String, Map<String, Double>> graph = new HashMap<>();

    public void addHub(String hub) {
        graph.putIfAbsent(hub, new HashMap<>());
    }

    // 新增或更新帶權重有向邊
    public boolean addOrUpdateEdge(String from, String to, double cost) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            System.err.printf("[錯誤] 節點不存在: %s -> %s\n", from, to);
            return false;
        }
        if (cost < 0) {
            System.err.printf("[錯誤] 成本不可為負數: %.2f (路線: %s -> %s)\n", cost, from, to);
            return false;
        }

        Map<String, Double> edges = graph.get(from);
        boolean isUpdate = edges.containsKey(to);
        edges.put(to, cost);

        System.out.printf("[%s] %s -> %s, 成本: %.2f\n", (isUpdate ? "更新路線" : "新增路線"), from, to, cost);
        return true;
    }

    // 移除有向邊
    public boolean removeEdge(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            System.err.printf("[錯誤] 節點不存在: %s 或 %s\n", from, to);
            return false;
        }
        if (!graph.get(from).containsKey(to)) {
            System.err.printf("[錯誤] 路線不存在: %s -> %s\n", from, to);
            return false;
        }
        graph.get(from).remove(to);
        System.out.printf("[移除路線] %s -> %s\n", from, to);
        return true;
    }

    // 查詢特定路線成本
    public Double getCost(String from, String to) {
        if (!graph.containsKey(from) || !graph.containsKey(to)) {
            System.err.printf("[錯誤] 查詢時節點不存在: %s 或 %s\n", from, to);
            return null;
        }
        return graph.get(from).get(to);
    }

    public void printNetworkReport() {
        System.out.println("\n=== 物流成本網路現況 ===");
        for (String hub : graph.keySet()) {
            System.out.println("物流中心 [" + hub + "] 出發路線:");
            Map<String, Double> dests = graph.get(hub);
            if (dests.isEmpty()) {
                System.out.println("  (無出發路線)");
            } else {
                for (var dest : dests.entrySet()) {
                    System.out.printf("  -> %s (成本: %.2f)\n", dest.getKey(), dest.getValue());
                }
            }
        }
        System.out.println("========================");
    }

    public static void main(String[] args) {
        LogisticsWeightedGraph logistics = new LogisticsWeightedGraph();

        logistics.addHub("台北總倉");
        logistics.addHub("桃園轉運站");
        logistics.addHub("台中集散地");
        logistics.addHub("高雄分處");

        // 正常操作
        logistics.addOrUpdateEdge("台北總倉", "桃園轉運站", 150.0);
        logistics.addOrUpdateEdge("桃園轉運站", "台中集散地", 300.0);
        logistics.addOrUpdateEdge("台中集散地", "高雄分處", 450.0);

        // 異常測試 1: 負權重
        logistics.addOrUpdateEdge("台北總倉", "高雄分處", -50.0);

        // 異常測試 2: 不存在的節點
        logistics.addOrUpdateEdge("台北總倉", "花蓮倉庫", 200.0);

        // 更新操作
        logistics.addOrUpdateEdge("台北總倉", "桃園轉運站", 120.0);

        // 查詢
        System.out.println("查詢 台北總倉 -> 桃園轉運站 成本: " + logistics.getCost("台北總倉", "桃園轉運站"));

        // 移除
        logistics.removeEdge("台中集散地", "高雄分處");

        logistics.printNetworkReport();
    }
}