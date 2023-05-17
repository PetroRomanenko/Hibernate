package com.ferros.controller;

import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.PostStatus;
import com.ferros.repository.Hibernate.HibernatePostRepositoryImpl;
import com.ferros.repository.PostRepository;
import com.ferros.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PostController {
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private PostRepository postRepository = new HibernatePostRepositoryImpl(sessionFactory);
    public Post savePost(String content, List<Label> labels){
        Post post = new Post(null,content,new Date(), null , PostStatus.ACTIVE, labels);

        return postRepository.save(post);


    }

    public Post findPostById(Integer id){
        Optional<Post> optionalPostResult = postRepository.getById(id);
        return optionalPostResult.orElse(null);

    }

    public List<Post> getAllPosts(){
        return postRepository.getAll();
    }

    public Post update(Post post){
        post.setUpdated(new Date());
        post.setPostStatus(PostStatus.UNDER_REVIEW);
        postRepository.update(post);
        return  postRepository.getById(post.getId()).orElse(null);
    }

    public void deletePostById(Integer id){
        postRepository.deleteById(id);
    }



}
