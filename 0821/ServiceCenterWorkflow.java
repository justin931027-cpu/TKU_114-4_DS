import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class ServiceTicket {
    String id;
    String customerName;
    String issue;

    ServiceTicket(String id, String customerName, String issue) {
        this.id = id;
        this.customerName = customerName;
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "id=" + id
                + ", customer=" + customerName
                + ", issue=" + issue;
    }
}

public class ServiceCenterWorkflow {

    private Map<String, ServiceTicket> ticketMap = new HashMap<>();
    private Deque<ServiceTicket> waitingQueue = new ArrayDeque<>();
    private Deque<ServiceTicket> completedStack = new ArrayDeque<>();
    private Set<String> ticketIds = new HashSet<>();

    public void createTicket(ServiceTicket ticket) {
        if (ticketIds.contains(ticket.id)) {
            System.out.println("重複 ticket id，無法新增：" + ticket.id);
            return;
        }

        ticketIds.add(ticket.id);
        ticketMap.put(ticket.id, ticket);
        waitingQueue.offerLast(ticket);

        System.out.println("建立成功：" + ticket);
    }

    public void processNext() {
        ServiceTicket ticket = waitingQueue.pollFirst();

        if (ticket == null) {
            System.out.println("目前沒有等待處理的 ticket");
            return;
        }

        completedStack.push(ticket);

        System.out.println("處理完成：" + ticket);
    }

    public void cancelWaiting(String id) {
        Iterator<ServiceTicket> iterator = waitingQueue.iterator();

        while (iterator.hasNext()) {
            ServiceTicket ticket = iterator.next();

            if (ticket.id.equals(id)) {
                iterator.remove();
                ticketMap.remove(id);
                ticketIds.remove(id);

                System.out.println("取消成功：" + ticket);
                return;
            }
        }

        System.out.println("找不到尚未處理的 ticket：" + id);
    }

    public void undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("沒有可以 undo 的完成紀錄");
            return;
        }

        ServiceTicket ticket = completedStack.pop();

        waitingQueue.offerFirst(ticket);

        System.out.println("Undo 完成：" + ticket);
    }

    public ServiceTicket findById(String id) {
        return ticketMap.get(id);
    }

    public void printSummary() {
        System.out.println("===== Summary =====");
        System.out.println("總 ticket 數：" + ticketMap.size());
        System.out.println("等待處理：" + waitingQueue.size());
        System.out.println("已完成：" + completedStack.size());

        System.out.println("Waiting Queue：");
        for (ServiceTicket ticket : waitingQueue) {
            System.out.println(ticket);
        }

        System.out.println("Completed Stack：");
        for (ServiceTicket ticket : completedStack) {
            System.out.println(ticket);
        }

        System.out.println();
    }

    public static void main(String[] args) {

        ServiceCenterWorkflow system =
                new ServiceCenterWorkflow();

        system.createTicket(
                new ServiceTicket("T001", "Amy", "帳號無法登入"));

        system.createTicket(
                new ServiceTicket("T002", "Bob", "密碼重設"));

        system.createTicket(
                new ServiceTicket("T003", "Cindy", "付款問題"));

        System.out.println();

        System.out.println("測試重複 id：");
        system.createTicket(
                new ServiceTicket("T001", "David", "網路問題"));

        System.out.println();

        system.printSummary();

        System.out.println("處理兩張 ticket：");
        system.processNext();
        system.processNext();

        System.out.println();

        system.printSummary();

        System.out.println("查詢 T002：");
        System.out.println(system.findById("T002"));

        System.out.println();

        System.out.println("取消尚未處理的 T003：");
        system.cancelWaiting("T003");

        System.out.println();

        System.out.println("取消不存在的 T999：");
        system.cancelWaiting("T999");

        System.out.println();

        system.printSummary();

        System.out.println("第一次 undo：");
        system.undoLastCompletion();

        System.out.println();

        system.printSummary();

        System.out.println("第二次 undo：");
        system.undoLastCompletion();

        System.out.println();

        system.printSummary();

        System.out.println("測試空 Queue：");
        system.processNext();
        system.processNext();

        System.out.println();

        System.out.println("再測試 undo：");
        system.undoLastCompletion();
        system.undoLastCompletion();
        system.undoLastCompletion();

        System.out.println();

        system.printSummary();
    }
}