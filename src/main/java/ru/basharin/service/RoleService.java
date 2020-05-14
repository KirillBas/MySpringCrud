package ru.basharin.service;

import ru.basharin.model.Role;
import ru.basharin.model.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role getRoleById(long id);

    void save(Role role);

    void delete(long id);

    void update(Role role);

    List<Role> getRolesByUserId(User user);
}
