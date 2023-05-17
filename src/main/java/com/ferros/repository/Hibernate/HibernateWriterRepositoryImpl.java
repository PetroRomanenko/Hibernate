package com.ferros.repository.Hibernate;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.Writer;
import com.ferros.repository.WriterRepository;
import lombok.Cleanup;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class HibernateWriterRepositoryImpl implements WriterRepository {

    private final SessionFactory sessionFactory;
    public HibernateWriterRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public Optional<Writer> getById(Integer id) {
        @Cleanup var session   = sessionFactory.openSession();
        return Optional.ofNullable(session.find(Writer.class,id));
    }

    //TODO Подумать про мапер всех постов, не у верен что тут они будут вытягиватся
    @Override
    public List<Writer> getAll() {
        @Cleanup var session   = sessionFactory.openSession();
        return session.createQuery("select w from Writer w", Writer.class)
                .getResultList();
    }

    @Override
    public Writer save(Writer writer) {
        @Cleanup var session   = sessionFactory.openSession();
        session.beginTransaction();
        for (Post post: writer.getPosts()){
            //TODO  Подумать над тем чтобы не сохранять сущность а вытягивать гетом ее
            session.save((post));
        }
        session.save(writer);
        session.getTransaction().commit();
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(writer);
        session.getTransaction().commit();
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

//        var query = session.createQuery("Delete from Writer w where w.id =: writerId", Writer.class);
//        query.setParameter("writerId",id);

        session.remove( session.find(Writer.class, id));
        session.getTransaction().commit();


    }
}
