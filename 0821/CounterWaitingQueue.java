import java.util.ArrayDeque;
import java.util.Deque;

class Customer {
    String name;
    int number;

    Customer(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return "號碼：" + number + "，姓名：" + name;
    }
}

public class CounterWaitingQueue {

    private Deque<Customer> queue = new ArrayDeque<>();

    public void addCustomer(Customer customer) {
        queue.offerLast(customer);
        System.out.println("加入排隊：" + customer);
    }

    public Customer nextCustomer() {
        return queue.peekFirst();
    }

    public Customer serveNext() {
        return queue.pollFirst();
    }

    public int waitingCount() {
        return queue.size();
    }

    public static void main(String[] args) {

        CounterWaitingQueue counter = new CounterWaitingQueue();

        counter.addCustomer(new Customer("Amy", 1));
        counter.addCustomer(new Customer("Bob", 2));
        counter.addCustomer(new Customer("Cindy", 3));
        counter.addCustomer(new Customer("David", 4));

        System.out.println();
        System.out.println("目前等候人數：" + counter.waitingCount());
        System.out.println("下一位顧客：" + counter.nextCustomer());

        System.out.println();
        System.out.println("服務：" + counter.serveNext());
        System.out.println("下一位顧客：" + counter.nextCustomer());
        System.out.println("目前等候人數：" + counter.waitingCount());

        System.out.println();
        System.out.println("服務：" + counter.serveNext());
        System.out.println("服務：" + counter.serveNext());
        System.out.println("服務：" + counter.serveNext());

        System.out.println();
        System.out.println("目前等候人數：" + counter.waitingCount());
        System.out.println("下一位顧客：" + counter.nextCustomer());

        Customer customer = counter.serveNext();

        if (customer == null) {
            System.out.println("目前沒有顧客等待");
        }
    }
}