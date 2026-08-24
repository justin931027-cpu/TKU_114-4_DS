class ArrayStack<T> {
    private T[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
        top = 0;
    }

    public void push(T value) {
        if (isFull()) {
            System.out.println("Stack 已滿");
            return;
        }

        data[top] = value;
        top++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }

        top--;
        T value = data[top];
        data[top] = null;

        return value;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return data[top - 1];
    }

    public int size() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public boolean isFull() {
        return top == data.length;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {

        ArrayStack<String> stringStack = new ArrayStack<>(3);

        stringStack.push("Java");
        stringStack.push("Python");
        stringStack.push("C++");

        System.out.println("String Stack");
        System.out.println("peek: " + stringStack.peek());
        System.out.println("size: " + stringStack.size());
        System.out.println("isEmpty: " + stringStack.isEmpty());
        System.out.println("isFull: " + stringStack.isFull());

        stringStack.push("JavaScript");

        System.out.println("pop: " + stringStack.pop());
        System.out.println("pop: " + stringStack.pop());
        System.out.println("size: " + stringStack.size());

        System.out.println();

        ArrayStack<Integer> integerStack = new ArrayStack<>(4);

        integerStack.push(10);
        integerStack.push(20);
        integerStack.push(30);

        System.out.println("Integer Stack");
        System.out.println("peek: " + integerStack.peek());
        System.out.println("size: " + integerStack.size());
        System.out.println("isEmpty: " + integerStack.isEmpty());
        System.out.println("isFull: " + integerStack.isFull());

        System.out.println("pop: " + integerStack.pop());
        System.out.println("peek: " + integerStack.peek());

        integerStack.pop();
        integerStack.pop();

        System.out.println("pop 空 Stack: " + integerStack.pop());
        System.out.println("isEmpty: " + integerStack.isEmpty());
    }
}