import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void addEnd(List<Integer> list, int value) {
        list.add(value);
    }

    static void insertAt(List<Integer> list, int index, int value) {
        list.add(index, value);
    }

    static int search(List<Integer> list, int value) {
        return list.indexOf(value);
    }

    static void removeValue(List<Integer> list, int value) {
        list.remove(Integer.valueOf(value));
    }

    static int sum(List<Integer> list) {
        int total = 0;

        for (int value : list) {
            total += value;
        }

        return total;
    }

    static void testList(List<Integer> list) {
        addEnd(list, 10);
        addEnd(list, 20);
        addEnd(list, 30);

        System.out.println("尾端新增後：" + list);

        insertAt(list, 1, 15);
        System.out.println("指定位置插入後：" + list);

        System.out.println("搜尋 20 的位置：" + search(list, 20));

        removeValue(list, 15);
        System.out.println("刪除 15 後：" + list);

        System.out.println("總和：" + sum(list));
    }

    public static void main(String[] args) {

        System.out.println("ArrayList 測試：");
        List<Integer> arrayList = new ArrayList<>();
        testList(arrayList);

        System.out.println();

        System.out.println("LinkedList 測試：");
        List<Integer> linkedList = new LinkedList<>();
        testList(linkedList);

        System.out.println();

        System.out.println("功能結果：");
        System.out.println("ArrayList 與 LinkedList 都能完成相同操作。");

        System.out.println();
        System.out.println("可能的內部成本差異：");
        System.out.println("ArrayList 使用動態陣列，隨機存取較快，");
        System.out.println("但在中間插入或刪除時，可能需要搬移後面的元素。");
        System.out.println("LinkedList 使用鏈結節點，");
        System.out.println("已找到位置後插入或刪除較方便，");
        System.out.println("但搜尋或依索引存取通常需要逐一走訪節點。");
    }
}