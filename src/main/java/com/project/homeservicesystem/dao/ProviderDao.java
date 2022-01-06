package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.users.Provider;
import com.project.homeservicesystem.entities.users.User;
import com.project.homeservicesystem.util.HibernateUtil;
import com.project.homeservicesystem.util.ImageWrapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
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

    public List<Provider> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Provider ");
        List<Provider> providers = query.list();
        transaction.commit();
        session.close();
        return providers;
    }
    public Optional<Provider> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Provider> query = session.createQuery("FROM Provider provider WHERE provider.email=:email");
        query.setParameter("email", email);
        Optional<Provider> provider = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return provider;
    }
    public Optional<Provider> findByUserNameAndPass(String userName,String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Provider> query = session.createQuery("From Provider P Where P.password = :password and  P.userName=:userName");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Optional<Provider> provider = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return provider;
    }

}
