import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseGradeMap {

    private Map<String, List<Integer>> courseGrades;

    public CourseGradeMap() {
        courseGrades = new HashMap<>();
    }

    // 新增課程
    public void addCourse(String courseId) {

        if (!courseGrades.containsKey(courseId)) {
            courseGrades.put(courseId, new ArrayList<>());
        }
    }

    // 新增成績
    public void addGrade(String courseId, int grade) {

        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException(
                "成績必須介於 0 到 100"
            );
        }

        addCourse(courseId);

        courseGrades.get(courseId).add(grade);
    }

    // 取得某課程所有成績
    public List<Integer> getGrades(String courseId) {

        if (!courseGrades.containsKey(courseId)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(courseGrades.get(courseId));
    }

    // 計算平均
    public double getAverage(String courseId) {

        List<Integer> grades = courseGrades.get(courseId);

        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (int grade : grades) {
            total += grade;
        }

        return (double) total / grades.size();
    }

    // 找最高分
    public int getHighestGrade(String courseId) {

        List<Integer> grades = courseGrades.get(courseId);

        if (grades == null || grades.isEmpty()) {
            return -1;
        }

        return Collections.max(grades);
    }

    // 找最低分
    public int getLowestGrade(String courseId) {

        List<Integer> grades = courseGrades.get(courseId);

        if (grades == null || grades.isEmpty()) {
            return -1;
        }

        return Collections.min(grades);
    }

    // 課程數量
    public int getCourseCount() {
        return courseGrades.size();
    }

    // 依課號排序輸出報告
    public void printReport() {

        List<String> courseIds =
            new ArrayList<>(courseGrades.keySet());

        Collections.sort(courseIds);

        System.out.println("========== 課程成績統計 ==========");

        for (String courseId : courseIds) {

            List<Integer> grades = courseGrades.get(courseId);

            System.out.println("課程：" + courseId);
            System.out.println("成績：" + grades);

            System.out.printf(
                "平均：%.2f%n",
                getAverage(courseId)
            );

            System.out.println(
                "最高分：" + getHighestGrade(courseId)
            );

            System.out.println(
                "最低分：" + getLowestGrade(courseId)
            );

            System.out.println("--------------------------------");
        }
    }

    public static void main(String[] args) {

        CourseGradeMap map = new CourseGradeMap();

        // Java
        map.addGrade("CS101", 80);
        map.addGrade("CS101", 90);
        map.addGrade("CS101", 75);
        map.addGrade("CS101", 95);

        // Data Structure
        map.addGrade("CS202", 88);
        map.addGrade("CS202", 92);
        map.addGrade("CS202", 78);

        // Database
        map.addGrade("CS303", 70);
        map.addGrade("CS303", 85);
        map.addGrade("CS303", 90);

        map.printReport();
    }
}