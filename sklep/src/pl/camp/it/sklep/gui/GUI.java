package pl.camp.it.sklep.gui;

import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.model.Product;

public class GUI {


    public static void showMenu() {
        System.out.println("1. Lista produktów.");
        System.out.println("2. Kup podukt.");
        System.out.println("3. Uzupełnij magazyn.");
        System.out.println("4. Wyjscie.");
    }

    public static void poductList(Product[] products) {
        for(Product currentProduct : products) {
            System.out.println(currentProduct);
        }
    }
}
