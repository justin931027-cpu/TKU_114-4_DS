interface DeliveryMethod {
    double calculateFee(double distance);
    String getDescription();
}

class HomeDelivery implements DeliveryMethod {
    @Override
    public double calculateFee(double distance) {
        return 60 + distance * 5;
    }

    @Override
    public String getDescription() {
        return "宅配到府";
    }
}

class ConvenienceStoreDelivery implements DeliveryMethod {
    @Override
    public double calculateFee(double distance) {
        return 60;
    }

    @Override
    public String getDescription() {
        return "超商取貨";
    }
}

class SelfPickup implements DeliveryMethod {
    @Override
    public double calculateFee(double distance) {
        return 0;
    }

    @Override
    public String getDescription() {
        return "門市自取";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void showDeliveryInfo(double distance) {
        System.out.println("配送方式：" + deliveryMethod.getDescription());
        System.out.println("運費：" + deliveryMethod.calculateFee(distance) + " 元");
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {
        double distance = 10;

        OrderService homeDelivery =
                new OrderService(new HomeDelivery());

        OrderService storeDelivery =
                new OrderService(new ConvenienceStoreDelivery());

        OrderService selfPickup =
                new OrderService(new SelfPickup());

        homeDelivery.showDeliveryInfo(distance);
        System.out.println();

        storeDelivery.showDeliveryInfo(distance);
        System.out.println();

        selfPickup.showDeliveryInfo(distance);
    }
}