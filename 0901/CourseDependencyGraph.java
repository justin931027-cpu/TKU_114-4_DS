import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseDependencyGraph {

    // 課程 -> 後續課程
    private Map<String, Set<String>> adjacencyList;

    public CourseDependencyGraph() {
        adjacencyList = new HashMap<>();
    }

    // 新增課程
    public void addCourse(String course) {

        if (!adjacencyList.containsKey(course)) {
            adjacencyList.put(course, new HashSet<>());
        }
    }

    // 新增 prerequisite
    // prerequisite -> course
    public void addPrerequisite(
            String prerequisite,
            String course) {

        addCourse(prerequisite);
        addCourse(course);

        adjacencyList
            .get(prerequisite)
            .add(course);
    }

    // 查詢 prerequisites
    public List<String> getPrerequisites(
            String course) {

        List<String> result = new ArrayList<>();

        if (!adjacencyList.containsKey(course)) {
            return result;
        }

        for (String currentCourse :
                adjacencyList.keySet()) {

            if (adjacencyList
                    .get(currentCourse)
                    .contains(course)) {

                result.add(currentCourse);
            }
        }

        result.sort(String::compareTo);

        return result;
    }

    // 查詢後續課程
    public List<String> getNextCourses(
            String course) {

        if (!adjacencyList.containsKey(course)) {
            return new ArrayList<>();
        }

        List<String> result =
            new ArrayList<>(
                adjacencyList.get(course)
            );

        result.sort(String::compareTo);

        return result;
    }

    // In-degree
    public int inDegree(String course) {

        if (!adjacencyList.containsKey(course)) {
            return 0;
        }

        int count = 0;

        for (String currentCourse :
                adjacencyList.keySet()) {

            if (adjacencyList
                    .get(currentCourse)
                    .contains(course)) {

                count++;
            }
        }

        return count;
    }

    // Out-degree
    public int outDegree(String course) {

        if (!adjacencyList.containsKey(course)) {
            return 0;
        }

        return adjacencyList.get(course).size();
    }

    // 課程數量
    public int courseCount() {
        return adjacencyList.size();
    }

    // Edge 數量
    public int edgeCount() {

        int count = 0;

        for (Set<String> courses :
                adjacencyList.values()) {

            count += courses.size();
        }

        return count;
    }

    // 顯示 Graph
    public void printGraph() {

        System.out.println(
            "===== Course Dependency Graph ====="
        );

        List<String> courses =
            new ArrayList<>(
                adjacencyList.keySet()
            );

        courses.sort(String::compareTo);

        for (String course : courses) {

            System.out.println(
                course +
                " -> " +
                getNextCourses(course)
            );
        }
    }

    // 輸出每門課的 prerequisites、後續課程
    public void printCourseInfo() {

        List<String> courses =
            new ArrayList<>(
                adjacencyList.keySet()
            );

        courses.sort(String::compareTo);

        System.out.println();
        System.out.println(
            "========== 課程資訊 =========="
        );

        for (String course : courses) {

            System.out.println(
                "課程：" + course
            );

            System.out.println(
                "Prerequisites："
                + getPrerequisites(course)
            );

            System.out.println(
                "後續課程："
                + getNextCourses(course)
            );

            System.out.println(
                "In-degree："
                + inDegree(course)
            );

            System.out.println(
                "Out-degree："
                + outDegree(course)
            );

            System.out.println(
                "----------------------------"
            );
        }
    }

    public static void main(String[] args) {

        CourseDependencyGraph graph =
            new CourseDependencyGraph();

        // 建立課程
        graph.addCourse("CS101");
        graph.addCourse("CS102");
        graph.addCourse("CS201");
        graph.addCourse("CS202");
        graph.addCourse("CS301");

        /*
         * CS101 -> CS201
         * CS102 -> CS201
         * CS201 -> CS301
         * CS102 -> CS202
         * CS202 -> CS301
         */

        graph.addPrerequisite("CS101", "CS201");
        graph.addPrerequisite("CS102", "CS201");

        graph.addPrerequisite("CS201", "CS301");

        graph.addPrerequisite("CS102", "CS202");
        graph.addPrerequisite("CS202", "CS301");

        graph.printGraph();

        graph.printCourseInfo();

        System.out.println();

        System.out.println(
            "CS301 的 prerequisites："
            + graph.getPrerequisites("CS301")
        );

        System.out.println(
            "CS102 的後續課程："
            + graph.getNextCourses("CS102")
        );

        System.out.println();

        System.out.println(
            "CS301 in-degree："
            + graph.inDegree("CS301")
        );

        System.out.println(
            "CS102 out-degree："
            + graph.outDegree("CS102")
        );

        System.out.println();

        System.out.println(
            "Course Count："
            + graph.courseCount()
        );

        System.out.println(
            "Edge Count："
            + graph.edgeCount()
        );
    }
}