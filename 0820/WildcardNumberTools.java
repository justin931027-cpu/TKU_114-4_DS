import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    static double average(List<? extends Number> values) {
        if (values.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Number value : values) {
            sum += value.doubleValue();
        }

        return sum / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values.isEmpty()) {
            return Double.NaN;
        }

        double max = values.get(0).doubleValue();

        for (Number value : values) {
            if (value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }

        return max;
    }

    static void addRange(List<? super Integer> target, int start, int end) {
        if (start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.5);
        doubles.add(2.5);
        doubles.add(3.5);

        System.out.println("Integer average: " + average(integers));
        System.out.println("Integer maximum: " + maximum(integers));

        System.out.println("Double average: " + average(doubles));
        System.out.println("Double maximum: " + maximum(doubles));

        List<Integer> empty = new ArrayList<>();

        System.out.println("Empty average: " + average(empty));
        System.out.println("Empty maximum: " + maximum(empty));

        List<Number> numbers = new ArrayList<>();
        addRange(numbers, 1, 5);
        System.out.println("addRange: " + numbers);

        addRange(numbers, 10, 5);
        System.out.println("start > end: " + numbers);
    }
}