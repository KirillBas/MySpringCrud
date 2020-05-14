package ru.basharin.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.basharin.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getUsers();
    User getUserById(long id);
    void save(User user);
    void delete(long id);
    void update(User user);
    User getUserByNameAndEmail(String name, String email);
    boolean authUser(String name, String password);
}
