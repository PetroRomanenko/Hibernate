package com.ferros;

import com.ferros.model.Post;
import com.ferros.model.Writer;
import com.ferros.repository.PostRepository;
import com.ferros.repository.WriterRepository;
import com.ferros.repository.hibernate.HibernatePostRepositoryImpl;
import com.ferros.repository.hibernate.HibernateWriterRepositoryImpl;
import com.ferros.view.MainView;

import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.showMainMenu();
    }
}
