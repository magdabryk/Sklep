package pl.camp.it.sklep.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    private final Map<String, User> users = new HashMap<>();
    private final String USER_DB_FILE = "users.txt";

    public UserDB() {
        /*this.users.put("user", new User("user", "a5efb7f2d1727868be9b96957eb3bd16", User.Role.USER));
        this.users.put("admin", new User("admin", "6e5c6d75e86491554530ce16141b42ee", User.Role.ADMIN));*/
        try {
            BufferedReader reader = new BufferedReader(new FileReader(USER_DB_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                this.users.put(params[0], new User(params[0], params[1], User.Role.valueOf(params[2])));
            }
        } catch (IOException e) {
            System.out.println("bład odczytu z pliku");
        }
    }


    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }


    public boolean isLogin(String newLogin) {
        return users.containsKey(newLogin);
    }

    public User findUserByLogin(String login) {
        return this.users.get(login);
    }


    public void presistToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.USER_DB_FILE));

            boolean flag = false;
            for (User user : this.users.values()) {
                if (flag) {
                    writer.newLine();
                }
                flag = true;
                writer.write(user.convertToData());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("bład podczas zapisu");
            e.printStackTrace();
        }
    }

    public void addUser(String newLogin, String newPassword) {
        this.users.put(newLogin, new User(newLogin, DigestUtils.md5Hex(newPassword + Authenticator.seed), User.Role.USER));
    }

    public void listUser() {
        System.out.println(users.keySet());
    }

    public User.Role whichRole(String login) {
        for (User currentUser : this.users.values()) {
            if (currentUser.getLogin().equals(login) && login != null) {
                return currentUser.getRole();
            }
        }
        return null;
    }

    public void changeRole(String login) {
        for (User currentUser : this.users.values()) {
            if (currentUser.getLogin().equals(login) && currentUser.getRole().equals(User.Role.ADMIN)) {
                currentUser.setRole(User.Role.USER);
                System.out.println("Rola użytkownika o loginie " + currentUser.getLogin() + " został zmieniona na USER");
            } else if (currentUser.getLogin().equals(login) && currentUser.getRole().equals(User.Role.USER)) {
                currentUser.setRole(User.Role.ADMIN);
                System.out.println("Rola użytkownika o loginie " + currentUser.getLogin() + " został zmieniona na ADMIN");
            }

        }
    }
}




