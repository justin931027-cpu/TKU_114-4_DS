import java.util.Arrays;

final class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;

        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = Arrays.copyOf(quantities, quantities.length);
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return Arrays.copyOf(quantities, quantities.length);
    }

    public int totalQuantity() {
        int total = 0;

        for (int quantity : quantities) {
            total += quantity;
        }

        return total;
    }

    public int outOfStockCount() {
        int count = 0;

        for (int quantity : quantities) {
            if (quantity == 0) {
                count++;
            }
        }

        return count;
    }
}

public class InventorySnapshotPractice {
    public static void main(String[] args) {
        int[] data = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", data);

        System.out.println("倉庫編號：" + snapshot.getWarehouseId());
        System.out.println("庫存數量：" + Arrays.toString(snapshot.getQuantities()));
        System.out.println("總數量：" + snapshot.totalQuantity());
        System.out.println("缺貨品項數：" + snapshot.outOfStockCount());

        data[0] = 100;

        int[] copy = snapshot.getQuantities();
        copy[1] = 50;

        System.out.println("修改外部陣列後：");
        System.out.println(Arrays.toString(snapshot.getQuantities()));

        InventorySnapshot emptySnapshot =
                new InventorySnapshot("W002", null);

        System.out.println("null 陣列長度：" +
                emptySnapshot.getQuantities().length);
    }
}