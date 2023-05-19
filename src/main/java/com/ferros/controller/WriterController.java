package com.ferros.controller;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Post;
import com.ferros.model.Writer;
import com.ferros.repository.hibernate.HibernateWriterRepositoryImpl;
import com.ferros.repository.WriterRepository;

import java.util.List;

public class WriterController {

    private final WriterRepository writerRepository = new HibernateWriterRepositoryImpl();
    public Writer saveWriter(String firstName, String lastName, List<Post> posts){
        Writer writer = Writer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .posts(posts)
                .build();

        return writerRepository.save(writer);


    }

    public Writer  findWriterById(Integer id) throws NoDataInDatabaseException {
        Writer writer= writerRepository.getById(id);
        if (writer!=null){
            return writer;
        }else {
            throw new NoDataInDatabaseException("No writer with current id:" +id );
        }


    }

    public List<Writer> getAllWriters(){

        return writerRepository.getAll();
    }

    public Writer update(String firstName, String lastName, Integer id) throws NoDataInDatabaseException {
        Writer writer = findWriterById(id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);

        return writerRepository.update(writer);
    }

    public void deleteWriterById(Integer id){

        writerRepository.deleteById(id);
    }


}
