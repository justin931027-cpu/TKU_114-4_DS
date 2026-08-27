import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Q06_EnrollmentIndex {

    private final Map<String, Set<String>> data = new LinkedHashMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty()
                || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = data.computeIfAbsent(
                courseCode,
                k -> new LinkedHashSet<>()
        );

        return students.add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty()
                || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students = data.get(courseCode);

        if (students == null) {
            return false;
        }

        boolean removed = students.remove(studentId);

        if (students.isEmpty()) {
            data.remove(courseCode);
        }

        return removed;
    }

    public int courseSize(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            return 0;
        }

        Set<String> students = data.get(courseCode.trim());

        if (students == null) {
            return 0;
        }

        return students.size();
    }

    public List<String> studentsOf(String courseCode) {
        List<String> result = new ArrayList<>();

        if (courseCode == null || courseCode.trim().isEmpty()) {
            return result;
        }

        Set<String> students = data.get(courseCode.trim());

        if (students != null) {
            result.addAll(students);
        }

        return result;
    }

    public List<String> coursesOf(String studentId) {
        List<String> result = new ArrayList<>();

        if (studentId == null || studentId.trim().isEmpty()) {
            return result;
        }

        studentId = studentId.trim();

        for (Map.Entry<String, Set<String>> entry : data.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Map<String, Integer> summary() {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (Map.Entry<String, Set<String>> entry : data.entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }

        return result;
    }
}