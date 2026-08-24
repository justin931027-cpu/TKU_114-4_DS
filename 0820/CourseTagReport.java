import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;

public class CourseTagReport {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> tagList = new ArrayList<>();
        Set<String> tagSet = new HashSet<>();
        Map<String, Integer> tagCount = new HashMap<>();

        System.out.print("請輸入課程標籤數量：");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("請輸入第 " + (i + 1) + " 個標籤：");
            String tag = sc.nextLine();

            tagList.add(tag);
            tagSet.add(tag);
            tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
        }

        System.out.println();
        System.out.println("List<String>：保存原始輸入順序");
        System.out.println(tagList);

        System.out.println();
        System.out.println("Set<String>：保存不重複標籤");
        System.out.println(tagSet);

        System.out.println();
        System.out.println("Map<String, Integer>：統計每個標籤出現次數");
        System.out.println(tagCount);

        sc.close();
    }
}