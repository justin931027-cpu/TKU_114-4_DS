import java.util.*;

public class ServiceRequestSystem {
    public static class Request {
        int id;
        int priority; // 數值越小優先級越高
        String description;
        boolean cancelled;

        public Request(int id, int priority, String description) {
            this.id = id;
            this.priority = priority;
            this.description = description;
            this.cancelled = false;
        }

        @Override
        public String toString() {
            return "Request[ID=" + id + ", Pri=" + priority + ", Desc='" + description + "']";
        }
    }

    private final Map<Integer, Request> requestMap = new HashMap<>();
    private final PriorityQueue<Request> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.priority));

    // 新增服務請求
    public boolean addRequest(int id, int priority, String desc) {
        if (requestMap.containsKey(id)) {
            System.err.println("[警告] 請求 ID " + id + " 已存在。");
            return false;
        }
        Request req = new Request(id, priority, desc);
        requestMap.put(id, req);
        pq.offer(req);
        return true;
    }

    // 取消服務單 (保持兩份結構一致)
    public boolean cancelRequest(int id) {
        if (!requestMap.containsKey(id)) {
            System.out.println("[取消失敗] 找不到單號: " + id);
            return false;
        }
        Request req = requestMap.remove(id);
        req.cancelled = true; // 標記為已取消，並從 PQ 實體移除
        pq.remove(req);
        System.out.println("[成功取消] 單號 " + id + " 已從系統撤銷");
        return true;
    }

    // 依 ID 隨機查詢
    public Request findById(int id) {
        return requestMap.get(id);
    }

    // 取出下一筆最高優先級工作 (Lazy Clean-up 確保被標記的垃圾請求不會被取出)
    public Request fetchNext() {
        while (!pq.isEmpty() && pq.peek().cancelled) {
            pq.poll();
        }
        if (pq.isEmpty()) {
            return null;
        }
        Request next = pq.poll();
        requestMap.remove(next.id); // 從 Map 同步移除
        return next;
    }

    public static void main(String[] args) {
        ServiceRequestSystem sys = new ServiceRequestSystem();

        System.out.println("--- 測試 1: 正常加入與優先序出單 ---");
        sys.addRequest(101, 3, "網路連線緩慢");
        sys.addRequest(102, 1, "主機房伺服器當機 (緊急)");
        sys.addRequest(103, 2, "印表機卡紙");

        System.out.println("查詢 ID 103: " + sys.findById(103));

        System.out.println("\n--- 測試 2: 取消服務單與同步檢查 ---");
        sys.cancelRequest(101); // 取消 101
        System.out.println("再次查詢 ID 101: " + sys.findById(101));

        System.out.println("\n--- 測試 3: 依優先級出單 ---");
        while (true) {
            Request r = sys.fetchNext();
            if (r == null) break;
            System.out.println("處理下一筆: " + r);
        }

        System.out.println("\n--- 測試 4: 邊界案例 (對空系統操作與無效取消) ---");
        System.out.println("空佇列取出: " + sys.fetchNext());
        sys.cancelRequest(9999);
    }
}