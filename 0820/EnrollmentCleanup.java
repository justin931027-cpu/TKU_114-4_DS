import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {
    public static void main(String[] args) {

        List<String> students = new ArrayList<>();

        students.add("Amy");
        students.add("Bob");
        students.add("");
        students.add("Amy");
        students.add(null);
        students.add("Cindy");
        students.add("   ");
        students.add("Bob");
        students.add("David");

        System.out.println("清理前：");
        System.out.println(students);

        Iterator<String> iterator = students.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();

            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        System.out.println();
        System.out.println("清理後：");
        System.out.println(students);

        Set<String> seen = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (String name : students) {
            if (!seen.add(name)) {
                duplicates.add(name);
            }
        }

        System.out.println();
        System.out.println("重複姓名：");
        System.out.println(duplicates);
    }
}