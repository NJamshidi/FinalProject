package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.services.MainService;
import com.project.homeservicesystem.config.HibernateConfig;
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
public class MainServiceDao {
    private SessionFactory sessionFactory ;

    public void save(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(mainService);
        transaction.commit();
        session.close();
    }

    public void update(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(mainService);
        transaction.commit();
        session.close();
    }


    public List<MainService> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from MainService");
        List<MainService> mainService = query.list();
        transaction.commit();
        session.close();
        return mainService;
    }

    public Optional<MainService> findByTitle(String title) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<MainService> query = session.createQuery("From MainService S Where S.title=:title");
        query.setParameter("title", title);
        Optional<MainService> mainService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return mainService;
    }
    public void delete(MainService mainService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(mainService);
        transaction.commit();
        session.close();
    }
    public Optional<MainService> findById( Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<MainService> query = session.createQuery("From MainService S Where S.id=:id");
        query.setParameter("id", id);
        Optional<MainService> mainService = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return mainService;
    }
}
