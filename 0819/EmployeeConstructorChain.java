abstract class EmployeeBase {
    protected int id;
    protected String name;

    public EmployeeBase(int id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("EmployeeBase constructor");
    }

    public abstract double calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private double salary;

    public FullTimeEmployee(int id, String name, double salary) {
        super(id, name);
        this.salary = salary < 0 ? 0 : salary;
        System.out.println("FullTimeEmployee constructor");
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private double hourlyRate;
    private double hours;

    public PartTimeEmployee(int id, String name, double hourlyRate, double hours) {
        super(id, name);
        this.hourlyRate = hourlyRate < 0 ? 0 : hourlyRate;
        this.hours = hours < 0 ? 0 : hours;
        System.out.println("PartTimeEmployee constructor");
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {
        FullTimeEmployee fullTime =
                new FullTimeEmployee(1, "王小明", 40000);

        System.out.println("全職員工薪資：" + fullTime.calculatePay());

        System.out.println();

        PartTimeEmployee partTime =
                new PartTimeEmployee(2, "陳小華", 200, 80);

        System.out.println("兼職員工薪資：" + partTime.calculatePay());
    }
}