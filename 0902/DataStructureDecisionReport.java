import java.util.*;

public class DataStructureDecisionReport {
    public static class Decision {
        int id;
        String scenario;
        String choice;
        String rationale;
        String primaryBigO;

        public Decision(int id, String scenario, String choice, String rationale, String primaryBigO) {
            this.id = id;
            this.scenario = scenario;
            this.choice = choice;
            this.rationale = rationale;
            this.primaryBigO = primaryBigO;
        }

        @Override
        public String toString() {
            return String.format("[%02d] 情境: %-26s\n     推薦結構: %-15s | Big-O: %s\n     選擇理由: %s\n",
                    id, scenario, choice, primaryBigO, rationale);
        }
    }

    public static List<Decision> get12Decisions() {
        List<Decision> list = new ArrayList<>();
        list.add(new Decision(1, "高頻鍵值精確查詢", "Hash Table", "提供接近常數時間的直接定位", "Search: O(1)"));
        list.add(new Decision(2, "依優先級排程任務", "Heap (PriorityQueue)", "動態提取最值且維持半有序狀態最佳", "Offer/Poll: O(log n)"));
        list.add(new Decision(3, "範圍搜尋與有序遍歷", "BST (AVL / Red-Black)", "保持全域有序且平衡樹具備對數檢索界線", "Search/Range: O(log n)"));
        list.add(new Decision(4, "瀏覽器歷史上一頁/下一頁", "Double Stack / List", "完美契合後進先出 (LIFO) 操作", "Push/Pop: O(1)"));
        list.add(new Decision(5, "排隊服務 (FIFO)", "Queue (LinkedList)", "先進先出，避免前段資料移動成本", "Enqueue/Dequeue: O(1)"));
        list.add(new Decision(6, "稀疏實體關係網路拓撲", "Adjacency List", "節省空間，精準走訪鄰居", "Visit Neighbors: O(deg(V))"));
        list.add(new Decision(7, "密集成對連通性矩陣", "Adjacency Matrix", "矩陣存取能以常數時間驗證兩點連線", "Edge Lookup: O(1)"));
        list.add(new Decision(8, "前綴自動補全搜尋", "Trie", "利用字串公共前綴大幅降低比對空間與時間", "Search: O(L), L為長度"));
        list.add(new Decision(9, "動態維持全域中位數", "Dual Heap (Min/Max)", "雙堆平衡頂端直接定界中位數", "Insert: O(log n), Find: O(1)"));
        list.add(new Decision(10, "不可重複集合交集與差集", "Hash Set", "哈希表基底可極速排除重複元素", "Add/Contains: O(1)"));
        list.add(new Decision(11, "高頻尾端插入與索引存取", "Dynamic Array", "連續記憶體快取友善，支援隨機讀取", "Get: O(1), Amortized Add: O(1)"));
        list.add(new Decision(12, "LRU 快取淘汰機制", "LinkedHashMap", "雜湊配合雙向鏈結維持存取時序淘汰", "Get/Put: O(1)"));
        return list;
    }

    public static void queryDecision(List<Decision> decisions, Integer id) {
        if (id == null || decisions == null || decisions.isEmpty()) {
            System.out.println("[邊界/錯誤] 決策表為空或查詢 ID 為空。");
            return;
        }
        for (Decision d : decisions) {
            if (d.id == id) {
                System.out.println(d);
                return;
            }
        }
        System.out.println("[邊界] 找不到 ID 為 " + id + " 的決策情境。");
    }

    public static void main(String[] args) {
        List<Decision> decisions = get12Decisions();

        System.out.println("=== 12 組經典資料結構決策報告 ===");
        decisions.forEach(System.out::println);

        System.out.println("--- 測試: 針對特定 ID 查詢 ---");
        queryDecision(decisions, 2);

        System.out.println("--- 測試: 邊界案例 (無效 ID 與 null) ---");
        queryDecision(decisions, 99);
        queryDecision(decisions, null);
    }
}