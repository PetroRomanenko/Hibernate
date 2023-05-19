package com.ferros.repository.hibernate;

import com.ferros.model.Post;
import com.ferros.model.Writer;
import com.ferros.repository.WriterRepository;
import com.ferros.utils.HibernateUtil;
import jakarta.persistence.NoResultException;
import lombok.Cleanup;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateWriterRepositoryImpl implements WriterRepository {


    @Override
    public Writer getById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT w FROM Writer w LEFT JOIN FETCH w.posts WHERE w.id = :id", Writer.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }


    @Override
    public List<Writer> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT w FROM Writer w LEFT JOIN FETCH w.posts", Writer.class)
                    .getResultList();
        }
    }

    @Override
    public Writer save(Writer writer) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            for (Post post : writer.getPosts()) {
                session.get(Post.class, post.getId());
            }
            session.save(writer);
            session.getTransaction().commit();
            return writer;
        }
    }

    @Override
    public Writer update(Writer writer) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(writer);
            Hibernate.initialize(writer.getPosts());
            session.getTransaction().commit();
            return writer;
        }
    }

    @Override
    public void deleteById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            session.remove(session.find(Writer.class, id));
            session.getTransaction().commit();
        }

    }
}
