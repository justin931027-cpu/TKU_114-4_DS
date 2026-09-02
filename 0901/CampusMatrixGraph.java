import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private int[][] matrix;
    private int vertexCount;
    private int edgeCount;

    public CampusMatrixGraph(int maxVertices) {

        if (maxVertices <= 0) {
            throw new IllegalArgumentException(
                "頂點數量必須大於 0"
            );
        }

        matrix = new int[maxVertices][maxVertices];
        vertexCount = 0;
        edgeCount = 0;
    }

    // 新增頂點
    public int addVertex() {

        if (vertexCount >= matrix.length) {
            throw new IllegalStateException(
                "Graph 已經沒有空間"
            );
        }

        return vertexCount++;
    }

    // 檢查頂點
    private void checkVertex(int vertex) {

        if (vertex < 0 || vertex >= vertexCount) {
            throw new IllegalArgumentException(
                "不存在的 vertex: " + vertex
            );
        }
    }

    // 新增無向邊
    public void addEdge(int from, int to) {

        checkVertex(from);
        checkVertex(to);

        if (from == to) {
            return;
        }

        // 如果原本沒有 edge
        if (matrix[from][to] == 0) {

            matrix[from][to] = 1;
            matrix[to][from] = 1;

            edgeCount++;
        }
    }

    // 移除無向邊
    public void removeEdge(int from, int to) {

        checkVertex(from);
        checkVertex(to);

        if (matrix[from][to] == 1) {

            matrix[from][to] = 0;
            matrix[to][from] = 0;

            edgeCount--;
        }
    }

    // 判斷是否存在 edge
    public boolean hasEdge(int from, int to) {

        checkVertex(from);
        checkVertex(to);

        return matrix[from][to] == 1;
    }

    // 查詢 degree
    public int degree(int vertex) {

        checkVertex(vertex);

        int degree = 0;

        for (int i = 0; i < vertexCount; i++) {

            if (matrix[vertex][i] == 1) {
                degree++;
            }
        }

        return degree;
    }

    // 查詢 neighbors
    public List<Integer> neighbors(int vertex) {

        checkVertex(vertex);

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < vertexCount; i++) {

            if (matrix[vertex][i] == 1) {
                result.add(i);
            }
        }

        return result;
    }

    // Edge 數量
    public int edgeCount() {
        return edgeCount;
    }

    // Vertex 數量
    public int vertexCount() {
        return vertexCount;
    }

    // 印出 Matrix
    public void printMatrix() {

        System.out.println("===== Adjacency Matrix =====");

        for (int i = 0; i < vertexCount; i++) {

            for (int j = 0; j < vertexCount; j++) {

                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        CampusMatrixGraph graph =
            new CampusMatrixGraph(10);

        int library = graph.addVertex();
        int classroom = graph.addVertex();
        int dormitory = graph.addVertex();
        int cafeteria = graph.addVertex();
        int gym = graph.addVertex();

        // 建立校園道路
        graph.addEdge(library, classroom);
        graph.addEdge(library, dormitory);
        graph.addEdge(classroom, cafeteria);
        graph.addEdge(dormitory, cafeteria);
        graph.addEdge(cafeteria, gym);

        // 重複 edge
        graph.addEdge(library, classroom);

        graph.printMatrix();

        System.out.println();

        System.out.println(
            "Library degree = " +
            graph.degree(library)
        );

        System.out.println(
            "Library neighbors = " +
            graph.neighbors(library)
        );

        System.out.println(
            "Edge count = " +
            graph.edgeCount()
        );

        System.out.println();

        System.out.println("移除 Library - Classroom");

        graph.removeEdge(library, classroom);

        System.out.println(
            "Library degree = " +
            graph.degree(library)
        );

        System.out.println(
            "Edge count = " +
            graph.edgeCount()
        );
    }
}