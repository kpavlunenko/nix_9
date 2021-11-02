package ua.com.alevel.db;

import ua.com.alevel.entity.User;

import java.util.Arrays;
import java.util.UUID;

public final class UserDB {

    private User[] users = new User[2];
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
        users[countOfUsers] = user;
        countOfUsers++;
        if(countOfUsers == users.length) {
            users = Arrays.copyOf(users, countOfUsers + 10);
        }
    }

    public void update(User user) {
        users[findIndexUserInArray(user.getId())] = user;
    }

    public void delete(String id) {
        int idInArray = findIndexUserInArray(id);
        users[idInArray] = null;
        for (int i = idInArray; i < countOfUsers - 1; i++) {
            users[i] = users[i + 1];
        }
        users[countOfUsers - 1] = null;
        countOfUsers--;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId().equals(id)) {
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
            if (users[i].getId().equals(id)) {
                return users[i];
            }
        }
        throw new RuntimeException("user not found by id");
    }

    private int findIndexUserInArray(String id) {
        for (int i = 0; i < countOfUsers; i++) {
            if (users[i].getId().equals(id)) {
                return i;
            }
        }
        throw new RuntimeException("user not found by id");
    }
}
