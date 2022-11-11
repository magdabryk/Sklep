package pl.camp.it.sklep.engine;

import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.User;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Engine {

    public static void start() throws IOException {

        final ProductDB productDB = new ProductDB();
        final UserDB userDB = new UserDB();

        GUI.loginOrRegister();
        switch (GUI.reader.readLine()) {
            case "1":
                loginUser(userDB, productDB);
                break;
            case "2":
                boolean ifAgain = true;
                String newLogin;

                while (ifAgain) {
                    System.out.println("Podaj login:");
                    newLogin = GUI.reader.readLine();
                    if (ifAgain = userDB.isLogin(newLogin)) {
                        continue;
                    }
                    System.out.println("Podaj hasło:");
                    String newPassword = GUI.reader.readLine();
                    userDB.addUser(newLogin, newPassword);
                    System.out.println("Użytkownik dodany prawidłowo.");
                    loginUser(userDB, productDB);
                }
                break;
            default:
                System.out.println("Nie ma takej pozycji w menu. Wybierz jeszcze raz.");
        }
    }

    public static void loginUser(UserDB userDB, ProductDB productDB) throws IOException {
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

                case "5":
                    isWorking = false;
                    GUI.reader.close();

                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(GUI.DB_FILE));
                        writer.write(productDB.getProducts().get(0).convertToData());
                        for (int i = 1; i < productDB.getProducts().size(); i++) {
                            writer.newLine();
                            writer.write(productDB.getProducts().get(i).convertToData());
                        }
                        for (User user : userDB.getUsers()) {
                            writer.newLine();
                            writer.append(user.convertToData());
                        }

                        writer.close();
                    } catch (IOException e) {
                        System.out.println("bład zapisu do pliku");
                        e.printStackTrace();
                    }
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
                case "4":
                    if (Authenticator.loggedUser.getRole().equals(User.Role.ADMIN)) {
                        System.out.println("Lista loginow ");
                        userDB.listUser();
                        System.out.println("Wpisz nazwę użytkownika którego uprawnienia chcesz zmienić");
                        String currentLogin = GUI.reader.readLine();
                        User.Role rola = userDB.whichRole(currentLogin);
                        if (rola.equals(User.Role.ADMIN)) {
                            System.out.println("Użytkownik ma status ADMIN czy chcesz zmienic uprawnienia na USER (t/n)");
                            switch (GUI.reader.readLine()) {
                                case "t":
                                    userDB.changeRole(currentLogin);
                                    break;
                                case "n":
                                    break;
                                default:
                                    System.out.println("błedny wybór");
                                    break;
                            }
                        } else if (rola.equals(User.Role.USER)) {
                            System.out.println("Użytkownik ma status USER czy chcesz zmienic uprawnienia na ADMIN (t/n)");
                            switch (GUI.reader.readLine()) {
                                case "t":
                                    userDB.changeRole(currentLogin);
                                    break;
                                case "n":
                                    break;
                                default:
                                    System.out.println("błedny wybór");
                                    break;
                            }
                        }
                        break;
                    }
                default:
                    System.out.println("Nie ma takej pozycji w menu. Wybierz jeszcze raz.");
                    break;
            }
        }
    }
}


