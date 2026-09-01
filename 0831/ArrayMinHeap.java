import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {

    private int[] heap;
    private int size;

    public ArrayMinHeap() {
        heap = new int[4];
        size = 0;
    }

    // 加入
    public void add(int value) {

        // 容量不足，擴充兩倍
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }

        heap[size] = value;

        int current = size;
        size++;

        // 向上調整
        while (current > 0) {

            int parent = (current - 1) / 2;

            if (heap[parent] <= heap[current]) {
                break;
            }

            swap(parent, current);

            current = parent;
        }
    }

    // 查看最小值
    public int peek() {

        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap[0];
    }

    // 移除最小值
    public int remove() {

        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        int result = heap[0];

        heap[0] = heap[size - 1];
        size--;

        if (size > 0) {
            heapifyDown();
        }

        return result;
    }

    // 目前 Heap 狀態
    public int[] snapshot() {

        return Arrays.copyOf(heap, size);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapifyDown() {

        int current = 0;

        while (true) {

            int left = current * 2 + 1;
            int right = current * 2 + 2;

            if (left >= size) {
                break;
            }

            int smaller = left;

            if (right < size && heap[right] < heap[left]) {
                smaller = right;
            }

            if (heap[current] <= heap[smaller]) {
                break;
            }

            swap(current, smaller);

            current = smaller;
        }
    }

    private void swap(int a, int b) {

        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // 測試至少 20 筆
    public static void main(String[] args) {

        ArrayMinHeap minHeap = new ArrayMinHeap();

        int[] data = {
            50, 20, 80, 10, 60,
            30, 90, 40, 70, 15,
            25, 35, 45, 55, 65,
            75, 85, 95, 5, 100
        };

        // 20 筆資料
        for (int value : data) {
            minHeap.add(value);
        }

        System.out.println(
            "Heap = " + Arrays.toString(minHeap.snapshot())
        );

        System.out.println("peek = " + minHeap.peek());

        System.out.println("Remove:");

        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.remove());
        }
    }
}