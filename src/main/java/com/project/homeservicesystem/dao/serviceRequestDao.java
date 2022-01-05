package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.services.ServiceRequest;
import com.project.homeservicesystem.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class serviceRequestDao {
        private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

        public void save(ServiceRequest serviceRequest) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(serviceRequest);
            transaction.commit();
            session.close();
        }

        public void update(ServiceRequest serviceRequest) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(serviceRequest);
            transaction.commit();
            session.close();
        }

        public void delete(ServiceRequest serviceRequest) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(serviceRequest);
            transaction.commit();
            session.close();
        }

        public List<ServiceRequest> findAll() {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from ServiceRequest");
            List<ServiceRequest> serviceRequest = query.list();
            transaction.commit();
            session.close();
            return serviceRequest;
        }

        public ServiceRequest findServiceRequestByID(int id){
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from ServiceRequest R where R.id=:id");
            query.setParameter("id", id);
            ServiceRequest serviceRequest = (ServiceRequest) query.uniqueResult();
            transaction.commit();
            session.close();
            return serviceRequest;
        }

}
