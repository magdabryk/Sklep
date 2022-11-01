package pl.camp.it.sklep.engine;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.User;
import java.util.Scanner;


public class Engine {

    public static void start() {


        final ProductDB productDB = new ProductDB();
        final UserDB userDB = new UserDB();
        final Scanner scanner = new Scanner(System.in);
        boolean isWorking = Authenticator.authenticate(userDB);

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

                case "4":
                    isWorking = false;
                    break;

                case "3":
                    if (Authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
                        System.out.println("Wpisz ID produktu którego stan magazynowy chcesz uzupełnić: ");
                        int refillId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Wpisz ilość produktu w dostawie: ");
                        int refillAmount = Integer.parseInt(scanner.nextLine());
                        productDB.reffilProduct(refillId, refillAmount);
                        break;
                    }
                default:
                    System.out.println("Nie ma takej pozycji w menu. Wybierz jeszcze raz.");
                    break;
            }

        }
    }
}
