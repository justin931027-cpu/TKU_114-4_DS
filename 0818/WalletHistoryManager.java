class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;

    public WalletTransaction(int sequence, String type, int amount) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
    }

    public int getSequence() {
        return sequence;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "交易編號：" + sequence +
               "，類型：" + type +
               "，金額：" + amount;
    }
}

class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private WalletTransaction[] transactions;
    private int transactionCount;
    private int nextSequence;

    public DigitalWallet(String walletId, String owner, int balance, int historySize) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = Math.max(balance, 0);
        this.transactions = new WalletTransaction[historySize];
        this.transactionCount = 0;
        this.nextSequence = 1;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;

        transactions[transactionCount] =
                new WalletTransaction(nextSequence, "DEPOSIT", amount);

        transactionCount++;
        nextSequence++;

        return true;
    }

    public boolean withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        balance -= amount;

        transactions[transactionCount] =
                new WalletTransaction(nextSequence, "WITHDRAW", amount);

        transactionCount++;
        nextSequence++;

        return true;
    }

    public WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }

        return null;
    }

    public int totalByType(String type) {
        int total = 0;

        if (type == null) {
            return 0;
        }

        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase(type)) {
                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    public boolean transferTo(DigitalWallet target, int amount) {
        if (target == null) {
            return false;
        }

        if (target == this) {
            return false;
        }

        if (amount <= 0 || balance < amount) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        if (target.transactionCount >= target.transactions.length) {
            return false;
        }

        balance -= amount;
        target.balance += amount;

        transactions[transactionCount] =
                new WalletTransaction(nextSequence, "TRANSFER_OUT", amount);

        transactionCount++;
        nextSequence++;

        target.transactions[target.transactionCount] =
                new WalletTransaction(target.nextSequence, "TRANSFER_IN", amount);

        target.transactionCount++;
        target.nextSequence++;

        return true;
    }

    public void printStatement() {
        System.out.println("=== 電子錢包交易明細 ===");
        System.out.println("錢包編號：" + walletId);
        System.out.println("持有人：" + owner);
        System.out.println("目前餘額：" + balance);

        System.out.println("--- 交易紀錄 ---");

        if (transactionCount == 0) {
            System.out.println("目前沒有交易紀錄");
        } else {
            for (int i = 0; i < transactionCount; i++) {
                System.out.println(transactions[i]);
            }
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        DigitalWallet wallet1 =
                new DigitalWallet("W001", "小明", 1000, 10);

        DigitalWallet wallet2 =
                new DigitalWallet("W002", "小華", 500, 10);

        wallet1.deposit(500);
        wallet1.withdraw(200);

        System.out.println("=== 查詢交易 ===");
        System.out.println(wallet1.findTransaction(1));
        System.out.println(wallet1.findTransaction(99));

        System.out.println("\n=== 指定類型總金額 ===");
        System.out.println("DEPOSIT 總額：" +
                wallet1.totalByType("DEPOSIT"));

        System.out.println("WITHDRAW 總額：" +
                wallet1.totalByType("WITHDRAW"));

        System.out.println("\n=== 轉帳 ===");
        System.out.println("轉帳結果：" +
                wallet1.transferTo(wallet2, 300));

        System.out.println("\n=== Wallet 1 Statement ===");
        wallet1.printStatement();

        System.out.println("\n=== Wallet 2 Statement ===");
        wallet2.printStatement();

        System.out.println("\nTRANSFER_OUT 總額：" +
                wallet1.totalByType("TRANSFER_OUT"));

        System.out.println("TRANSFER_IN 總額：" +
                wallet2.totalByType("TRANSFER_IN"));
    }
}