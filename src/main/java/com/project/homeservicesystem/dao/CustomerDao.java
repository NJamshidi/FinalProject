package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.users.Customer;
import com.project.homeservicesystem.entities.users.User;
import com.project.homeservicesystem.util.HibernateUtil;
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
public class CustomerDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }

    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    public void delete(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
    }

    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer ");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

    public Customer findByUserNameAndPass(String userName,String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("From User U Where U.password = :password and  U.userName=:userName");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Customer customer = query.uniqueResult();
        transaction.commit();
        session.close();
        return customer;
    }
    public Optional<Customer> findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Customer> query = session.createQuery("FROM Customer customer WHERE customer.email=:email");
        query.setParameter("email", email);
        Optional<Customer> customer = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return customer;
    }

}
