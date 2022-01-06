package com.project.homeservicesystem.dao;

import com.project.homeservicesystem.entities.users.UserFeedback;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserFeedbackDao {
    private final SessionFactory sessionFactory;

    public void save(UserFeedback userFeedback) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(userFeedback);
        transaction.commit();
        session.close();
    }
}
