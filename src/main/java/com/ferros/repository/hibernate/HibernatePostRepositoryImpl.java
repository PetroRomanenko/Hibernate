package com.ferros.repository.hibernate;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.repository.PostRepository;
import com.ferros.utils.HibernateUtil;
import jakarta.persistence.NoResultException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernatePostRepositoryImpl implements PostRepository {
    @Override
    public Post getById(Integer id) {
        try (Session session =HibernateUtil.getSession()) {
            return session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.labels WHERE p.id = :id", Post.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    public List<Post> getAll() {
        try (Session session =HibernateUtil.getSession()) {
            return session.createQuery("SELECT p FROM Post p LEFT JOIN FETCH p.labels", Post.class)
                    .getResultList();
        }
    }

    @Override
    public Post save(Post post) {
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();
            for (Label label : post.getLabels()) {
                session.save(label);
            }
            session.save(post);
            session.getTransaction().commit();
            return post;
        }
    }

    @Override
    public Post update(Post post) {
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(post);
            session.getTransaction().commit();
            return post;
        }
    }

    @Override
    public void deleteById(Integer id) {
//        try (Session session =HibernateUtil.getSession()) {
////            session.beginTransaction();
////
////            var query = session.createQuery("DELETE FROM Post p WHERE p.id =: postId", Post.class);
////            query.setParameter("postId", id);
////            session.getTransaction().commit();
//        }
        try (Session session =HibernateUtil.getSession()) {
            session.beginTransaction();

            session.remove(session.find(Post.class, id));
            session.getTransaction().commit();
        }
    }
}
