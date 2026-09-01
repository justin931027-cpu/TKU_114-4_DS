import java.util.NoSuchElementException;

public class EmergencyTriageQueue {

    // 病人資料
    public static class Patient {
        private String medicalId;
        private int urgency;
        private int arrivalOrder;

        public Patient(String medicalId, int urgency, int arrivalOrder) {
            this.medicalId = medicalId;
            this.urgency = urgency;
            this.arrivalOrder = arrivalOrder;
        }

        public String getMedicalId() {
            return medicalId;
        }

        public int getUrgency() {
            return urgency;
        }

        public int getArrivalOrder() {
            return arrivalOrder;
        }

        @Override
        public String toString() {
            return medicalId + "|" + urgency + "|" + arrivalOrder;
        }
    }

    private Patient[] heap;
    private int size;

    public EmergencyTriageQueue() {
        heap = new Patient[10];
        size = 0;
    }

    // 病人報到
    public void checkIn(Patient patient) {

        if (size == heap.length) {
            resize();
        }

        heap[size] = patient;

        int current = size;
        size++;

        // 向上調整
        while (current > 0) {

            int parent = (current - 1) / 2;

            if (!higherPriority(heap[current], heap[parent])) {
                break;
            }

            swap(current, parent);
            current = parent;
        }
    }

    // 查看下一位
    public Patient peekNext() {

        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        return heap[0];
    }

    // 叫號
    public Patient callNext() {

        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        Patient result = heap[0];

        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) {
            heapifyDown();
        }

        return result;
    }

    // 查詢目前人數
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判斷 a 是否優先於 b
    private boolean higherPriority(Patient a, Patient b) {

        // 急迫程度越高越優先
        if (a.urgency != b.urgency) {
            return a.urgency > b.urgency;
        }

        // 到院越早越優先
        if (a.arrivalOrder != b.arrivalOrder) {
            return a.arrivalOrder < b.arrivalOrder;
        }

        // 病歷號較小者優先
        return a.medicalId.compareTo(b.medicalId) < 0;
    }

    private void heapifyDown() {

        int current = 0;

        while (true) {

            int left = current * 2 + 1;
            int right = current * 2 + 2;

            if (left >= size) {
                break;
            }

            int bestChild = left;

            if (right < size
                    && higherPriority(heap[right], heap[left])) {
                bestChild = right;
            }

            if (!higherPriority(heap[bestChild], heap[current])) {
                break;
            }

            swap(current, bestChild);
            current = bestChild;
        }
    }

    private void swap(int a, int b) {
        Patient temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    private void resize() {

        Patient[] newHeap = new Patient[heap.length * 2];

        for (int i = 0; i < heap.length; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    // 測試
    public static void main(String[] args) {

        EmergencyTriageQueue queue =
                new EmergencyTriageQueue();

        queue.checkIn(new Patient("A003", 3, 3));
        queue.checkIn(new Patient("A001", 5, 1));
        queue.checkIn(new Patient("A002", 5, 2));
        queue.checkIn(new Patient("A004", 2, 4));

        System.out.println("目前人數：" + queue.size());
        System.out.println("下一位：" + queue.peekNext());

        while (!queue.isEmpty()) {
            System.out.println("叫號：" + queue.callNext());
        }

        try {
            queue.callNext();
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty");
        }
    }
}