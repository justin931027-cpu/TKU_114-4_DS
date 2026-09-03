package final_exam;
import java.util.ArrayList;
import java.util.List;

public class Q03_MinHeapRemove {

    private ArrayList<Integer> heap;

    // Constructor
    public Q03_MinHeapRemove(List<Integer> values) {
        heap = new ArrayList<>();

        // 忽略輸入中的 null
        if (values != null) {
            for (Integer value : values) {
                if (value != null) {
                    heap.add(value);
                }
            }
        }

        // Bottom-up Heapify
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    // 移除最小值
    public Integer removeMin() {

        // 空 Heap 回傳 null
        if (heap.isEmpty()) {
            return null;
        }

        // 保存 root，也就是最小值
        int min = heap.get(0);

        // 只有一個元素
        if (heap.size() == 1) {
            heap.remove(0);
            return min;
        }

        // 將最後一個元素移到 root
        int last = heap.remove(heap.size() - 1);
        heap.set(0, last);

        // Bubble-down
        bubbleDown(0);

        return min;
    }

    // 查看最小值
    public Integer peek() {

        // 空 Heap 回傳 null
        if (heap.isEmpty()) {
            return null;
        }

        return heap.get(0);
    }

    // 回傳 Heap 大小
    public int size() {
        return heap.size();
    }

    // 回傳 Heap 的副本
    public List<Integer> snapshot() {
        return new ArrayList<>(heap);
    }

    // Bubble-down
    private void bubbleDown(int index) {

        while (true) {

            // 左子節點
            int left = index * 2 + 1;

            // 右子節點
            int right = index * 2 + 2;

            // 假設目前節點是最小
            int smallest = index;

            // 比較左子節點
            if (left < heap.size()
                    && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            // 比較右子節點
            if (right < heap.size()
                    && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            // 如果目前節點已經比子節點小，就完成
            if (smallest == index) {
                break;
            }

            // 交換
            int temp = heap.get(index);
            heap.set(index, heap.get(smallest));
            heap.set(smallest, temp);

            // 繼續往下
            index = smallest;
        }
    }
}