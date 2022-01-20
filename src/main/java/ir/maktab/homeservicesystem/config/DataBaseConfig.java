package ir.maktab.homeservicesystem.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "ir.maktab.homeservicesystem.data.dao")
@PropertySource("classpath:database.properties")
@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class DataBaseConfig {
//    @Autowired
    private final Environment environment;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver")); //getrequiredproperty
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.user"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }


    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("ir.maktab.homeservicesystem.data.entities");
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibernateProperties(){
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        //jpaProperties.put("hibernate.ejb.naming_strategy", environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        //jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return jpaProperties;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}

//    public DataBaseConfig(Environment environment) {
//        this.env = environment;
//    }
//
//    @Bean("sessionFactory")
//    @DependsOn("hibernateProperties")
//    public SessionFactory getSessionFactory(java.util.Properties hibernateProperties) {
//        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//        configuration.addAnnotatedClass(UserFeedback.class);
//        configuration.addAnnotatedClass(Customer.class);
//        configuration.addAnnotatedClass(Expert.class);
//        configuration.addAnnotatedClass(MainService.class);
//        configuration.addAnnotatedClass(Admin.class);
//        configuration.addAnnotatedClass(Order.class);
//        configuration.addAnnotatedClass(SubService.class);
//        configuration.addAnnotatedClass(Offer.class);
//        configuration.addAnnotatedClass(User.class);
//
//        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                .applySettings(hibernateProperties).build();
//        return configuration.buildSessionFactory(serviceRegistry);
//    }
//
//    @Bean
//    public java.util.Properties hibernateProperties() {
//        java.util.Properties properties = new java.util.Properties();
//        properties.setProperty(org.hibernate.cfg.Environment.DRIVER, env.getProperty("hibernate.connection.driver_class"));
//        properties.setProperty(org.hibernate.cfg.Environment.URL, env.getProperty("hibernate.connection.url"));
//        properties.setProperty(org.hibernate.cfg.Environment.USER, env.getProperty("hibernate.connection.username"));
//        properties.setProperty(org.hibernate.cfg.Environment.PASS, env.getProperty("hibernate.connection.password"));
//        properties.setProperty(org.hibernate.cfg.Environment.DIALECT, env.getProperty("hibernate.dialect"));
//        properties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, env.getProperty("hibernate.show_sql"));
//        properties.setProperty(org.hibernate.cfg.Environment.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
//        properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
//        return properties;
//    }


