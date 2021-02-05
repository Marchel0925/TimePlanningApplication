package utils;

import entities.Details;
import entities.Time;
import entities.Date;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;

public class HibernateSessionBuilder {

    private static final List<Class> ENTITIES = asList(Date.class, Time.class, Details.class);

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String HBM2DDL_AUTO = "validate";

    private static SessionFactory sessionFactory;

    private HibernateSessionBuilder() {
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void shutdown() {
        // Close caches and connection pools
        if(getInstance() != null) getInstance().close();
    }

    private static class LazyHolder {
        static final SessionFactory INSTANCE = initializeSessionFactory();
    }

    private static SessionFactory initializeSessionFactory() {
        try {
            Configuration configuration = configuration(properties());
            sessionFactory = configuration.buildSessionFactory(serviceRegistry(configuration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Configuration configuration(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        ENTITIES.forEach(configuration::addAnnotatedClass);
        return configuration;
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DB_DRIVER);
        properties.put(Environment.URL, UserUtils.DATABASE_HOST);
        properties.put(Environment.USER, UserUtils.DATABASE_USERNAME);
        properties.put(Environment.PASS, UserUtils.DATABASE_PASSWORD);
        properties.put(Environment.DIALECT, DB_DIALECT);
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
        return properties;
    }

    private static ServiceRegistry serviceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
    }
}