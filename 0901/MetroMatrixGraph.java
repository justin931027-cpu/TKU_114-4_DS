import java.util.*;

public class MetroMatrixGraph {
    private final String[] stations;
    private final Map<String, Integer> stationIndexMap;
    private final int[][] adjMatrix;
    private final int numVertices;

    public MetroMatrixGraph(String[] stations) {
        this.stations = stations.clone();
        this.numVertices = stations.length;
        this.adjMatrix = new int[numVertices][numVertices];
        this.stationIndexMap = new HashMap<>();

        for (int i = 0; i < numVertices; i++) {
            stationIndexMap.put(stations[i], i);
        }
    }

    // 新增無向邊
    public void addEdge(String s1, String s2) {
        if (!stationIndexMap.containsKey(s1) || !stationIndexMap.containsKey(s2)) {
            System.err.println("站點不存在: " + s1 + " 或 " + s2);
            return;
        }
        int u = stationIndexMap.get(s1);
        int v = stationIndexMap.get(s2);
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    public List<String> getNeighbors(String station) {
        List<String> neighbors = new ArrayList<>();
        if (!stationIndexMap.containsKey(station)) return neighbors;
        int u = stationIndexMap.get(station);
        for (int v = 0; v < numVertices; v++) {
            if (adjMatrix[u][v] == 1) {
                neighbors.add(stations[v]);
            }
        }
        return neighbors;
    }

    public int getDegree(String station) {
        return getNeighbors(station).size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjMatrix[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printMatrixReport() {
        System.out.println("=== 捷運網路矩陣報告 ===");
        System.out.println("總邊數 (Edge Count): " + getEdgeCount());
        System.out.println("\n各站點 Degree 與鄰站:");
        for (String station : stations) {
            System.out.printf("%-12s (Degree: %d): %s\n", station, getDegree(station), getNeighbors(station));
        }

        System.out.println("\n鄰接矩陣 (Adjacency Matrix):");
        System.out.printf("%-10s", "");
        for (String s : stations) System.out.printf("%-10s", s);
        System.out.println();
        for (int i = 0; i < numVertices; i++) {
            System.out.printf("%-10s", stations[i]);
            for (int j = 0; j < numVertices; j++) {
                System.out.printf("%-10d", adjMatrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] stations = {"台北車站", "中山", "雙連", "西門", "中正紀念堂"};
        MetroMatrixGraph metro = new MetroMatrixGraph(stations);

        metro.addEdge("台北車站", "中山");
        metro.addEdge("中山", "雙連");
        metro.addEdge("台北車站", "西門");
        metro.addEdge("台北車站", "中正紀念堂");
        metro.addEdge("西門", "中正紀念堂");

        metro.printMatrixReport();
    }
}