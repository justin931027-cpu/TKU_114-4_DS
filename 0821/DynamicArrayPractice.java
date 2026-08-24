class DynamicArray<T> {
    private Object[] data;
    private int size;

    public DynamicArray() {
        data = new Object[2];
        size = 0;
    }

    public void add(T value) {
        ensureCapacity();

        data[size] = value;
        size++;
    }

    public void add(int index, T value) {
        checkAddIndex(index);
        ensureCapacity();

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndex(index);

        T oldValue = (T) data[index];
        data[index] = value;

        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        T removed = (T) data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;

        return removed;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    private void ensureCapacity() {
        if (size == data.length) {
            Object[] newData = new Object[data.length * 2];

            for (int i = 0; i < data.length; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "index = " + index + ", size = " + size);
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "index = " + index + ", size = " + size);
        }
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {

        DynamicArray<String> strings = new DynamicArray<>();

        strings.add("Java");
        strings.add("Python");

        System.out.println("String 初始容量：" + strings.capacity());

        strings.add("C++");

        System.out.println("擴充後容量：" + strings.capacity());

        strings.add(1, "JavaScript");

        System.out.println("String 資料：");
        for (int i = 0; i < strings.size(); i++) {
            System.out.println(strings.get(i));
        }

        System.out.println("set 舊值：" + strings.set(0, "C#"));
        System.out.println("remove：" + strings.remove(1));
        System.out.println("size：" + strings.size());

        System.out.println();

        DynamicArray<Integer> numbers = new DynamicArray<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(1, 15);

        System.out.println("Integer 資料：");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        System.out.println("刪除中間元素：" + numbers.remove(1));

        System.out.println("刪除後：");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        try {
            numbers.get(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }

        try {
            numbers.get(numbers.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}