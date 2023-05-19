package com.ferros.controller;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Label;
import com.ferros.model.Post;

import java.util.List;

import org.junit.jupiter.api.Test;

class PostControllerTest {
    /**
     * Method under test: {@link PostController#savePost(String, Integer, List)}
     */
    @Test
    void testSavePost() {
        // Arrange
        // TODO: Populate arranged inputs
        PostController postController = null;
        String content = "";
        Integer writerId = null;
        List<Label> labels = null;

        // Act
        Post actualSavePostResult = postController.savePost(content, writerId, labels);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link PostController#findPostById(Integer)}
     */
    @Test
    void testFindPostById() throws NoDataInDatabaseException {
        // Arrange
        // TODO: Populate arranged inputs
        PostController postController = null;
        Integer id = null;

        // Act
        Post actualFindPostByIdResult = postController.findPostById(id);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link PostController#getAllPosts()}
     */
    @Test
    void testGetAllPosts() {
        // Arrange
        // TODO: Populate arranged inputs
        PostController postController = null;

        // Act
        List<Post> actualAllPosts = postController.getAllPosts();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link PostController#update(String, Integer)}
     */
    @Test
    void testUpdate() throws NoDataInDatabaseException {
        // Arrange
        // TODO: Populate arranged inputs
        PostController postController = null;
        String updatedPostContent = "";
        Integer updatedPostId = null;

        // Act
        Post actualUpdateResult = postController.update(updatedPostContent, updatedPostId);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link PostController#deletePostById(Integer)}
     */
    @Test
    void testDeletePostById() {
        // Arrange
        // TODO: Populate arranged inputs
        PostController postController = null;
        Integer id = null;

        // Act
        postController.deletePostById(id);

        // Assert
        // TODO: Add assertions on result
    }
}

