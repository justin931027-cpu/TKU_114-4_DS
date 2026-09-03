package final_exam;
import java.util.ArrayList;
import java.util.List;

public class Q02_MinHeapInsert {

    private ArrayList<Integer> heap;

    public Q02_MinHeapInsert() {
        heap = new ArrayList<>();
    }

    // 新增元素
    public void add(int value) {
        // 先加入 Heap 最後面
        heap.add(value);

        // Bubble-up
        int index = heap.size() - 1;

        while (index > 0) {

            // 父節點索引
            int parent = (index - 1) / 2;

            // 如果目前節點 >= 父節點，已符合 Min Heap
            if (heap.get(index) >= heap.get(parent)) {
                break;
            }

            // 交換目前節點與父節點
            int temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);

            // 繼續往上檢查
            index = parent;
        }
    }

    // 查看最小值
    public Integer peek() {
        // 空 Heap 回傳 null
        if (heap.isEmpty()) {
            return null;
        }

        // Min Heap 的最小值一定在 root
        return heap.get(0);
    }

    // Heap 大小
    public int size() {
        return heap.size();
    }

    // 回傳 Heap 的快照
    public List<Integer> snapshot() {
        // 建立新的 List，避免暴露內部 heap
        return new ArrayList<>(heap);
    }

    // 檢查是否符合 Min Heap
    public boolean isValidMinHeap() {

        for (int i = 0; i < heap.size(); i++) {

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 檢查左子節點
            if (left < heap.size()) {
                if (heap.get(i) > heap.get(left)) {
                    return false;
                }
            }

            // 檢查右子節點
            if (right < heap.size()) {
                if (heap.get(i) > heap.get(right)) {
                    return false;
                }
            }
        }

        return true;
    }
}