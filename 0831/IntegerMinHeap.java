import java.util.NoSuchElementException;

public class IntegerMinHeap {

    private int[] heap;
    private int size;

    public IntegerMinHeap() {
        heap = new int[10];
        size = 0;
    }

    // 加入元素
    public void add(int value) {

        // 空間不足，擴充
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];

            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }

            heap = newHeap;
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

        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        return heap[0];
    }

    // 移除最小值
    public int removeMin() {

        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        int result = heap[0];

        // 把最後一個元素放到 root
        heap[0] = heap[size - 1];
        size--;

        // 向下調整
        if (!isEmpty()) {
            int current = 0;

            while (true) {

                int left = current * 2 + 1;
                int right = current * 2 + 2;

                if (left >= size) {
                    break;
                }

                int smallerChild = left;

                if (right < size && heap[right] < heap[left]) {
                    smallerChild = right;
                }

                if (heap[current] <= heap[smallerChild]) {
                    break;
                }

                swap(current, smallerChild);

                current = smallerChild;
            }
        }

        return result;
    }

    // Heap 大小
    public int size() {
        return size;
    }

    // 是否為空
    public boolean isEmpty() {
        return size == 0;
    }

    // 交換
    private void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    // 測試
    public static void main(String[] args) {

        IntegerMinHeap minHeap = new IntegerMinHeap();

        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(50);
        minHeap.add(5);
        minHeap.add(20);

        System.out.println("peek = " + minHeap.peek());
        System.out.println("size = " + minHeap.size());

        while (!minHeap.isEmpty()) {
            System.out.println("removeMin = " + minHeap.removeMin());
        }

        System.out.println("isEmpty = " + minHeap.isEmpty());

        // 測試空 Heap 是否丟出例外
        try {
            minHeap.peek();
        } catch (NoSuchElementException e) {
            System.out.println("peek() exception OK");
        }

        try {
            minHeap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println("removeMin() exception OK");
        }
    }
}