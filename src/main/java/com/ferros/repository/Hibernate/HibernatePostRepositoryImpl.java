package com.ferros.repository.Hibernate;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.repository.PostRepository;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class HibernatePostRepositoryImpl implements PostRepository {
    private final SessionFactory sessionFactory ;
    public HibernatePostRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public Optional<Post> getById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(Post.class, id));

    }

    @Override
    public List<Post> getAll() {
        @Cleanup var session = sessionFactory.openSession();
        return session.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    @Override
    public Post save(Post post) {
        @Cleanup  var session = sessionFactory.openSession();
        session.beginTransaction();
        for (Label label: post.getLabels()    ) {
            session.save(label);
        }
        session.save(post);
        session.getTransaction().commit();
        return post;
    }

    @Override
    public Post update(Post post) {
         @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(post);
        session.getTransaction().commit();
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var query = session.createQuery("Delete from Post p where p.id =: postId", Post.class);
        query.setParameter("postId",id);
        session.getTransaction().commit();

    }
}
