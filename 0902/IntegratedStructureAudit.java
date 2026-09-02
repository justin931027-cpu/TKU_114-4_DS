import java.util.*;

public class IntegratedStructureAudit {
    public enum DataStructureType {
        LIST, QUEUE, BST, HEAP, HASH_TABLE, GRAPH
    }

    public static class RequirementScenario {
        String scenarioName;
        DataStructureType chosenDS;
        boolean needFrequentMinMax;
        boolean needO1Lookup;
        boolean needMiddleInsert;
        boolean hasComplexRelationships;

        public RequirementScenario(String name, DataStructureType ds, boolean minMax, boolean lookup, boolean midInsert, boolean relations) {
            this.scenarioName = name;
            this.chosenDS = ds;
            this.needFrequentMinMax = minMax;
            this.needO1Lookup = lookup;
            this.needMiddleInsert = midInsert;
            this.hasComplexRelationships = relations;
        }
    }

    public static class AuditReport {
        public boolean isAppropriate;
        public String diagnosis;
        public DataStructureType recommendation;

        public AuditReport(boolean isAppropriate, String diagnosis, DataStructureType recommendation) {
            this.isAppropriate = isAppropriate;
            this.diagnosis = diagnosis;
            this.recommendation = recommendation;
        }

        @Override
        public String toString() {
            return String.format("審查結論: %-8s | 建議替換: %-12s\n詳細診斷: %s",
                    (isAppropriate ? "[合理]" : "[不合理/高風險]"),
                    (isAppropriate ? "無 (維持原選)" : recommendation),
                    diagnosis);
        }
    }

    public static AuditReport audit(RequirementScenario req) {
        if (req == null) {
            return new AuditReport(false, "[邊界異常] 傳入之需求情境物件為 null。", null);
        }
        if (req.chosenDS == null) {
            return new AuditReport(false, "[邊界異常] 未指定評估之資料結構 (chosenDS is null)。", null);
        }

        // 規則 1: 網路節點與關係
        if (req.hasComplexRelationships && req.chosenDS != DataStructureType.GRAPH) {
            return new AuditReport(false, "實體具備複雜的多對多關聯性，使用線性結構或樹結構難以表達邊的關係。", DataStructureType.GRAPH);
        }

        // 規則 2: 高頻極值處理
        if (req.needFrequentMinMax && req.chosenDS != DataStructureType.HEAP && req.chosenDS != DataStructureType.BST) {
            return new AuditReport(false, "需要頻繁獲取最值，採用非優先佇列結構將導致反覆尋找極值的搜尋開銷過高。", DataStructureType.HEAP);
        }

        // 規則 3: O(1) 快速查找
        if (req.needO1Lookup && req.chosenDS != DataStructureType.HASH_TABLE) {
            return new AuditReport(false, "情境強烈要求 O(1) 等級之主鍵檢索，目前結構搜尋需要 O(log n) 或 O(n)。", DataStructureType.HASH_TABLE);
        }

        // 規則 4: 中間頻繁插入
        if (req.needMiddleInsert && req.chosenDS == DataStructureType.QUEUE) {
            return new AuditReport(false, "Queue 嚴格遵守 FIFO 兩端操作，不支援也不應進行中間資料插入。", DataStructureType.LIST);
        }

        return new AuditReport(true, "所選資料結構與業務情境之時間空間特性吻合。", req.chosenDS);
    }

    public static void main(String[] args) {
        List<RequirementScenario> testSuite = Arrays.asList(
                // 1. 合理案例: 地圖路網選 Graph
                new RequirementScenario("社群關係網路建模", DataStructureType.GRAPH, false, false, false, true),

                // 2. 不合理案例: 高頻 O(1) 查詢卻選了 List
                new RequirementScenario("百萬用戶 ID 登入快查", DataStructureType.LIST, false, true, false, false),

                // 3. 不合理案例: 頻繁取極值卻選了 Queue
                new RequirementScenario("即時最高出價競標系統", DataStructureType.QUEUE, true, false, false, false),

                // 4. 合理案例: 依優先級選 Heap
                new RequirementScenario("緊急病患分流系統", DataStructureType.HEAP, true, false, false, false),

                // 5. 邊界測試: null 與空屬性
                new RequirementScenario("未定義結構情境", null, false, false, false, false),
                null
        );

        System.out.println("=== 整合架構決策自動化審查報告 ===");
        for (int i = 0; i < testSuite.size(); i++) {
            RequirementScenario sc = testSuite.get(i);
            System.out.println("\n案例 " + (i + 1) + ": " + (sc != null ? sc.scenarioName : "Null 情境"));
            AuditReport report = audit(sc);
            System.out.println(report);
        }
    }
}