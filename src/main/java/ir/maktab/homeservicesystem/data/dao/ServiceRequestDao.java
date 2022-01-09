package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ServiceRequestDao {
        private final SessionFactory sessionFactory ;

        public void save(Order order) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
            session.close();
        }

        public void update(Order order) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            session.close();
        }

        public void delete(Order order) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            session.close();
        }

        public List<Order> findAll() {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Order");
            List<Order> order = query.list();
            transaction.commit();
            session.close();
            return order;
        }

        public Order findServiceRequestByID(int id){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Order R where R.id=:id");
            query.setParameter("id", id);
            Order order = (Order) query.uniqueResult();
            transaction.commit();
            session.close();
            return order;
        }


}
