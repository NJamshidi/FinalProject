package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.services.ServiceOffer;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class ServiceOfferDao {
    private final SessionFactory sessionFactory ;

    public void save(ServiceOffer serviceOffer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(serviceOffer);
        transaction.commit();
        session.close();
    }

    public void update(ServiceOffer serviceOffer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(serviceOffer);
        transaction.commit();
        session.close();
    }

    public void delete(ServiceOffer serviceOffer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(serviceOffer);
        transaction.commit();
        session.close();
    }

    public List<ServiceOffer> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ServiceOffer ");
        List<ServiceOffer> serviceOffer = query.list();
        transaction.commit();
        session.close();
        return serviceOffer;
    }
}
