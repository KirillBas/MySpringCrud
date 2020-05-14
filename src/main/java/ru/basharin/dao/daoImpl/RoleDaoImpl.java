package ru.basharin.dao.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.basharin.dao.RoleDao;
import ru.basharin.model.Role;
import ru.basharin.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = sessionFactory.getCurrentSession().createQuery("from Role");
        return query.getResultList();
    }

    @Override
    public Role getById(long id) {
        return (Role) sessionFactory.getCurrentSession().createQuery("from Role where id = :id")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public void add(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Role role = session.load(Role.class, id);
        if (role != null) {
            session.delete(role);
        }
    }

    @Override
    public void update(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    @Override
    public List<Role> getRolesByUserId(User user) {
//        TypedQuery<Role> query = sessionFactory.getCurrentSession()
//                .createQuery("select Role.role from Role, User," + " user_role as ur " + " where ur.user_id=Role.id AND ur.role_id = User.id AND User.id = :id")
//                .setParameter("id", user.getId());
        TypedQuery<Role> query = sessionFactory.getCurrentSession()
                .createQuery("select r.role from Role r join r.userSet where r.id=:id")
                .setParameter("id", user.getId());
        return query.getResultList();
    }
}
