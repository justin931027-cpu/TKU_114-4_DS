import java.util.List;

public class HeapPropertyValidator {

    // 判斷是否為 Min Heap
    public static boolean isMinHeap(List<Integer> list) {

        // null
        if (list == null) {
            return false;
        }

        // 空集合
        if (list.isEmpty()) {
            return true;
        }

        // 逐一檢查 parent 和 child
        for (int i = 0; i < list.size(); i++) {

            Integer current = list.get(i);

            if (current == null) {
                return false;
            }

            // 左子節點
            int left = 2 * i + 1;

            // 右子節點
            int right = 2 * i + 2;

            // parent <= left child
            if (left < list.size()) {

                if (list.get(i) > list.get(left)) {
                    return false;
                }
            }

            // parent <= right child
            if (right < list.size()) {

                if (list.get(i) > list.get(right)) {
                    return false;
                }
            }
        }

        return true;
    }

    // 判斷是否為 Max Heap
    public static boolean isMaxHeap(List<Integer> list) {

        // null
        if (list == null) {
            return false;
        }

        // 空集合
        if (list.isEmpty()) {
            return true;
        }

        // 逐一檢查 parent 和 child
        for (int i = 0; i < list.size(); i++) {

            Integer current = list.get(i);

            if (current == null) {
                return false;
            }

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // parent >= left child
            if (left < list.size()) {

                if (list.get(i) < list.get(left)) {
                    return false;
                }
            }

            // parent >= right child
            if (right < list.size()) {

                if (list.get(i) < list.get(right)) {
                    return false;
                }
            }
        }

        return true;
    }

    // 測試
    public static void main(String[] args) {

        System.out.println(
            "Min Heap: "
            + isMinHeap(List.of(1, 3, 2, 7, 8, 4))
        );

        System.out.println(
            "Min Heap: "
            + isMinHeap(List.of(1, 3, 2, 0, 8))
        );

        System.out.println(
            "Max Heap: "
            + isMaxHeap(List.of(9, 7, 8, 3, 5, 6))
        );

        System.out.println(
            "Max Heap: "
            + isMaxHeap(List.of(9, 7, 10, 3, 5))
        );

        System.out.println(
            "null Min Heap: "
            + isMinHeap(null)
        );

        System.out.println(
            "empty Min Heap: "
            + isMinHeap(List.of())
        );

        System.out.println(
            "one element Max Heap: "
            + isMaxHeap(List.of(10))
        );
    }
}