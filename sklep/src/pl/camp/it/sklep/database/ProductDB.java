package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ProductDB {

    private final ArrayList<Product> products = new ArrayList<>();
    private final String PRODUCT_DB_FILE = "products.txt";

    public ProductDB() {
        products.add(new Product(1, "Sword", 250.00, 10));
        products.add(new Product(2, "Butterfly knifes", 150.50, 5));
        products.add(new Product(3, "Spear", 120.00, 50));
        products.add(new Product(4, "Dagger", 95.00, 9));
        products.add(new Product(5, "Bakwa Dan Do Sword", 500.00, 3));

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean buyProduct(int id, int amount) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getId() == id && (currentProduct.getAmount() - amount) >= 0) {
                currentProduct.setAmount(currentProduct.getAmount() - amount);
                return true;
            }
        }
        return false;
    }

    public void payProduct(int id, int amount) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getId() == id) {
                System.out.println("Do zapłaty " + currentProduct.getPrice() * amount);
            }
        }
    }

    public void reffilProduct(int id, int amount) {
        for (Product currentProduct : this.products) {
            if (currentProduct.getId() == id && amount > 0) {
                currentProduct.setAmount(currentProduct.getAmount() + amount);
                System.out.println("Uzupełniono stan magazynowy");
            }
        }
    }

    public void persistToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.PRODUCT_DB_FILE));
            writer.write("abc");
            //writer.write(this.products.get(0).convertToData());
            for(int i = 1; i < products.size(); i++){
                writer.newLine();
                writer.write(this.products.get(i).convertToData());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("bład zapisu do pliku");
            e.printStackTrace();
        }
    }




}
