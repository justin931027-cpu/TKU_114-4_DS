class CourseGrade {
    private String studentId;
    private String name;
    private double regularScore;
    private double midtermScore;
    private double finalExamScore;
    private double attendanceScore;

    public CourseGrade(String studentId, String name,
                       double regularScore,
                       double midtermScore,
                       double finalExamScore,
                       double attendanceScore) {
        this.studentId = studentId;
        this.name = name;
        this.regularScore = checkScore(regularScore);
        this.midtermScore = checkScore(midtermScore);
        this.finalExamScore = checkScore(finalExamScore);
        this.attendanceScore = checkScore(attendanceScore);
    }

    private double checkScore(double score) {
        if (score < 0) {
            return 0;
        } else if (score > 100) {
            return 100;
        } else {
            return score;
        }
    }

    public double calculateFinalScore() {
        return regularScore * 0.5
                + midtermScore * 0.2
                + finalExamScore * 0.2
                + attendanceScore * 0.1;
    }

    public String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "學號：" + studentId
                + "，姓名：" + name
                + "，平時：" + regularScore
                + "，期中：" + midtermScore
                + "，期末：" + finalExamScore
                + "，出席：" + attendanceScore
                + "，總分：" + calculateFinalScore()
                + "，等級：" + getLevel();
    }
}

public class CourseGradeManager {
    public static void main(String[] args) {
        CourseGrade[] grades = {
            new CourseGrade("S001", "小明", 90, 85, 88, 100),
            new CourseGrade("S002", "小華", 75, 80, 70, 90),
            new CourseGrade("S003", "小美", 60, 65, 70, 80),
            new CourseGrade("S004", "小強", 50, 55, 45, 70),
            new CourseGrade("S005", "小安", 95, 92, 98, 100)
        };

        System.out.println("=== 所有學生成績 ===");

        for (CourseGrade grade : grades) {
            System.out.println(grade);
        }

        double total = 0;
        CourseGrade highest = grades[0];

        for (CourseGrade grade : grades) {
            total += grade.calculateFinalScore();

            if (grade.calculateFinalScore()
                    > highest.calculateFinalScore()) {
                highest = grade;
            }
        }

        double average = total / grades.length;

        System.out.println("\n平均成績：" + average);
        System.out.println("最高分學生：" + highest);

        System.out.println("\n=== 不及格名單 ===");

        for (CourseGrade grade : grades) {
            if (grade.calculateFinalScore() < 60) {
                System.out.println(grade);
            }
        }
    }
}