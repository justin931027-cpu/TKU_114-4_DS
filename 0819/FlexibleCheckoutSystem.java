interface PricingPolicy {
    double calculatePrice(double originalPrice);
}

class OriginalPricePolicy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice;
    }
}

class VipDiscountPolicy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.8;
    }
}

class Spend2000Discount300Policy implements PricingPolicy {
    @Override
    public double calculatePrice(double originalPrice) {
        if (originalPrice >= 2000) {
            return originalPrice - 300;
        }
        return originalPrice;
    }
}

interface NotificationChannel {
    boolean send(String orderId, double finalPrice);
}

class EmailNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, double finalPrice) {
        System.out.println("Email 通知：訂單 " + orderId
                + "，應付金額 " + finalPrice + " 元");
        return true;
    }
}

class SmsNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, double finalPrice) {
        System.out.println("SMS 通知：訂單 " + orderId
                + "，應付金額 " + finalPrice + " 元");
        return true;
    }
}

class ConsoleNotification implements NotificationChannel {
    @Override
    public boolean send(String orderId, double finalPrice) {
        System.out.println("Console 通知：訂單 " + orderId
                + "，應付金額 " + finalPrice + " 元");
        return true;
    }
}

class CheckoutResult {
    private String orderId;
    private double originalPrice;
    private double finalPrice;
    private boolean notificationStatus;

    public CheckoutResult(String orderId, double originalPrice,
                          double finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    public void showResult() {
        System.out.println("訂單編號：" + orderId);
        System.out.println("原價：" + originalPrice);
        System.out.println("結帳價：" + finalPrice);
        System.out.println("通知狀態：" + notificationStatus);
    }
}

class CheckoutService {
    private PricingPolicy pricingPolicy;
    private NotificationChannel notificationChannel;

    public CheckoutService(PricingPolicy pricingPolicy,
                           NotificationChannel notificationChannel) {
        this.pricingPolicy = pricingPolicy;
        this.notificationChannel = notificationChannel;
    }

    public CheckoutResult checkout(String orderId, double originalPrice) {
        double finalPrice = pricingPolicy.calculatePrice(originalPrice);

        boolean status =
                notificationChannel.send(orderId, finalPrice);

        return new CheckoutResult(
                orderId,
                originalPrice,
                finalPrice,
                status
        );
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {
        CheckoutService service1 = new CheckoutService(
                new OriginalPricePolicy(),
                new EmailNotification()
        );

        CheckoutService service2 = new CheckoutService(
                new VipDiscountPolicy(),
                new SmsNotification()
        );

        CheckoutService service3 = new CheckoutService(
                new Spend2000Discount300Policy(),
                new ConsoleNotification()
        );

        CheckoutResult result1 =
                service1.checkout("A001", 1500);

        CheckoutResult result2 =
                service2.checkout("A002", 1500);

        CheckoutResult result3 =
                service3.checkout("A003", 2500);

        System.out.println();
        result1.showResult();

        System.out.println();
        result2.showResult();

        System.out.println();
        result3.showResult();
    }
}