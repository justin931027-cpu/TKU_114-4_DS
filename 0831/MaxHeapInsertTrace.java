import java.util.*;

public class MaxHeapInsertTrace {

    private ArrayList<Integer> heap;

    public MaxHeapInsertTrace() {
        heap = new ArrayList<>();
    }

    // 加入元素
    public void add(int value) {
        heap.add(value);

        int current = heap.size() - 1;

        // 向上調整
        while (current > 0) {
            int parent = (current - 1) / 2;

            if (heap.get(parent) >= heap.get(current)) {
                break;
            }

            // 交換 parent 與 child
            int temp = heap.get(parent);
            heap.set(parent, heap.get(current));
            heap.set(current, temp);

            current = parent;
        }
    }

    // 取得最大值
    public int peekMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap.get(0);
    }

    // 回傳目前 Heap 陣列
    public int[] snapshot() {
        int[] result = new int[heap.size()];

        for (int i = 0; i < heap.size(); i++) {
            result[i] = heap.get(i);
        }

        return result;
    }

    // 測試
    public static void main(String[] args) {

        MaxHeapInsertTrace maxHeap = new MaxHeapInsertTrace();

        int[] data = {25, 40, 10, 50, 30, 50};

        for (int value : data) {
            maxHeap.add(value);

            System.out.println(
                "add(" + value + ") -> "
                + Arrays.toString(maxHeap.snapshot())
            );
        }

        System.out.println("peekMax() = " + maxHeap.peekMax());
    }
}