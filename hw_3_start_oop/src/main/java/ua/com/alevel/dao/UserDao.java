package ua.com.alevel.dao;

import ua.com.alevel.db.UserDB;
import ua.com.alevel.entity.User;

public class UserDao {

    public void create(User user) {
        UserDB.getInstance().create(user);
    }

    public void update(User user) {
        UserDB.getInstance().update(user);
    }

    public void delete(String id) {
        UserDB.getInstance().delete(id);
    }

    public User findUserById(String id) {
        return UserDB.getInstance().findUserById(id);
    }

    public User[] findAll() {
        return UserDB.getInstance().findAll();
    }
}
