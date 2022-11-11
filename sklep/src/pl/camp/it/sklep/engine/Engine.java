package pl.camp.it.sklep.engine;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.User;
import java.io.IOException;



public class Engine {

    public static void start() throws IOException{


        final ProductDB productDB = new ProductDB();
        final UserDB userDB = new UserDB();

        boolean isWorking = Authenticator.authenticate(userDB);

        while (isWorking) {
            GUI.showMenu();
            switch (GUI.reader.readLine()) {
                case "1":
                    GUI.poductList(productDB.getProducts());
                    break;
                case "2":
                    System.out.println("Wpisz ID produktu: ");
                    int buyId = Integer.parseInt(GUI.reader.readLine());
                    System.out.println("Wpisz ilość produktu któy chcesz kupić: ");
                    int buyAmount = Integer.parseInt(GUI.reader.readLine());
                    if (productDB.buyProduct(buyId, buyAmount)) {
                        productDB.payProduct(buyId, buyAmount);
                    } else {
                        System.out.println("Nie udało się kupić rządanego produktu wprowadź poprawne ID i ilość sztuk");
                    }
                    break;

                case "4":
                    isWorking = false;
                    GUI.reader.close();
                    productDB.persistToFile();
                    break;

                case "3":
                    if (Authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
                        System.out.println("Wpisz ID produktu którego stan magazynowy chcesz uzupełnić: ");
                        int refillId = Integer.parseInt(GUI.reader.readLine());
                        System.out.println("Wpisz ilość produktu w dostawie: ");
                        int refillAmount = Integer.parseInt(GUI.reader.readLine());
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
