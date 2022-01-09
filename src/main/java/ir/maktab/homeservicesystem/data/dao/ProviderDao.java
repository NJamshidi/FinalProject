package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.validation.ImageWrapper;
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
    private final SessionFactory sessionFactory;


    public void save(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ImageWrapper.saveImage(expert.getFirstName()+ expert.getLastName()+".jpg", expert);
        session.save(expert);
        transaction.commit();
        session.close();
    }

    public void update(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(expert);
        transaction.commit();
        session.close();
    }

    public void delete(Expert expert) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(expert);
        transaction.commit();
        session.close();
    }

    public List<Expert> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Expert ");
        List<Expert> experts = query.list();
        transaction.commit();
        session.close();
        return experts;
    }
    public Optional<Expert> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Expert> query = session.createQuery("FROM Expert provider WHERE provider.email=:email");
        query.setParameter("email", email);
        Optional<Expert> provider = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return provider;
    }
    public Optional<Expert> findByUserNameAndPass(String userName, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Expert> query = session.createQuery("From Expert P Where P.password = :password and  P.userName=:userName");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Optional<Expert> provider = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return provider;
    }

}
