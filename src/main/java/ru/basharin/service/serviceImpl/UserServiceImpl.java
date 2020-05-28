package ru.basharin.service.serviceImpl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basharin.dao.UserDao;
import ru.basharin.model.User;
import ru.basharin.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCrypt;

    private final UserDao userDao;

    public UserServiceImpl(BCryptPasswordEncoder bCrypt, UserDao userDao) {
        this.bCrypt = bCrypt;
        this.userDao = userDao;
    }

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
        user.setPassword(bCrypt.encode(user.getPassword()));
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

    @Transactional
    @Override
    public boolean authUser(String name, String password) {
        return userDao.auth(name, password);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.getByName(s);
    }
}
