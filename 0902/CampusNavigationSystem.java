import java.util.*;

public class CampusNavigationSystem {
    static class Location {
        String id;
        String name;

        Location(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(" + id + ")";
        }
    }

    private final Map<String, Location> locationRegistry = new HashMap<>();
    private final Map<String, List<String>> roadGraph = new HashMap<>();

    public void registerLocation(String id, String name) {
        if (id != null && name != null) {
            locationRegistry.put(id, new Location(id, name));
            roadGraph.putIfAbsent(id, new ArrayList<>());
        }
    }

    public void addRoad(String id1, String id2) {
        if (!locationRegistry.containsKey(id1) || !locationRegistry.containsKey(id2)) {
            System.err.println("[錯誤] 道路連接之地點未註冊: " + id1 + " 或 " + id2);
            return;
        }
        roadGraph.get(id1).add(id2);
        roadGraph.get(id2).add(id1);
    }

    public List<Location> findShortestNavigation(String startId, String targetId) {
        if (startId == null || targetId == null ||
            !locationRegistry.containsKey(startId) || !locationRegistry.containsKey(targetId)) {
            System.out.println("[邊界/錯誤] 起迄地點無效或不存在。");
            return Collections.emptyList();
        }

        if (startId.equals(targetId)) {
            return Collections.singletonList(locationRegistry.get(startId));
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parentMap = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startId);
        visited.add(startId);

        boolean reached = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(targetId)) {
                reached = true;
                break;
            }
            for (String neighbor : roadGraph.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!reached) {
            System.out.println("[結果] 無法從 " + locationRegistry.get(startId).name + " 到達 " + locationRegistry.get(targetId).name);
            return Collections.emptyList();
        }

        LinkedList<Location> path = new LinkedList<>();
        String curr = targetId;
        while (curr != null) {
            path.addFirst(locationRegistry.get(curr));
            curr = parentMap.get(curr);
        }
        return path;
    }

    public static void main(String[] args) {
        CampusNavigationSystem nav = new CampusNavigationSystem();
        nav.registerLocation("L01", "校門口");
        nav.registerLocation("L02", "行政大樓");
        nav.registerLocation("L03", "資訊學院");
        nav.registerLocation("L04", "圖書館");
        nav.registerLocation("L05", "體育館");
        nav.registerLocation("L06", "獨立涼亭"); // 無連通

        nav.addRoad("L01", "L02");
        nav.addRoad("L02", "L03");
        nav.addRoad("L02", "L04");
        nav.addRoad("L03", "L05");
        nav.addRoad("L04", "L05");

        System.out.println("--- 測試 1: 一般導航路徑 (校門口 -> 體育館) ---");
        List<Location> path = nav.findShortestNavigation("L01", "L05");
        System.out.println("最佳路徑 (Edges: " + (path.size() - 1) + "): " + path);

        System.out.println("\n--- 測試 2: 邊界案例 (不可達地點) ---");
        nav.findShortestNavigation("L01", "L06");

        System.out.println("\n--- 測試 3: 邊界案例 (輸入不存在或 null) ---");
        nav.findShortestNavigation("L01", "Unknown");
        nav.findShortestNavigation(null, "L02");
    }
}