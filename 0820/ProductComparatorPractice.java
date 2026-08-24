import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    int id;
    String name;
    double price;
    int stock;

    StoreProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int compareTo(StoreProduct other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", stock=" + stock;
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {

        List<StoreProduct> products = new ArrayList<>();

        products.add(new StoreProduct(3, "Mouse", 500, 20));
        products.add(new StoreProduct(1, "Keyboard", 1200, 10));
        products.add(new StoreProduct(5, "Monitor", 5000, 5));
        products.add(new StoreProduct(2, "Mouse", 800, 20));
        products.add(new StoreProduct(4, "Headset", 1200, 15));

        System.out.println("原始順序：");
        printProducts(products);

        List<StoreProduct> copy1 = new ArrayList<>(products);
        copy1.sort(null);

        System.out.println("\nNatural order：id 升冪");
        printProducts(copy1);

        Comparator<StoreProduct> byPriceThenName =
                Comparator.comparingDouble((StoreProduct p) -> p.price)
                          .thenComparing(p -> p.name);

        List<StoreProduct> copy2 = new ArrayList<>(products);
        copy2.sort(byPriceThenName);

        System.out.println("\nComparator 一：price 升冪，同價依 name");
        printProducts(copy2);

        Comparator<StoreProduct> byStockDescThenId =
                Comparator.comparingInt((StoreProduct p) -> p.stock)
                          .reversed()
                          .thenComparingInt(p -> p.id);

        List<StoreProduct> copy3 = new ArrayList<>(products);
        copy3.sort(byStockDescThenId);

        System.out.println("\nComparator 二：stock 降冪，同庫存依 id");
        printProducts(copy3);

        System.out.println("\n再次確認原始順序：");
        printProducts(products);
    }

    static void printProducts(List<StoreProduct> products) {
        for (StoreProduct product : products) {
            System.out.println(product);
        }
    }
}