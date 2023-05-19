package com.ferros.utils;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.Writer;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@UtilityClass
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = buildConfiguration();
            configuration.configure();

            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    private static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Label.class);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(Writer.class);
        return configuration;
    }
}
