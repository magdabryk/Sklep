package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

import java.io.*;
import java.util.ArrayList;


public class ProductDB {

    private final ArrayList<Product> products = new ArrayList<>();
    private final String PRODUCT_DB_FILE = "products.txt";

    public ProductDB() {

        try{
            BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_DB_FILE));
            String line;
            while((line = reader.readLine()) != null){
                String[] params = line.split(";");
                this.products.add(new Product(Integer.parseInt(params[0]), params[1], Double.parseDouble(params[2]), Integer.parseInt(params[3])));
            }
        }catch (IOException e){
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

            writer.write(this.products.get(0).convertToData());
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
