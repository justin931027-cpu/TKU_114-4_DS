class DigitalWallet {
    private String walletId;
    private String owner;
    private double balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, double balance) {
        this.walletId = walletId;
        this.owner = owner;

        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }

        this.transactionCount = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            System.out.println("付款成功：" + amount);
        } else {
            System.out.println("付款失敗：金額必須大於 0");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("提款失敗：金額必須大於 0");
        } else if (amount > balance) {
            System.out.println("提款失敗：餘額不足");
        } else {
            balance -= amount;
            transactionCount++;
            System.out.println("提款成功：" + amount);
        }
    }

    public void refund(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            System.out.println("退款成功：" + amount);
        } else {
            System.out.println("退款失敗：金額必須大於 0");
        }
    }

    public void showInfo() {
        System.out.println("錢包編號：" + walletId);
        System.out.println("持有人：" + owner);
        System.out.println("餘額：" + balance);
        System.out.println("交易次數：" + transactionCount);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet =
                new DigitalWallet("W001", "小明", 1000);

        System.out.println("=== 初始狀態 ===");
        wallet.showInfo();

        System.out.println("\n=== 正常儲值 ===");
        wallet.deposit(500);
        wallet.showInfo();

        System.out.println("\n=== 正常付款 ===");
        wallet.withdraw(300);
        wallet.showInfo();

        System.out.println("\n=== 餘額不足 ===");
        wallet.withdraw(2000);
        wallet.showInfo();

        System.out.println("\n=== 負數金額 ===");
        wallet.deposit(-100);
        wallet.withdraw(-50);
        wallet.showInfo();

        System.out.println("\n=== 退款 ===");
        wallet.refund(200);
        wallet.showInfo();
    }
}