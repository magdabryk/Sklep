package pl.camp.it.sklep;

import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.gui.GUI;
import pl.camp.it.sklep.model.User;

public class Authenticator {

    public static User loggedUser;
    public static String seed = "6m'gWg1#2W5a?J<&YW+uBam^";

    public static boolean authenticate(UserDB userDB) {

        for( int i = 0; i < 3; i++) {
            User user = GUI.readLoginAndPassword();
            User userFromDB = userDB.findUserByLogin(user.getLogin());

            if (userFromDB != null && userFromDB.equals(user)) {
                Authenticator.loggedUser = userFromDB;
                return true;
            }
        }
        return false;
    }

}
