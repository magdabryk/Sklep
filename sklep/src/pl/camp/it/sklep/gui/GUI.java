package pl.camp.it.sklep.gui;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class GUI {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static User readLoginAndPassword() {
        try {
            System.out.println("Zaloguj się");
            System.out.println("Login:");
            String login = reader.readLine();
            System.out.println("Hasło");
            String password = reader.readLine();
            return new User(login, password);
        } catch (IOException e) {
            System.out.println("problem z wczytywaniem danych z terminala");
        }
        return null;
    }

    public static void showMenu() {
        System.out.println("1. Lista produktów.");
        System.out.println("2. Kup podukt.");
        if(Authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
            System.out.println("3. Uzupełnij magazyn.");
        }
        System.out.println("4. Wyjscie.");
    }

    public static void poductList(ArrayList<Product> products) {
        for (Product currentProduct : products) {
            System.out.println(currentProduct);
        }
    }

    public static void loginOrRegister() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
    }
}
