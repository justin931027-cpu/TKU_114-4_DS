import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    private Deque<String> history = new ArrayDeque<>();

    public void visit(String page) {
        history.push(page);
        System.out.println("Visit: " + page);
    }

    public void back() {
        if (history.size() > 1) {
            history.pop();
            System.out.println("Back to: " + history.peek());
        } else if (history.size() == 1) {
            System.out.println("已經是最前面的頁面");
        } else {
            System.out.println("沒有瀏覽紀錄");
        }
    }

    public String current() {
        if (history.isEmpty()) {
            return null;
        }

        return history.peek();
    }

    public static void main(String[] args) {

        BrowserBackStack browser = new BrowserBackStack();

        System.out.println("目前頁面：" + browser.current());

        browser.back();

        browser.visit("Google");
        browser.visit("YouTube");
        browser.visit("GitHub");

        System.out.println("目前頁面：" + browser.current());

        browser.back();
        System.out.println("目前頁面：" + browser.current());

        browser.back();
        System.out.println("目前頁面：" + browser.current());

        browser.back();

        browser.visit("ChatGPT");

        System.out.println("目前頁面：" + browser.current());
    }
}