package pl.camp.it.sklep.database;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    private final Map<String, User> users = new HashMap<>();


    public UserDB() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(GUI.DB_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                if (params[0].equals("User")) {
                    this.users.put(params[1], new User(params[1], params[2], User.Role.valueOf(params[3])));
                }
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




