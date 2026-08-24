import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Delivery {
    String id;
    String receiver;
    String address;

    Delivery(String id, String receiver, String address) {
        this.id = id;
        this.receiver = receiver;
        this.address = address;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", receiver=" + receiver +
                ", address=" + address;
    }
}

public class DeliveryWorkflowSystem {

    private Map<String, Delivery> deliveryMap = new HashMap<>();
    private Queue<Delivery> waitingQueue = new LinkedList<>();
    private Stack<Delivery> completedStack = new Stack<>();

    public void addDelivery(Delivery delivery) {
        if (deliveryMap.containsKey(delivery.id)) {
            System.out.println("配送編號重複，無法新增：" + delivery.id);
            return;
        }

        deliveryMap.put(delivery.id, delivery);
        waitingQueue.offer(delivery);

        System.out.println("新增成功：" + delivery);
    }

    public void processNext() {
        Delivery delivery = waitingQueue.poll();

        if (delivery == null) {
            System.out.println("目前沒有等待配送的資料");
            return;
        }

        completedStack.push(delivery);

        System.out.println("完成配送：" + delivery);
    }

    public void undo() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可以 undo 的配送資料");
            return;
        }

        Delivery delivery = completedStack.pop();

        waitingQueue.offer(delivery);

        System.out.println("Undo：" + delivery);
    }

    public void findById(String id) {
        Delivery delivery = deliveryMap.get(id);

        if (delivery == null) {
            System.out.println("查無配送編號：" + id);
        } else {
            System.out.println("查詢結果：" + delivery);
        }
    }

    public void showStatistics() {
        System.out.println("總配送資料：" + deliveryMap.size());
        System.out.println("等待配送：" + waitingQueue.size());
        System.out.println("已完成配送：" + completedStack.size());
    }

    public void showWaiting() {
        System.out.println("等待配送：");

        for (Delivery delivery : waitingQueue) {
            System.out.println(delivery);
        }
    }

    public void showCompleted() {
        System.out.println("已完成歷程：");

        for (Delivery delivery : completedStack) {
            System.out.println(delivery);
        }
    }

    public static void main(String[] args) {

        DeliveryWorkflowSystem system =
                new DeliveryWorkflowSystem();

        system.addDelivery(
                new Delivery("D001", "Amy", "Taipei"));

        system.addDelivery(
                new Delivery("D002", "Bob", "Taichung"));

        system.addDelivery(
                new Delivery("D003", "Cindy", "Kaohsiung"));

        system.addDelivery(
                new Delivery("D001", "David", "Tainan"));

        System.out.println();

        system.showStatistics();

        System.out.println();

        system.processNext();
        system.processNext();

        System.out.println();

        system.showStatistics();

        System.out.println();

        system.findById("D002");
        system.findById("D999");

        System.out.println();

        system.undo();

        System.out.println();

        system.showWaiting();

        System.out.println();

        system.showCompleted();

        System.out.println();

        system.showStatistics();
    }
}