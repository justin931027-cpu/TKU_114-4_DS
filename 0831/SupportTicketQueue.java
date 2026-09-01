import java.util.*;

public class SupportTicketQueue {

    // Ticket 類別
    public static class Ticket {

        private String id;
        private int severity;
        private int createdOrder;

        public Ticket(String id, int severity, int createdOrder) {
            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        public String getId() {
            return id;
        }

        public int getSeverity() {
            return severity;
        }

        public int getCreatedOrder() {
            return createdOrder;
        }

        @Override
        public String toString() {
            return id + "|" + severity + "|" + createdOrder;
        }
    }

    // 自製 Max Heap
    private ArrayList<Ticket> heap;

    public SupportTicketQueue() {
        heap = new ArrayList<>();
    }

    // 加入 Ticket
    public void add(Ticket ticket) {

        heap.add(ticket);

        int current = heap.size() - 1;

        while (current > 0) {

            int parent = (current - 1) / 2;

            if (!higherPriority(heap.get(current), heap.get(parent))) {
                break;
            }

            swap(current, parent);

            current = parent;
        }
    }

    // 取出最高優先權 Ticket
    public Ticket remove() {

        if (heap.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        Ticket result = heap.get(0);

        Ticket last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);

            int current = 0;

            while (true) {

                int left = current * 2 + 1;
                int right = current * 2 + 2;

                if (left >= heap.size()) {
                    break;
                }

                int bestChild = left;

                if (right < heap.size()
                        && higherPriority(heap.get(right), heap.get(left))) {
                    bestChild = right;
                }

                if (!higherPriority(heap.get(bestChild), heap.get(current))) {
                    break;
                }

                swap(current, bestChild);

                current = bestChild;
            }
        }

        return result;
    }

    // 判斷 a 是否比 b 優先
    private boolean higherPriority(Ticket a, Ticket b) {

        // severity 越大越優先
        if (a.getSeverity() != b.getSeverity()) {
            return a.getSeverity() > b.getSeverity();
        }

        // severity 相同，createdOrder 越小越優先
        return a.getCreatedOrder() < b.getCreatedOrder();
    }

    private void swap(int a, int b) {

        Ticket temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // 測試
    public static void main(String[] args) {

        SupportTicketQueue queue = new SupportTicketQueue();

        queue.add(new Ticket("T001", 2, 1));
        queue.add(new Ticket("T002", 5, 2));
        queue.add(new Ticket("T003", 3, 3));
        queue.add(new Ticket("T004", 5, 4));
        queue.add(new Ticket("T005", 1, 5));

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}