import java.util.*;

public class WebsiteLinkGraph {
    private final Map<String, Set<String>> adjList = new HashMap<>();

    public void addPage(String page) {
        adjList.putIfAbsent(page, new HashSet<>());
    }

    public void addLink(String from, String to) {
        addPage(from);
        addPage(to);
        adjList.get(from).add(to);
    }

    public Set<String> getOutgoingLinks(String page) {
        return adjList.getOrDefault(page, Collections.emptySet());
    }

    public int getIncomingCount(String targetPage) {
        int count = 0;
        for (Set<String> targets : adjList.values()) {
            if (targets.contains(targetPage)) {
                count++;
            }
        }
        return count;
    }

    public void analyzeWebGraph() {
        System.out.println("=== 網站連結分析報告 ===");
        Map<String, Integer> incomingCounts = new HashMap<>();
        for (String page : adjList.keySet()) {
            incomingCounts.put(page, 0);
        }
        for (Set<String> targets : adjList.values()) {
            for (String to : targets) {
                incomingCounts.put(to, incomingCounts.get(to) + 1);
            }
        }

        List<String> noIncomingPages = new ArrayList<>();
        List<String> noOutgoingPages = new ArrayList<>();

        for (String page : adjList.keySet()) {
            int inCount = incomingCounts.get(page);
            int outCount = adjList.get(page).size();

            System.out.printf("頁面: %-12s | Outgoing: %-20s | Incoming 數: %d\n",
                    page, adjList.get(page), inCount);

            if (inCount == 0) noIncomingPages.add(page);
            if (outCount == 0) noOutgoingPages.add(page);
        }

        System.out.println("\n無 Incoming 頁面 (入口頁/Root): " + noIncomingPages);
        System.out.println("無 Outgoing 頁面 (終點/Dead End): " + noOutgoingPages);
    }

    public static void main(String[] args) {
        WebsiteLinkGraph graph = new WebsiteLinkGraph();
        graph.addLink("index.html", "about.html");
        graph.addLink("index.html", "products.html");
        graph.addLink("about.html", "contact.html");
        graph.addLink("products.html", "contact.html");
        graph.addLink("contact.html", "privacy.html");
        graph.addPage("landing.html"); // 孤立頁面測試

        graph.analyzeWebGraph();
    }
}