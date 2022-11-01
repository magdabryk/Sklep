package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.User;

public class UserDB {
    private User[] users = new User[2];
    public UserDB() {
        this.users[0] = new User("user", "a5efb7f2d1727868be9b96957eb3bd16", User.Role.USER);
        this.users[1] = new User("admin", "6e5c6d75e86491554530ce16141b42ee", User.Role.ADMIN);
    }

    public User[] getUsers() {
        return users;
    }

    public User findUserByLogin(String login) {
        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    }
