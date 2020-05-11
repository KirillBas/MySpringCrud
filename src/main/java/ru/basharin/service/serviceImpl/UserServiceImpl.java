package ru.basharin.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basharin.dao.UserDao;
import ru.basharin.model.User;
import ru.basharin.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return userDao.getById(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public User getUserByNameAndEmail(String name, String email) {
        return userDao.getUserByNameEmail(name, email);
    }
}