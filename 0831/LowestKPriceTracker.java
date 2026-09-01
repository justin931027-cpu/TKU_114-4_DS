import java.util.*;

public class LowestKPriceTracker {

    private int k;

    // Max Heap
    private ArrayList<Integer> heap;

    public LowestKPriceTracker(int k) {
        this.k = k;
        this.heap = new ArrayList<>();
    }

    // 加入價格
    public void add(Integer price) {

        // K <= 0 不處理
        if (k <= 0) {
            return;
        }

        // null 忽略
        if (price == null) {
            return;
        }

        // 負數忽略
        if (price < 0) {
            return;
        }

        // 還沒滿
        if (heap.size() < k) {

            heap.add(price);

            int current = heap.size() - 1;

            while (current > 0) {

                int parent = (current - 1) / 2;

                // Max Heap
                if (heap.get(parent) >= heap.get(current)) {
                    break;
                }

                swap(parent, current);

                current = parent;
            }

        } else {

            // Heap root 是目前 K 個價格中最大的
            if (price < heap.get(0)) {

                heap.set(0, price);

                // 向下調整
                int current = 0;

                while (true) {

                    int left = current * 2 + 1;
                    int right = current * 2 + 2;

                    if (left >= heap.size()) {
                        break;
                    }

                    int largerChild = left;

                    if (right < heap.size()
                            && heap.get(right) > heap.get(left)) {
                        largerChild = right;
                    }

                    if (heap.get(current) >= heap.get(largerChild)) {
                        break;
                    }

                    swap(current, largerChild);

                    current = largerChild;
                }
            }
        }
    }

    // 取得目前最低 K 個價格
    public List<Integer> getLowestPrices() {

        if (k <= 0) {
            return new ArrayList<>();
        }

        ArrayList<Integer> result = new ArrayList<>(heap);

        // 結果依價格遞增
        Collections.sort(result);

        return result;
    }

    // 交換
    private void swap(int a, int b) {

        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // 測試
    public static void main(String[] args) {

        LowestKPriceTracker tracker =
                new LowestKPriceTracker(3);

        tracker.add(100);
        tracker.add(50);
        tracker.add(80);
        tracker.add(30);
        tracker.add(20);

        tracker.add(null);
        tracker.add(-10);

        System.out.println(tracker.getLowestPrices());
    }
}