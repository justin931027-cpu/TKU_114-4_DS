import java.util.*;

public class CoursePlanningGraph {
    // prerequisite -> List of dependent courses
    private final Map<String, List<String>> prereqGraph = new HashMap<>();

    public void addCourse(String course) {
        prereqGraph.putIfAbsent(course, new ArrayList<>());
    }

    // fromCourse 為 toCourse 的先修課 (fromCourse -> toCourse)
    public void addPrerequisite(String fromCourse, String toCourse) {
        addCourse(fromCourse);
        addCourse(toCourse);
        prereqGraph.get(fromCourse).add(toCourse);
    }

    public Set<String> getAffectedCourses(String failedCourse) {
        Set<String> affected = new LinkedHashSet<>();
        if (failedCourse == null || !prereqGraph.containsKey(failedCourse)) {
            System.out.println("[邊界/警告] 課程不存在: " + failedCourse);
            return affected;
        }

        Set<String> visited = new HashSet<>();
        dfs(failedCourse, visited, affected);
        affected.remove(failedCourse); // 排除自身，只保留受連帶影響的後續課程
        return affected;
    }

    private void dfs(String curr, Set<String> visited, Set<String> affected) {
        visited.add(curr);
        for (String next : prereqGraph.getOrDefault(curr, Collections.emptyList())) {
            affected.add(next);
            if (!visited.contains(next)) {
                dfs(next, visited, affected);
            }
        }
    }

    public static void main(String[] args) {
        CoursePlanningGraph planner = new CoursePlanningGraph();
        planner.addPrerequisite("CS101_計概", "CS201_資料結構");
        planner.addPrerequisite("CS201_資料結構", "CS301_演算法");
        planner.addPrerequisite("CS201_資料結構", "CS302_軟體工程");
        planner.addPrerequisite("CS301_演算法", "CS401_高等演算法");
        planner.addCourse("GEN101_通識體育"); // 無任何先修關係的獨立課

        System.out.println("--- 測試 1: 一般連鎖擋修推演 (CS101 被當) ---");
        System.out.println("若 CS101 不及格，受影響無法修習的課程: " + planner.getAffectedCourses("CS101_計概"));

        System.out.println("\n--- 測試 2: 中段課程擋修 (CS201 被當) ---");
        System.out.println("若 CS201 不及格，受影響無法修習的課程: " + planner.getAffectedCourses("CS201_資料結構"));

        System.out.println("\n--- 測試 3: 邊界案例 (葉節點、無影響課程、不存在課程) ---");
        System.out.println("若 GEN101 不及格: " + planner.getAffectedCourses("GEN101_通識體育"));
        System.out.println("若 Unknown 不存在: " + planner.getAffectedCourses("Unknown"));
        System.out.println("若 null 輸入: " + planner.getAffectedCourses(null));
    }
}