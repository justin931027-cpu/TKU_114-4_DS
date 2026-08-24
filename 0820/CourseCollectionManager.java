import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class StudentCourse {
    String studentId;
    String name;
    int score;
    String tag;

    StudentCourse(String studentId, String name, int score, String tag) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return studentId + " " + name + " " + score + " " + tag;
    }
}

public class CourseCollectionManager {

    private List<StudentCourse> students = new ArrayList<>();
    private Set<String> studentIds = new HashSet<>();
    private Map<String, StudentCourse> studentMap = new HashMap<>();

    public void addStudent(StudentCourse student) {
        students.add(student);
        studentIds.add(student.studentId);
        studentMap.put(student.studentId, student);
    }

    public void updateScore(String studentId, int score) {
        StudentCourse student = studentMap.get(studentId);

        if (student != null) {
            student.score = score;
        }
    }

    public List<StudentCourse> findByTag(String tag) {
        List<StudentCourse> result = new ArrayList<>();

        for (StudentCourse student : students) {
            if (tag == null) {
                if (student.tag == null) {
                    result.add(student);
                }
            } else if (tag.equals(student.tag)) {
                result.add(student);
            }
        }

        return result;
    }

    public Map<String, Integer> scoreDistribution() {
        Map<String, Integer> result = new HashMap<>();

        result.put("A", 0);
        result.put("B", 0);
        result.put("C", 0);
        result.put("D", 0);
        result.put("F", 0);

        for (StudentCourse student : students) {
            String grade;

            if (student.score >= 90) {
                grade = "A";
            } else if (student.score >= 80) {
                grade = "B";
            } else if (student.score >= 70) {
                grade = "C";
            } else if (student.score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            result.put(grade, result.get(grade) + 1);
        }

        return result;
    }

    public List<StudentCourse> top(int count) {
        List<StudentCourse> copy = new ArrayList<>(students);

        copy.sort((a, b) -> Integer.compare(b.score, a.score));

        if (count >= copy.size()) {
            return copy;
        }

        if (count <= 0) {
            return new ArrayList<>();
        }

        return new ArrayList<>(copy.subList(0, count));
    }

    public void removeBelow(int minimum) {
        students.removeIf(student -> student.score < minimum);

        studentIds.clear();
        studentMap.clear();

        for (StudentCourse student : students) {
            studentIds.add(student.studentId);
            studentMap.put(student.studentId, student);
        }
    }

    public void printAll() {
        for (StudentCourse student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {

        CourseCollectionManager manager =
                new CourseCollectionManager();

        manager.addStudent(
                new StudentCourse("S001", "Amy", 95, "Java"));

        manager.addStudent(
                new StudentCourse("S002", "Bob", 82, "Web"));

        manager.addStudent(
                new StudentCourse("S003", "Cindy", 76, ""));

        manager.addStudent(
                new StudentCourse("S004", "David", 59, "Java"));

        manager.addStudent(
                new StudentCourse("S005", "Eric", 88, "Database"));

        manager.addStudent(
                new StudentCourse("S006", "Fiona", 67, null));

        manager.addStudent(
                new StudentCourse("S001", "Amy", 91, "Java"));

        System.out.println("原始資料：");
        manager.printAll();

        manager.updateScore("S002", 90);

        System.out.println();
        System.out.println("S002 更新成績後：");
        manager.printAll();

        System.out.println();
        System.out.println("tag = Java：");
        for (StudentCourse student :
                manager.findByTag("Java")) {
            System.out.println(student);
        }

        System.out.println();
        System.out.println("成績分布：");
        System.out.println(manager.scoreDistribution());

        System.out.println();
        System.out.println("前 3 名：");
        for (StudentCourse student :
                manager.top(3)) {
            System.out.println(student);
        }

        System.out.println();
        System.out.println("removeBelow(70) 後：");
        manager.removeBelow(70);
        manager.printAll();

        System.out.println();
        System.out.println("前 10 名：");
        for (StudentCourse student :
                manager.top(10)) {
            System.out.println(student);
        }
    }
}