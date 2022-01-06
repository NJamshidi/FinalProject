package com.project.homeservicesystem.config;

import com.project.homeservicesystem.entities.services.MainService;
import com.project.homeservicesystem.entities.services.ServiceCategory;
import com.project.homeservicesystem.entities.services.ServiceOffer;
import com.project.homeservicesystem.entities.services.ServiceRequest;
import com.project.homeservicesystem.entities.users.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    public static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                {
                    Configuration configuration = new Configuration();
                    Properties setting = new Properties();

                    setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    setting.put(Environment.URL, "jdbc:mysql://localhost:3306/homeservice");
                    setting.put(Environment.USER, "root");
                    setting.put(Environment.PASS, "123asPKb73!");
                    setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                    setting.put(Environment.SHOW_SQL, "false");
                    setting.put(Environment.FORMAT_SQL, "false");
                    setting.put(Environment.HBM2DDL_AUTO, "update");

                    configuration.setProperties(setting);
                    configuration.addAnnotatedClass(User.class);
                    configuration.addAnnotatedClass(Admin.class);
                    configuration.addAnnotatedClass(Provider.class);
                    configuration.addAnnotatedClass(Customer.class);
                    configuration.addAnnotatedClass(ServiceCategory.class);
                    configuration.addAnnotatedClass(MainService.class);
                    configuration.addAnnotatedClass(ServiceOffer.class);
                    configuration.addAnnotatedClass(ServiceRequest.class);
                    configuration.addAnnotatedClass(UserFeedback.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
