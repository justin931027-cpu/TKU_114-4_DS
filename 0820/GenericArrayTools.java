public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }

        int count = 0;

        for (T item : data) {
            if (target == null) {
                if (item == null) {
                    count++;
                }
            } else {
                if (target.equals(item)) {
                    count++;
                }
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            return;
        }

        if (first < 0 || first >= data.length ||
            second < 0 || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {

        String[] names = {"Amy", "Bob", "Amy", null};

        System.out.println(countMatches(names, "Amy"));
        System.out.println(countMatches(names, null));
        System.out.println(last(names));

        swap(names, 0, 1);

        for (String name : names) {
            System.out.println(name);
        }

        Integer[] numbers = {10, 20, 30, 20};

        System.out.println(countMatches(numbers, 20));
        System.out.println(last(numbers));

        swap(numbers, 0, 3);

        for (Integer number : numbers) {
            System.out.println(number);
        }
    }
}