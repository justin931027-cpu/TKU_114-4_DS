public class GenericResultDemo {

    static class Result<T> {
        boolean success;
        String message;
        T data;

        Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        void printResult() {
            System.out.println("success: " + success);
            System.out.println("message: " + message);
            System.out.println("data: " + data);
        }
    }

    public static void main(String[] args) {
        Result<String> result1 =
                new Result<>(true, "取得姓名成功", "王小明");

        Result<Integer> result2 =
                new Result<>(true, "取得分數成功", 90);

        Result<String> result3 =
                new Result<>(false, "取得資料失敗", null);

        result1.printResult();
        System.out.println();

        result2.printResult();
        System.out.println();

        result3.printResult();
    }
}