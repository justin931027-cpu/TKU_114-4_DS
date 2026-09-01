import java.util.*;

public class EventSimulationQueue {

    // Event
    public static class Event {
        private int time;
        private String type;
        private int sequence;
        private boolean cancelled;

        public Event(int time, String type, int sequence) {
            this.time = time;
            this.type = type;
            this.sequence = sequence;
            this.cancelled = false;
        }

        public int getTime() {
            return time;
        }

        public String getType() {
            return type;
        }

        public int getSequence() {
            return sequence;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void cancel() {
            cancelled = true;
        }

        @Override
        public String toString() {
            return time + "|" + type + "|" + sequence;
        }
    }

    private ArrayList<Event> heap;

    public EventSimulationQueue() {
        heap = new ArrayList<>();
    }

    // 加入事件
    public void add(Event event) {

        heap.add(event);

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

    // 取消指定 sequence 的事件
    public boolean cancel(int sequence) {

        for (Event event : heap) {

            if (event.getSequence() == sequence
                    && !event.isCancelled()) {

                event.cancel();
                return true;
            }
        }

        return false;
    }

    // 查看下一個事件
    public Event peekNext() {

        removeCancelled();

        if (heap.isEmpty()) {
            throw new NoSuchElementException("No event");
        }

        return heap.get(0);
    }

    // 執行下一個事件
    public Event executeNext() {

        removeCancelled();

        if (heap.isEmpty()) {
            throw new NoSuchElementException("No event");
        }

        Event result = heap.get(0);

        Event last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {

            heap.set(0, last);
            heapifyDown();
        }

        return result;
    }

    // 移除已取消事件
    private void removeCancelled() {

        while (!heap.isEmpty() && heap.get(0).isCancelled()) {

            Event last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {

                heap.set(0, last);
                heapifyDown();
            }
        }
    }

    private boolean higherPriority(Event a, Event b) {

        if (a.time != b.time) {
            return a.time < b.time;
        }

        return a.sequence < b.sequence;
    }

    private void heapifyDown() {

        int current = 0;

        while (true) {

            int left = current * 2 + 1;
            int right = current * 2 + 2;

            if (left >= heap.size()) {
                break;
            }

            int best = left;

            if (right < heap.size()
                    && higherPriority(heap.get(right), heap.get(left))) {
                best = right;
            }

            if (!higherPriority(heap.get(best), heap.get(current))) {
                break;
            }

            swap(current, best);
            current = best;
        }
    }

    private void swap(int a, int b) {

        Event temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // 測試
    public static void main(String[] args) {

        EventSimulationQueue queue =
                new EventSimulationQueue();

        queue.add(new Event(10, "A", 1));
        queue.add(new Event(5, "B", 2));
        queue.add(new Event(5, "C", 3));
        queue.add(new Event(8, "D", 4));
        queue.add(new Event(10, "E", 5));

        // 取消 sequence = 3
        queue.cancel(3);

        System.out.println("執行紀錄：");

        try {

            while (true) {
                System.out.println(queue.executeNext());
            }

        } catch (NoSuchElementException e) {
            System.out.println("所有事件執行完畢");
        }
    }
}