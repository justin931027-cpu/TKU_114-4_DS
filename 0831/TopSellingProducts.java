import java.util.*;

public class TopSellingProducts {

    // 商品
    public static class Product {

        private String id;
        private int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        public String getId() {
            return id;
        }

        public int getSales() {
            return sales;
        }

        @Override
        public String toString() {
            return id + "|" + sales;
        }
    }

    private int k;

    // 先使用 Map 合併重複商品
    private HashMap<String, Integer> products;

    // 固定大小 Min Heap
    private ArrayList<Product> heap;

    public TopSellingProducts(int k) {
        this.k = k;
        products = new HashMap<>();
        heap = new ArrayList<>();
    }

    // 加入商品
    public void add(Product product) {

        if (product == null) {
            return;
        }

        String id = product.getId();

        // 合併相同 ID
        products.put(
            id,
            products.getOrDefault(id, 0) + product.getSales()
        );
    }

    // 建立 Top-K Heap
    private void buildHeap() {

        heap.clear();

        if (k <= 0) {
            return;
        }

        for (Map.Entry<String, Integer> entry : products.entrySet()) {

            Product product =
                    new Product(entry.getKey(), entry.getValue());

            if (heap.size() < k) {

                heap.add(product);

                int current = heap.size() - 1;

                while (current > 0) {

                    int parent = (current - 1) / 2;

                    if (!lowerPriority(heap.get(current),
                                        heap.get(parent))) {
                        break;
                    }

                    swap(current, parent);
                    current = parent;
                }

            } else {

                // 新商品比目前最差的 Top-K 更好
                if (lowerPriority(product, heap.get(0))) {

                    // 不處理
                    continue;
                }

                if (higherPriority(product, heap.get(0))) {

                    heap.set(0, product);
                    heapifyDown();
                }
            }
        }
    }

    // 判斷 a 是否是比較差的商品
    private boolean lowerPriority(Product a, Product b) {

        if (a.sales != b.sales) {
            return a.sales < b.sales;
        }

        return a.id.compareTo(b.id) > 0;
    }

    // 判斷 a 是否比較熱門
    private boolean higherPriority(Product a, Product b) {

        if (a.sales != b.sales) {
            return a.sales > b.sales;
        }

        return a.id.compareTo(b.id) < 0;
    }

    private void heapifyDown() {

        int current = 0;

        while (true) {

            int left = current * 2 + 1;
            int right = current * 2 + 2;

            if (left >= heap.size()) {
                break;
            }

            int worse = left;

            if (right < heap.size()
                    && lowerPriority(heap.get(right), heap.get(left))) {
                worse = right;
            }

            if (!lowerPriority(heap.get(worse), heap.get(current))) {
                break;
            }

            swap(current, worse);
            current = worse;
        }
    }

    private void swap(int a, int b) {

        Product temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // 取得 Top-K
    public List<Product> getTopK() {

        buildHeap();

        ArrayList<Product> result =
                new ArrayList<>(heap);

        // 銷量高到低
        result.sort((a, b) -> {

            if (a.sales != b.sales) {
                return Integer.compare(b.sales, a.sales);
            }

            return a.id.compareTo(b.id);
        });

        return result;
    }

    // 測試
    public static void main(String[] args) {

        TopSellingProducts tracker =
                new TopSellingProducts(3);

        tracker.add(new Product("P001", 100));
        tracker.add(new Product("P002", 300));
        tracker.add(new Product("P003", 200));
        tracker.add(new Product("P004", 500));
        tracker.add(new Product("P005", 400));

        // P002 重複，先合併
        tracker.add(new Product("P002", 250));

        System.out.println("Top-K:");

        for (Product product : tracker.getTopK()) {
            System.out.println(product);
        }
    }
}