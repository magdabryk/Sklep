package pl.camp.it.sklep.database;

import pl.camp.it.sklep.engine.Engine;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.Product;

import java.io.*;
import java.util.ArrayList;


public class ProductDB {

    private final ArrayList<Product> products = new ArrayList<>();

    public ProductDB() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(GUI.DB_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                if (params[0].equals("Product")) {
                    this.products.add(new Product(Integer.parseInt(params[1]), params[2], Double.parseDouble(params[3]), Integer.parseInt(params[4])));
                }
            }
        } catch (IOException e) {
            System.out.println("bład odczytu z pliku");
        }
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
        if (id <= this.products.size() && id > 0 && amount > 0) {
            for (Product currentProduct : this.products) {
                if (currentProduct.getId() == id) {
                    currentProduct.setAmount(currentProduct.getAmount() + amount);
                    System.out.println("Uzupełniono stan magazynowy");
                }
            }
        } else {
            System.out.println("bład podczas uzupełniania magazynu");
        }
    }

}
