package com.ferros.controller;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.Writer;
import com.ferros.model.PostStatus;
import com.ferros.repository.Hibernate.HibernatePostRepositoryImpl;
import com.ferros.repository.Hibernate.HibernateWriterRepositoryImpl;
import com.ferros.repository.PostRepository;
import com.ferros.repository.WriterRepository;
import com.ferros.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class WriterController {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private WriterRepository writerRepository = new HibernateWriterRepositoryImpl(sessionFactory);
    public Writer saveWriter(Writer writer){

        return writerRepository.save(writer);


    }

    public Writer  findWriterById(Integer id){
        Optional<Writer> optionalWriterResult = writerRepository.getById(id);
        return optionalWriterResult.orElse(null);

    }

    public List<Writer> getAllPosts(){

        return writerRepository.getAll();
    }

    public Writer update(Writer writer){
        writerRepository.update(writer);
        return  writerRepository.getById(writer.getId()).orElse(null);
    }

    public void deletePostById(Integer id){
        writerRepository.deleteById(id);
    }


}
