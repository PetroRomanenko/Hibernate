package com.ferros.repository.Hibernate;



import com.ferros.model.Label;
import com.ferros.repository.LabelRepository;
import lombok.Cleanup;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public class HibernateLabelRepositoryImpl implements LabelRepository {
    private final SessionFactory sessionFactory ;

    public HibernateLabelRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<Label> getById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.ofNullable(session.find(Label.class, id));
    }

    @Override
    public List<Label> getAll() {
        @Cleanup var session = sessionFactory.openSession();
        return session.createQuery("select l from Label l", Label.class)
                .getResultList();
    }

    @Override
    public Label save(Label label) {
        @Cleanup  var session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(label);
        session.getTransaction().commit();
        return label;
    }

    @Override
    public Label update(Label label) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(label);
//        session.flush();
        session.getTransaction().commit();
        return label;
    }

    @Override
    public void deleteById(Integer id) {

         var session = sessionFactory.openSession();
         session.beginTransaction();

         session.remove( session.find(Label.class, id));
         session.getTransaction().commit();
//        session.flush();
    }
}
