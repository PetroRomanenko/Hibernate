package com.ferros.controller;

import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.PostStatus;
import com.ferros.model.Writer;
import com.ferros.repository.WriterRepository;
import com.ferros.repository.hibernate.HibernatePostRepositoryImpl;
import com.ferros.repository.PostRepository;
import com.ferros.repository.hibernate.HibernateWriterRepositoryImpl;

import java.util.Date;
import java.util.List;

public class PostController {
    private final PostRepository postRepository = new HibernatePostRepositoryImpl();
    private final WriterRepository writerRepository = new HibernateWriterRepositoryImpl() ;

    public Post savePost(String content, Integer writerId, List<Label> labels) {

        Post post = Post.builder()
                .content(content)
                .created(new Date())
                .labels(labels)
                .status(PostStatus.ACTIVE)
                .writer(writerRepository.getById(writerId))
                .build();

        return postRepository.save(post);


    }
    public Post findPostById(Integer id) throws NoDataInDatabaseException {
        Post post;
        if (postRepository.getById(id)!=null) {
            post = postRepository.getById(id);
            return post;
        }else {
            throw new NoDataInDatabaseException("No post with current id: " +id );
        }

    }




    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    public Post update(String updatedPostContent, Integer updatedPostId) throws NoDataInDatabaseException {
        Post post=findPostById(updatedPostId);
        post.setUpdated(new Date());
        post.setContent(updatedPostContent);
        post.setStatus(PostStatus.UNDER_REVIEW);
        postRepository.update(post);
        return postRepository.getById(post.getId());
    }

    public void deletePostById(Integer id) {

        postRepository.deleteById(id);
    }



}
