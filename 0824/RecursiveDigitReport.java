public class RecursiveDigitReport {

    public static int digitSum(int n) {
        n = Math.abs(n);

        if (n < 10) {
            return n;
        }

        return n % 10 + digitSum(n / 10);
    }

    public static int digitCount(int n) {
        n = Math.abs(n);

        if (n < 10) {
            return 1;
        }

        return 1 + digitCount(n / 10);
    }

    public static int countDigit(int n, int digit) {
        n = Math.abs(n);

        if (n < 10) {
            return n == digit ? 1 : 0;
        }

        int count = (n % 10 == digit) ? 1 : 0;

        return count + countDigit(n / 10, digit);
    }

    public static void main(String[] args) {

        System.out.println("50205：");
        System.out.println("digitSum = " + digitSum(50205));
        System.out.println("digitCount = " + digitCount(50205));
        System.out.println("countDigit(0) = " + countDigit(50205, 0));

        System.out.println();

        System.out.println("0：");
        System.out.println("digitSum = " + digitSum(0));
        System.out.println("digitCount = " + digitCount(0));
        System.out.println("countDigit(0) = " + countDigit(0, 0));

        System.out.println();

        System.out.println("-731：");
        System.out.println("digitSum = " + digitSum(-731));
        System.out.println("digitCount = " + digitCount(-731));
        System.out.println("countDigit(7) = " + countDigit(-731, 7));
    }
}
