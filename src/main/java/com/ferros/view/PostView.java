package com.ferros.view;
import com.ferros.controller.PostController;
import com.ferros.model.Post;
import com.ferros.model.PostStatus;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class PostView {
    private Scanner scanner = new Scanner(System.in);
    private PostController controller = new PostController();

    private final String CRUDMassage = """
            Chose action in Post:\s
            1.Create\s
            2.Show all\s
            3.Show by ID\s
            4.Update\s
            5.Delete\s
            6.Show list of label in Post\s
            7.Exit to previous menu""";
    private final String line = "****************************************";

    public void createPost() {
        scanner.nextLine();
        System.out.println("Enter your Post: ");
        String content = scanner.nextLine();

        Post createdPost = controller.savePost(content);
        System.out.println("Saved post: " + createdPost);
    }

    public void findPostById() {
        System.out.println("Enter ID of desired Post: ");
        int lookedId = scanner.nextInt();
        scanner.skip("\n");

        Post foundedPost = controller.findPostById(lookedId);
        printPost(foundedPost, "You are looked for this post: ");
    }

    public void showAllPosts() {
        System.out.println("All posts: ");
        printPostList(controller.getAllPosts());

    }



    public void updatePost() {
        System.out.println("Enter Post id: ");
        int updatedPostID = scanner.nextInt();
        scanner.skip("\n");
        System.out.println("Desired Post: " + controller.findPostById(updatedPostID));

        if(controller.findPostById(updatedPostID)!=null){
            System.out.println("Change name of Post: ");
            String updatedPostName = scanner.nextLine();

            LabelView labelView = new LabelView();

            System.out.println("Chose id of Label: ");
            labelView.showAllLabels();
            Integer labelId = scanner.nextInt();
            scanner.skip("\n");

            Post updatedPost = controller.findPostById(updatedPostID);
            updatedPost.setContent(updatedPostName);

            updatedPost.setLabels(controller.getAllLabelsInThisPost(updatedPostID));
            controller.update(updatedPost,labelId);
            printPost(updatedPost,"Updated post: ");
        }else {
            System.out.println("Нет такого поста выберете еще раз");
            menuChoice();
        }


    }

    public void deletePostByID() {
        System.out.println("Enter Post Id: ");
        int deletedPostID = scanner.nextInt();
        scanner.skip("\n");
        if(controller.findPostById(deletedPostID)!=null) {
            Post post = controller.findPostById(deletedPostID);
            post.setStatus(PostStatus.DELETED);
            controller.deletePostById(deletedPostID);
            printPost(post,"Deleted Post");
        }else {
            System.out.println("Нет такого поста выберете еще раз");
            menuChoice();
        }
    }

    private void getAllLabelInPost() {
        System.out.println("Enter Post Id: ");
        int postID = scanner.nextInt();
        scanner.skip("\n");
        if(controller.findPostById(postID)!=null) {
            if (controller.getAllLabelsInThisPost(postID)!=null) {
                LabelView labelView = new LabelView();
                labelView.printList(controller.getAllLabelsInThisPost(postID));
            }else {
                System.out.println("У данного поста нет ЛЕйблов");
            }

        }else {
            System.out.println("Нет такого поста выберете еще раз");
            menuChoice();
        }

    }

    public void showMenuMassage() {
        System.out.println(line);
        System.out.println(CRUDMassage);
        System.out.println(line);
    }

    public void menuChoice() {
        int chose;
        do {
            showMenuMassage();
            chose = scanner.nextInt();
            switch (chose) {
                case 1 -> createPost();
                case 2 -> showAllPosts();
                case 3 -> findPostById();
                case 4 -> updatePost();
                case 5 -> deletePostByID();
                case 6 -> getAllLabelInPost();
            }
        } while (chose != 7);


    }



    public void printPostList(List<Post> postList){

        for (Post post:postList  ) {
            printPost(post,null);
        }
    }

    public void printPost(Post post, String message){
        if(message!=null) {
            System.out.println(message);
        }
        System.out.print("Post id: " +post.getId()+"    ");
        System.out.println("Post content: " +post.getContent());
        System.out.println("Post created: "+new Date(post.getCreated()));
        if (post.getUpdated()!=null){
            System.out.println("Post updated:" +new Date(post.getUpdated()));
        }
        System.out.println("Post status: " + post.getStatus());
        System.out.println("Labels in this post: ");
        LabelView labelView = new LabelView();
        labelView.printList(post.getLabels());
    }

}
