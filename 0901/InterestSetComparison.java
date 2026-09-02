import java.util.HashSet;
import java.util.Set;

public class InterestSetComparison {

    // Union 聯集
    public static Set<String> union(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        result.addAll(first);
        result.addAll(second);

        return result;
    }

    // Intersection 交集
    public static Set<String> intersection(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        for (String item : first) {

            if (second.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    // 第一個有、第二個沒有
    public static Set<String> firstOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        for (String item : first) {

            if (!second.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    // 第二個有、第一個沒有
    public static Set<String> secondOnly(
            Set<String> first,
            Set<String> second) {

        Set<String> result = new HashSet<>();

        for (String item : second) {

            if (!first.contains(item)) {
                result.add(item);
            }
        }

        return result;
    }

    // 顯示結果
    public static void printComparison(
            Set<String> first,
            Set<String> second) {

        System.out.println("===== Interest Set Comparison =====");

        System.out.println("第一個 Set：" + first);
        System.out.println("第二個 Set：" + second);

        System.out.println();

        System.out.println("Union：");
        System.out.println(union(first, second));

        System.out.println();

        System.out.println("Intersection：");
        System.out.println(intersection(first, second));

        System.out.println();

        System.out.println("First Only：");
        System.out.println(firstOnly(first, second));

        System.out.println();

        System.out.println("Second Only：");
        System.out.println(secondOnly(first, second));
    }

    public static void main(String[] args) {

        Set<String> first = new HashSet<>();

        first.add("Pokemon");
        first.add("Anime");
        first.add("KPOP");
        first.add("Gaming");

        Set<String> second = new HashSet<>();

        second.add("Anime");
        second.add("KPOP");
        second.add("Travel");
        second.add("Food");

        printComparison(first, second);

        // 確認原本 Set 沒有被修改
        System.out.println();
        System.out.println("原本第一個 Set：" + first);
        System.out.println("原本第二個 Set：" + second);
    }
}