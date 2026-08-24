import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    String studentId;
    String courseCode;

    Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Enrollment)) {
            return false;
        }

        Enrollment other = (Enrollment) obj;

        return Objects.equals(studentId, other.studentId)
                && Objects.equals(courseCode, other.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return "studentId=" + studentId
                + ", courseCode=" + courseCode;
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {

        Set<Enrollment> enrollments = new HashSet<>();

        enrollments.add(new Enrollment("S001", "JAVA"));
        enrollments.add(new Enrollment("S002", "JAVA"));
        enrollments.add(new Enrollment("S001", "DB"));
        enrollments.add(new Enrollment("S001", "JAVA"));
        enrollments.add(new Enrollment("S003", "WEB"));
        enrollments.add(new Enrollment("S002", "JAVA"));

        System.out.println("報名資料：");

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

        System.out.println();
        System.out.println("不重複報名筆數：" + enrollments.size());
    }
}