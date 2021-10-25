package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.UUID;

public final class UserDB {

    private  User [] users = new User[10];
    private static UserDB instance;
    private static int countOfUsers = 0;

    private UserDB() {}

    public static UserDB getInstance() {
        if (instance == null){
            instance = new UserDB();
        }
        return instance;
    }

    public void create(User user) {
        user.setId(generateId());
        users[0] = user;
    }

    public void update(User user) {
        User currentUser = findUserById(user.getId());
        currentUser = user;
    }

    public void delete(String id) {
        users[findIndexUserInArray(id)] = null;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId() == id) {
                return generateId();
            }
        }
        return id;
    }

    public User [] findAll() {
        return users;
    }

    public User findUserById(String id) {
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new RuntimeException("user not found by id");
    }

    private int findIndexUserInArray(String id) {
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId() == id) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }
}
