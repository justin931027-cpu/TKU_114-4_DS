abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract double calculatePay();
}

class MonthlyEmployee extends Employee {
    private double monthlySalary;

    public MonthlyEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private double hours;

    public HourlyEmployee(String name, double hourlyRate, double hours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hours = hours;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hours;
    }
}

class CommissionEmployee extends Employee {
    private double sales;
    private double commissionRate;

    public CommissionEmployee(String name, double sales, double commissionRate) {
        super(name);
        this.sales = sales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatePay() {
        return sales * commissionRate;
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = {
            new MonthlyEmployee("王小明", 40000),
            new HourlyEmployee("陳小華", 200, 80),
            new CommissionEmployee("林大偉", 100000, 0.1),
            new MonthlyEmployee("張美玲", 45000)
        };

        double totalPay = 0;
        double maxPay = 0;

        for (Employee employee : employees) {
            double pay = employee.calculatePay();

            System.out.println(employee.name + " 薪資：" + pay);

            totalPay += pay;

            if (pay > maxPay) {
                maxPay = pay;
            }
        }

        System.out.println("薪資總額：" + totalPay);
        System.out.println("最高薪資：" + maxPay);
    }
}