package com.ferros.utils;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.Writer;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;


@UtilityClass
public class HibernateUtil {
    public static SessionFactory buildSessionFactory(){
        Configuration configuration = buildConfiguration();
        configuration.configure();

        var sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }

    private static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(Label.class);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(Writer.class);
        return configuration;
    }
}
