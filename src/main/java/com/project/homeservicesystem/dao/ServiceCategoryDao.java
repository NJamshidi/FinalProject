package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.services.ServiceCategory;
import com.project.homeservicesystem.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ServiceCategoryDao {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    public void save(ServiceCategory serviceCategory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(serviceCategory);
        transaction.commit();
        session.close();
    }

    public void update(ServiceCategory serviceCategory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(serviceCategory);
        transaction.commit();
        session.close();
    }

    public void delete(ServiceCategory serviceCategory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(serviceCategory);
        transaction.commit();
        session.close();
    }

    public List<ServiceCategory> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from ServiceCategory ");
        List<ServiceCategory> serviceCategory = query.list();
        transaction.commit();
        session.close();
        return serviceCategory;
    }

    public Optional<ServiceCategory> findById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<ServiceCategory> query = session.createQuery("From ServiceCategory S where S.id=:id");
        query.setParameter("id", id);
        Optional<ServiceCategory> serviceCategory = Optional.ofNullable(query.uniqueResult());
        transaction.commit();
        session.close();
        return serviceCategory;
    }
    public List<ServiceCategory> findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(ServiceCategory.class, "s");
        criteria.setFetchMode("providers", FetchMode.EAGER);
        criteria.add(Restrictions.eq("s.name", name));
        List<ServiceCategory> serviceCategoryList = criteria.list();
        transaction.commit();
        session.close();
        return serviceCategoryList;
    }
}
