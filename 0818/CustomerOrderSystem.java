class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class OrderItem {
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return productName +
               "，單價：" + price +
               "，數量：" + quantity +
               "，小計：" + getSubtotal();
    }
}

class CustomerOrder {
    private String orderId;
    private Customer customer;
    private OrderItem[] items;

    public CustomerOrder(String orderId, Customer customer, OrderItem[] items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
    }

    public double getTotalAmount() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public int getTotalQuantity() {
        int total = 0;

        for (OrderItem item : items) {
            total += item.getQuantity();
        }

        return total;
    }

    public void printSummary() {
        System.out.println("訂單編號：" + orderId);
        System.out.println("顧客編號：" + customer.getCustomerId());
        System.out.println("顧客姓名：" + customer.getName());

        System.out.println("=== 訂單明細 ===");

        for (OrderItem item : items) {
            System.out.println(item);
        }

        System.out.println("商品總數量：" + getTotalQuantity());
        System.out.println("訂單總額：" + getTotalAmount());
    }
}

public class CustomerOrderSystem {
    public static void main(String[] args) {
        Customer customer = new Customer("C001", "小明");

        OrderItem[] items = {
            new OrderItem("鍵盤", 800, 1),
            new OrderItem("滑鼠", 500, 2),
            new OrderItem("耳機", 1200, 1)
        };

        CustomerOrder order =
                new CustomerOrder("O001", customer, items);

        order.printSummary();
    }
}