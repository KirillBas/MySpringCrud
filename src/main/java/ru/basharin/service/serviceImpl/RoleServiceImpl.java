package ru.basharin.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basharin.dao.RoleDao;
import ru.basharin.model.Role;
import ru.basharin.model.User;
import ru.basharin.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly = true)
    @Override
    public List<Role> getRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    @Override
    public Role getRoleById(long id) {
        return roleDao.getById(id);
    }

    @Transactional
    @Override
    public void save(Role role) {
        roleDao.add(role);
    }

    @Transactional
    @Override
    public void delete(long id) {
        roleDao.delete(id);
    }

    @Transactional
    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    @Override
    public List<Role> getRolesByUserId(User user) {
        return roleDao.getRolesByUserId(user);
    }
}
