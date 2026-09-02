import java.util.*;

public class EnrollmentConflictSet {
    static class Enrollment {
        String studentId;
        String courseId;

        Enrollment(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        String toCompositeKey() {
            return studentId + "_" + courseId;
        }
    }

    public static void processEnrollments(List<Enrollment> enrollments) {
        Set<String> seenKeys = new HashSet<>();
        List<Enrollment> duplicateRecords = new ArrayList<>();

        Map<String, Set<String>> studentCourses = new HashMap<>();
        Map<String, Integer> courseStudentCounts = new HashMap<>();

        for (Enrollment e : enrollments) {
            String key = e.toCompositeKey();
            if (seenKeys.contains(key)) {
                duplicateRecords.add(e);
            } else {
                seenKeys.add(key);
                studentCourses.computeIfAbsent(e.studentId, k -> new HashSet<>()).add(e.courseId);
                courseStudentCounts.put(e.courseId, courseStudentCounts.getOrDefault(e.courseId, 0) + 1);
            }
        }

        System.out.println("=== 重複選課記錄 ===");
        if (duplicateRecords.isEmpty()) {
            System.out.println("無重複選課記錄。");
        } else {
            for (Enrollment dup : duplicateRecords) {
                System.out.printf("發現重複: 學號 %s 再次加選 課程 %s\n", dup.studentId, dup.courseId);
            }
        }

        System.out.println("\n=== 學生選課集合 ===");
        for (var entry : studentCourses.entrySet()) {
            System.out.println("學號 " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n=== 各課程修課人數 ===");
        for (var entry : courseStudentCounts.entrySet()) {
            System.out.println("課程 " + entry.getKey() + ": " + entry.getValue() + " 人");
        }
    }

    public static void main(String[] args) {
        List<Enrollment> requests = Arrays.asList(
                new Enrollment("S001", "CS101"),
                new Enrollment("S002", "CS101"),
                new Enrollment("S001", "MATH201"),
                new Enrollment("S001", "CS101"), // 重複
                new Enrollment("S003", "PHYS101"),
                new Enrollment("S002", "MATH201"),
                new Enrollment("S002", "CS101")  // 重複
        );

        processEnrollments(requests);
    }
}