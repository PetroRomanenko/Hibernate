package com.ferros.controller;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Post;
import com.ferros.model.Writer;

import java.util.List;

import org.junit.jupiter.api.Test;

class WriterControllerTest {
    /**
     * Method under test: {@link WriterController#saveWriter(String, String, List)}
     */
    @Test
    void testSaveWriter() {
        // Arrange
        // TODO: Populate arranged inputs
        WriterController writerController = null;
        String firstName = "";
        String lastName = "";
        List<Post> posts = null;

        // Act
        Writer actualSaveWriterResult = writerController.saveWriter(firstName, lastName, posts);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link WriterController#findWriterById(Integer)}
     */
    @Test
    void testFindWriterById() throws NoDataInDatabaseException {
        // Arrange
        // TODO: Populate arranged inputs
        WriterController writerController = null;
        Integer id = null;

        // Act
        Writer actualFindWriterByIdResult = writerController.findWriterById(id);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link WriterController#getAllWriters()}
     */
    @Test
    void testGetAllWriters() {
        // Arrange
        // TODO: Populate arranged inputs
        WriterController writerController = null;

        // Act
        List<Writer> actualAllWriters = writerController.getAllWriters();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link WriterController#update(String, String, Integer)}
     */
    @Test
    void testUpdate() throws NoDataInDatabaseException {
        // Arrange
        // TODO: Populate arranged inputs
        WriterController writerController = null;
        String firstName = "";
        String lastName = "";
        Integer id = null;

        // Act
        Writer actualUpdateResult = writerController.update(firstName, lastName, id);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link WriterController#deleteWriterById(Integer)}
     */
    @Test
    void testDeleteWriterById() {
        // Arrange
        // TODO: Populate arranged inputs
        WriterController writerController = null;
        Integer id = null;

        // Act
        writerController.deleteWriterById(id);

        // Assert
        // TODO: Add assertions on result
    }
}

