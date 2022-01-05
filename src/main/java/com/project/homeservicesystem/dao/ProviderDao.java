package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.users.Provider;
import com.project.homeservicesystem.entities.users.User;
import com.project.homeservicesystem.util.HibernateUtil;
import com.project.homeservicesystem.util.ImageWrapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProviderDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();


    public void save(Provider provider) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ImageWrapper.saveImage(provider.getFirstName()+provider.getLastName()+".jpg",provider);
        session.save(provider);
        transaction.commit();
        session.close();
    }

    public void update(Provider provider) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(provider);
        transaction.commit();
        session.close();
    }

    public void delete(Provider provider) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(provider);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Provider ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

}
