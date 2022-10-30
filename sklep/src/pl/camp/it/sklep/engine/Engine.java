package pl.camp.it.sklep.engine;

import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.gui.GUI;

import java.util.Scanner;

public class Engine {

public static void start() {

    ProductDB productDB = new ProductDB();
    Scanner scanner = new Scanner(System.in);
    boolean isWorking = true;

    while (isWorking) {
        GUI.showMenu();
        switch (scanner.nextLine()) {
            case "1":
                GUI.poductList(productDB.getProducts());
                break;
            case "2":
                System.out.println("kupowane produktu");
                break;
            case "3":
                System.out.println("uzupłniana magazynu");
                break;
            case "4":
                isWorking = false;
                break;
            default:
                System.out.println("Nie ma takej pozycji w menu. Wybierz jeszcze raz.");
                break;
        }

    }
}
}
