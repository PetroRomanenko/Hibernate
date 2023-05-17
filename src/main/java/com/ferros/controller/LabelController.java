package com.ferros.controller;

import com.ferros.model.Label;
import com.ferros.repository.Hibernate.HibernateLabelRepositoryImpl;
import com.ferros.repository.LabelRepository;
import com.ferros.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class LabelController {
    private SessionFactory sessionFactory= HibernateUtil.buildSessionFactory();
    private LabelRepository labelRepository = new HibernateLabelRepositoryImpl(sessionFactory);

    public LabelController(){

    }

    //TODO решить что сюда отдавать имя обьекта или на уровне вида соберать обьект сразу, null в имени
    public LabelController(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label saveLabel(String name) {
        if (name!=null) {
            Label label = new Label(null, name);
            return labelRepository.save(label);
        }
        return null;
    }

    public Label findLabelById(Integer id){
        Optional<Label> optionalResult = labelRepository.getById(id);
        return  optionalResult.orElse(null);
    }

    public List<Label> getAllLabels(){

            return labelRepository.getAll();

    }

    public Label update(Label label){
        if (labelRepository.getById(label.getId()).isPresent()){
            labelRepository.update(label);
            return label;
        }
        return null;
    }

    public Label deleteById(Integer id){
        if(labelRepository.getById(id).isPresent()){
            Label label = findLabelById(id);
            labelRepository.deleteById(id);
            return label;
        }
        return null;
    }

}
