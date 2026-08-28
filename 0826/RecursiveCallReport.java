public class RecursiveCallReport {

    static int recursiveSum(int[] data, int index) {
        if (index >= data.length) {
            System.out.println(
                "index=" + index +
                ", current value=N/A" +
                ", recursive result=N/A" +
                ", return value=0"
            );
            return 0;
        }

        int currentValue = data[index];
        int recursiveResult = recursiveSum(data, index + 1);
        int returnValue = currentValue + recursiveResult;

        System.out.println(
            "index=" + index +
            ", current value=" + currentValue +
            ", recursive result=" + recursiveResult +
            ", return value=" + returnValue
        );

        return returnValue;
    }

    static void test(String title, int[] data) {
        System.out.println("=== " + title + " ===");

        int result = recursiveSum(data, 0);

        System.out.println("Final sum = " + result);
        System.out.println();
    }

    public static void main(String[] args) {
        int[] normal = {10, 20, 30, 40};
        int[] single = {99};
        int[] empty = {};

        test("Normal Array", normal);
        test("Single Element", single);
        test("Empty Array", empty);
    }
}