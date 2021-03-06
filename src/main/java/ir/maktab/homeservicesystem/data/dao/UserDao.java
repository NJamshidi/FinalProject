package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.users.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {
    private final SessionFactory sessionFactory ;

    public int save(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(user);
        transaction.commit();
        session.close();
        if (id == 1) {
            return 1;
        }
        return -1;
    }


    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }
    public Optional<User> findUserByUserNameAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("From User U Where U.password = :password and  U.userName=:userName");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Optional<User> user =  Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return user;
    }


    public Optional<User> findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("From User U Where U.email = :email");
        query.setParameter("email", email);
        Optional<User> user =  Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return user;
    }

    public List<User> findUserByConditions(String firstName, String lastName, String email, Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "u");
        if (firstName != null) {
            criteria.add(Restrictions.eq("u.firstName", firstName));
        }
        if (lastName != null) {
            criteria.add(Restrictions.eq("u.lastName", lastName));
        }
        if (email != null) {
            criteria.add(Restrictions.eq("u.email", email));
        }
        if (role != null) {
            criteria.add(Restrictions.eq("u.role", role));
        }
        List<User> users = criteria.list();
        transaction.commit();
        session.close();
        return users;
    }
}
