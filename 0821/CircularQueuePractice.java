class CircularQueue<T> {
    private Object[] data;
    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {
        data = new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public boolean enqueue(T value) {
        if (size == data.length) {
            System.out.println("Queue 已滿，無法加入 " + value);
            return false;
        }

        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;

        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            return null;
        }

        T value = (T) data[front];
        data[front] = null;

        front = (front + 1) % data.length;
        size--;

        return value;
    }

    public int size() {
        return size;
    }

    public void printState() {
        System.out.print("array = [");

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);

            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
        System.out.println("front = " + front);
        System.out.println("rear = " + rear);
        System.out.println("size = " + size);
        System.out.println();
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {

        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("enqueue A");
        queue.enqueue("A");
        queue.printState();

        System.out.println("enqueue B");
        queue.enqueue("B");
        queue.printState();

        System.out.println("enqueue C");
        queue.enqueue("C");
        queue.printState();

        System.out.println("dequeue = " + queue.dequeue());
        queue.printState();

        System.out.println("dequeue = " + queue.dequeue());
        queue.printState();

        System.out.println("enqueue D");
        queue.enqueue("D");
        queue.printState();

        System.out.println("enqueue E");
        queue.enqueue("E");
        queue.printState();

        System.out.println("enqueue F");
        queue.enqueue("F");
        queue.printState();

        System.out.println("dequeue = " + queue.dequeue());
        queue.printState();

        System.out.println("enqueue G");
        queue.enqueue("G");
        queue.printState();

        System.out.println("FIFO 取出所有元素：");

        while (queue.size() > 0) {
            System.out.println(queue.dequeue());
            queue.printState();
        }
    }
}