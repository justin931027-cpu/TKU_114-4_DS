class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        this.id = (id == null || id.trim().isEmpty()) ? "Unknown" : id;
        this.name = (name == null || name.trim().isEmpty()) ? "Unknown" : name;
        this.availableCount = Math.max(availableCount, 0);
    }

    public boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }
        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    @Override
    public String toString() {
        return "設備編號：" + id +
               "，名稱：" + name +
               "，可借數量：" + availableCount;
    }
}

public class EquipmentInventory {
    public static void main(String[] args) {
        Equipment e1 = new Equipment("E001", "Laptop", 2);
        Equipment e2 = new Equipment("E002", "Projector", 0);

        System.out.println(e1);
        System.out.println(e2);

        System.out.println("Laptop 借用：" + e1.borrowOne());
        System.out.println("Laptop 借用：" + e1.borrowOne());
        System.out.println("Laptop 借用：" + e1.borrowOne());

        System.out.println("Projector 借用：" + e2.borrowOne());

        e2.returnItems(2);
        System.out.println("歸還 2 台 Projector");

        System.out.println("Projector 借用：" + e2.borrowOne());

        e2.returnItems(-5);

        System.out.println(e1);
        System.out.println(e2);
    }
}