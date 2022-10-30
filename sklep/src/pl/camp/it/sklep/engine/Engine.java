package pl.camp.it.sklep.engine;

import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.Product;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

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
                    System.out.println("Wpisz ID produktu: ");
                    int buyId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Wpisz ilość produktu któy chcesz kupić: ");
                    int buyAmount = Integer.parseInt(scanner.nextLine());
                    if (productDB.buyProduct(buyId, buyAmount)) {
                        productDB.payProduct(buyId, buyAmount);
                    } else {
                        System.out.println("Nie udało się kupić rządanego produktu wprowadź poprawne ID i ilość sztuk");
                    }


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
