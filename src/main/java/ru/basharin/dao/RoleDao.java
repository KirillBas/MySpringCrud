package ru.basharin.dao;

import ru.basharin.model.Role;
import ru.basharin.model.User;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    Role getById(long id);
    void add(Role role);
    void delete(long id);
    void update(Role role);
    List<Role> getRolesByUserId(User user);
}
