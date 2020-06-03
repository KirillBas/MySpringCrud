package ru.basharin.dao;

import ru.basharin.model.Role;
import ru.basharin.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getById(long id);
    void add(User user);
    void delete(long id);
    void update(User user);
    User getUserByNameEmail(String name, String email);
    User auth(String name, String password);
    User getByName(String name);
}
