package final_exam;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q05_StudentHashIndex {

    // 學生 → 課程
    private Map<String, Set<String>> studentCourses;

    // 課程 → 學生
    private Map<String, Set<String>> courseStudents;

    // Constructor
    public Q05_StudentHashIndex() {
        studentCourses = new HashMap<>();
        courseStudents = new HashMap<>();
    }

    // 正規化 studentId / courseId
    private String normalize(String value) {

        if (value == null) {
            return null;
        }

        value = value.trim();

        if (value.isEmpty()) {
            return null;
        }

        return value.toUpperCase();
    }

    // 學生選課
    public boolean enroll(String studentId, String courseId) {

        studentId = normalize(studentId);
        courseId = normalize(courseId);

        // null / blank → false
        if (studentId == null || courseId == null) {
            return false;
        }

        // 取得學生的課程 Set
        Set<String> courses =
                studentCourses.computeIfAbsent(
                        studentId,
                        k -> new HashSet<>()
                );

        // 重複選課 → false
        if (courses.contains(courseId)) {
            return false;
        }

        // 加入學生 → 課程索引
        courses.add(courseId);

        // 加入課程 → 學生索引
        courseStudents
                .computeIfAbsent(courseId, k -> new HashSet<>())
                .add(studentId);

        return true;
    }

    // 學生退選
    public boolean drop(String studentId, String courseId) {

        studentId = normalize(studentId);
        courseId = normalize(courseId);

        // null / blank → false
        if (studentId == null || courseId == null) {
            return false;
        }

        // 找學生的課程
        Set<String> courses = studentCourses.get(studentId);

        // 學生不存在或沒有這門課
        if (courses == null || !courses.contains(courseId)) {
            return false;
        }

        // 從學生課程中移除
        courses.remove(courseId);

        // 如果學生已經沒有課程
        // 移除 student key
        if (courses.isEmpty()) {
            studentCourses.remove(studentId);
        }

        // 從課程學生索引中移除
        Set<String> students = courseStudents.get(courseId);

        if (students != null) {
            students.remove(studentId);

            // 如果課程已經沒有學生
            // 可以將 course key 一起移除
            if (students.isEmpty()) {
                courseStudents.remove(courseId);
            }
        }

        return true;
    }

    // 查詢某學生的課程
    public Set<String> coursesOf(String studentId) {

        studentId = normalize(studentId);

        // 無效輸入
        if (studentId == null) {
            return new HashSet<>();
        }

        Set<String> courses = studentCourses.get(studentId);

        // 找不到學生
        if (courses == null) {
            return new HashSet<>();
        }

        // 回傳獨立 Set
        return new HashSet<>(courses);
    }

    // 查詢某課程的學生
    public Set<String> studentsIn(String courseId) {

        courseId = normalize(courseId);

        // 無效輸入
        if (courseId == null) {
            return new HashSet<>();
        }

        Set<String> students = courseStudents.get(courseId);

        // 找不到課程
        if (students == null) {
            return new HashSet<>();
        }

        // 回傳獨立 Set
        return new HashSet<>(students);
    }

    // 總選課數
    public int enrollmentCount() {

        int count = 0;

        for (Set<String> courses : studentCourses.values()) {
            count += courses.size();
        }

        return count;
    }
}