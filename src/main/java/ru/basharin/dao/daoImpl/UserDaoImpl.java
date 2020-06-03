package ru.basharin.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.basharin.dao.UserDao;
import ru.basharin.model.Role;
import ru.basharin.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getById(long id) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where id=:id").setParameter("id", id).uniqueResult();
    }

    @Override
    public void add(User user) {
        Role role = new Role();
        role.setRole("USER");
        user.addRole(role);
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        if (user != null) {
            session.delete(user);
        }
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public User getUserByNameEmail(String name, String email) {
        List<User> userList = (List<User>) sessionFactory.getCurrentSession().createQuery("from User");
        for (User res : userList) {
            if (res.getName().equals(name) && res.getEmail().equals(email)) {
                return res;
            }
        }
        return null;
    }

    @Override
    public User auth(String name, String password) {
        List<User> userList = (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
        for (User authUser : userList) {
            if (authUser.getName().equals(name) && authUser.getPassword().equals(password)) {
                return authUser;
            }
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where name=: name").setParameter("name", name).uniqueResult();
    }
}
