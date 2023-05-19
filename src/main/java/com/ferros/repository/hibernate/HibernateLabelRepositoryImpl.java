package com.ferros.repository.hibernate;



import com.ferros.model.Label;
import com.ferros.repository.LabelRepository;
import com.ferros.utils.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class HibernateLabelRepositoryImpl implements LabelRepository {



    @Override
    public Label getById(Integer id) {
        try (Session session =HibernateUtil.getSession()) {
            return session.find(Label.class, id);
        }

    }

    @Override
    public List<Label> getAll() {
        try (Session session =HibernateUtil.getSession()) {
            return session.createQuery("select l from Label l", Label.class)
                    .getResultList();
        }
    }

    @Override
    public Label save(Label label) {
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(label);
            session.getTransaction().commit();
            return label;
        }
    }

    @Override
    public Label update(Label label) {
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(label);
            session.getTransaction().commit();
            return label;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();

            session.remove(session.find(Label.class, id));
            session.getTransaction().commit();
        }
    }
}
