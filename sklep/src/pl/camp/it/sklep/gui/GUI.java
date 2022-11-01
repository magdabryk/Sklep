package pl.camp.it.sklep.gui;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;

import java.util.Scanner;

public class GUI {

    public static User readLoginAndPassword() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Hasło");
        String password = scanner.nextLine();
        return new User(login, password);
    }

    public static void showMenu() {
        System.out.println("1. Lista produktów.");
        System.out.println("2. Kup podukt.");
        if(Authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
            System.out.println("3. Uzupełnij magazyn.");
        }
        System.out.println("4. Wyjscie.");
    }

    public static void poductList(Product[] products) {
        for (Product currentProduct : products) {
            System.out.println(currentProduct);
        }
    }
}
