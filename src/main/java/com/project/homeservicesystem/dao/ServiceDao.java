package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.services.Service;
import com.project.homeservicesystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Service service) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(service);
        transaction.commit();
        session.close();
    }

    public void update(Service service) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(service);
        transaction.commit();
        session.close();
    }


    public List<Service> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Service");
        List<Service> service = query.list();
        transaction.commit();
        session.close();
        return service;
    }

    public Service findByName(String title) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Service> query = session.createQuery("From Service S Where S.title=:title");
        query.setParameter("title", title);
        Service service = query.uniqueResult();
        transaction.commit();
        session.close();
        return service;
    }
}
