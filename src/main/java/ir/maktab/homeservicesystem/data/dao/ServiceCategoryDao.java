package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import lombok.RequiredArgsConstructor;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ServiceCategoryDao {
    private final SessionFactory sessionFactory ;

    public void save(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(subService);
        transaction.commit();
        session.close();
    }

    public void update(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(subService);
        transaction.commit();
        session.close();
    }

    public void delete(SubService subService) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(subService);
        transaction.commit();
        session.close();
    }

    public List<SubService> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from SubService ");
        List<SubService> subService = query.list();
        transaction.commit();
        session.close();
        return subService;
    }

    public Optional<SubService> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S where S.id=:id");
        query.setParameter("id", id);
        Optional<SubService> serviceCategory = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return serviceCategory;
    }
    public Optional<SubService> findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<SubService> query = session.createQuery("From SubService S where S.name=:name");
        query.setParameter("name", name);
        Optional<SubService> serviceCategory = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return serviceCategory;
    }

}
