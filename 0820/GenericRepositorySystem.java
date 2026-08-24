import java.util.ArrayList;

class Repository<T> {
    private ArrayList<T> data = new ArrayList<>();

    public void add(T item) {
        data.add(item);
    }

    public T get(int index) {
        return data.get(index);
    }

    public T remove(int index) {
        return data.remove(index);
    }

    public int size() {
        return data.size();
    }

    public void printAll() {
        for (T item : data) {
            System.out.println(item);
        }
    }
}

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name=" + name +
                ", price=" + price;
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {

        Repository<String> stringRepository = new Repository<>();

        stringRepository.add("Java");
        stringRepository.add("Python");
        stringRepository.add("C++");

        System.out.println("Repository<String>：");
        stringRepository.printAll();

        System.out.println("get(1): " + stringRepository.get(1));
        System.out.println("size: " + stringRepository.size());

        stringRepository.remove(0);

        System.out.println("remove 後：");
        stringRepository.printAll();
        System.out.println("size: " + stringRepository.size());

        System.out.println();

        Repository<Product> productRepository = new Repository<>();

        productRepository.add(new Product(1, "Keyboard", 1200));
        productRepository.add(new Product(2, "Mouse", 500));
        productRepository.add(new Product(3, "Monitor", 5000));

        System.out.println("Repository<Product>：");
        productRepository.printAll();

        System.out.println("get(1): " + productRepository.get(1));
        System.out.println("size: " + productRepository.size());

        productRepository.remove(1);

        System.out.println("remove 後：");
        productRepository.printAll();
        System.out.println("size: " + productRepository.size());
    }
}