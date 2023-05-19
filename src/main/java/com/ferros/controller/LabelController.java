package com.ferros.controller;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Label;
import com.ferros.repository.hibernate.HibernateLabelRepositoryImpl;
import com.ferros.repository.LabelRepository;
import com.ferros.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class LabelController {
    private final LabelRepository labelRepository = new HibernateLabelRepositoryImpl();


    public Label saveLabel(String name) {
            Label label = Label.builder()
                    .name(name)
                    .build();
            return labelRepository.save(label);
    }

    public Label findLabelById(Integer id) throws NoDataInDatabaseException {
        Label label;
        if (labelRepository.getById(id)!=null) {
            label = labelRepository.getById(id);
            return label;
        }else {
            throw new NoDataInDatabaseException("No label with current id: " +id );
        }

    }

    public List<Label> getAllLabels() {

        return labelRepository.getAll();

    }

    public Label update(Label label) {
        return labelRepository.update(label);
    }

    public void deleteById(Integer id) {
        labelRepository.deleteById(id);
    }

}
