public class RecursiveArrayStatistics {

    public static int maximum(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array cannot be null or empty");
        }

        return maximumHelper(data, 0);
    }

    private static int maximumHelper(int[] data, int index) {
        if (index == data.length - 1) {
            return data[index];
        }

        int maxRest = maximumHelper(data, index + 1);

        return Math.max(data[index], maxRest);
    }

    public static int minimum(int[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array cannot be null or empty");
        }

        return minimumHelper(data, 0);
    }

    private static int minimumHelper(int[] data, int index) {
        if (index == data.length - 1) {
            return data[index];
        }

        int minRest = minimumHelper(data, index + 1);

        return Math.min(data[index], minRest);
    }

    public static int countAbove(int[] data, int value) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array cannot be null or empty");
        }

        return countAboveHelper(data, 0, value);
    }

    private static int countAboveHelper(int[] data, int index, int value) {
        if (index == data.length) {
            return 0;
        }

        int count = data[index] > value ? 1 : 0;

        return count + countAboveHelper(data, index + 1, value);
    }

    public static void main(String[] args) {

        int[] numbers = {12, 5, 30, 8, 25, 3};

        System.out.println("maximum = " + maximum(numbers));
        System.out.println("minimum = " + minimum(numbers));
        System.out.println("countAbove 10 = " + countAbove(numbers, 10));

        System.out.println();

        try {
            maximum(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            minimum(new int[0]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}