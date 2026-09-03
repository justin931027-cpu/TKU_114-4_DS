package final_exam;
import java.util.ArrayList;
import java.util.List;

public class Q06_AdjacencyMatrixGraph {

    // Vertex 名稱
    private List<String> vertices;

    // Adjacency Matrix
    private boolean[][] matrix;

    // Constructor
    public Q06_AdjacencyMatrixGraph(List<String> vertices) {

        // 複製 vertices，避免外部修改
        this.vertices = new ArrayList<>();

        if (vertices != null) {
            for (String vertex : vertices) {
                if (vertex != null && !this.vertices.contains(vertex)) {
                    this.vertices.add(vertex);
                }
            }
        }

        // 建立鄰接矩陣
        matrix = new boolean[this.vertices.size()][this.vertices.size()];
    }

    // 尋找 vertex 的 index
    private int indexOf(String vertex) {
        return vertices.indexOf(vertex);
    }

    // 新增 Edge
    public boolean addEdge(String first, String second) {

        // 找不到 vertex
        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        // 不允許 self-loop
        if (firstIndex == secondIndex) {
            return false;
        }

        // 已經存在 edge，不允許重複
        if (matrix[firstIndex][secondIndex]) {
            return false;
        }

        // Undirected Graph
        matrix[firstIndex][secondIndex] = true;
        matrix[secondIndex][firstIndex] = true;

        return true;
    }

    // 移除 Edge
    public boolean removeEdge(String first, String second) {

        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        // 找不到 vertex
        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        // 不允許 self-loop
        if (firstIndex == secondIndex) {
            return false;
        }

        // Edge 不存在
        if (!matrix[firstIndex][secondIndex]) {
            return false;
        }

        // Undirected Graph，兩邊都要刪除
        matrix[firstIndex][secondIndex] = false;
        matrix[secondIndex][firstIndex] = false;

        return true;
    }

    // 判斷 Edge 是否存在
    public boolean hasEdge(String first, String second) {

        int firstIndex = indexOf(first);
        int secondIndex = indexOf(second);

        // 任一 vertex 不存在
        if (firstIndex == -1 || secondIndex == -1) {
            return false;
        }

        // self-loop 不存在
        if (firstIndex == secondIndex) {
            return false;
        }

        return matrix[firstIndex][secondIndex];
    }

    // 計算 vertex 的 degree
    public int degree(String vertex) {

        int vertexIndex = indexOf(vertex);

        // vertex 不存在
        if (vertexIndex == -1) {
            return 0;
        }

        int count = 0;

        // 計算該列有幾個 true
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertexIndex][i]) {
                count++;
            }
        }

        return count;
    }

    // 找出 vertex 的所有鄰居
    public List<String> neighbors(String vertex) {

        List<String> result = new ArrayList<>();

        int vertexIndex = indexOf(vertex);

        // vertex 不存在，回傳空 List
        if (vertexIndex == -1) {
            return result;
        }

        // 按照 vertices 原本的順序尋找
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[vertexIndex][i]) {
                result.add(vertices.get(i));
            }
        }

        return result;
    }
}