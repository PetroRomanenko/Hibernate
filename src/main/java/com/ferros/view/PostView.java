package com.ferros.view;

import com.ferros.controller.LabelController;
import com.ferros.controller.PostController;
import com.ferros.controller.WriterController;
import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Label;
import com.ferros.model.Post;
import com.ferros.model.PostStatus;
import com.ferros.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private Scanner scanner = new Scanner(System.in);
    private PostController controller = new PostController();
    private LabelController labelController = new LabelController();
    private WriterController writerController = new WriterController();


    private final String CRUDMassage = """
            Chose action in Post:\s
            1.Create\s
            2.Show all\s
            3.Show by ID\s
            4.Update\s
            5.Delete\s
            6.Exit to previous menu""";
    private final String line = "****************************************";

    public List<Label> getLabelsForThisPost() {
        List<Label> labels = new ArrayList<>();
        scanner.nextLine();
        System.out.println("Choose label for this post: ");

        System.out.println(labelController.getAllLabels());
        System.out.println("Write id of label, to exit write 0:");
        Integer id;
        do {

            id = scanner.nextInt();
            if (id != 0) {
                try {
                    labels.add(labelController.findLabelById(id));
                } catch (NoDataInDatabaseException e) {
                    System.out.println(e.getMessage());
                    menuChoice();
                }
            }
        } while (id != 0);


        return labels;
    }

    public void createPost() {
        scanner.nextLine();
        System.out.println("Enter your Post: ");
        String content = scanner.nextLine();
        System.out.println("Chose an writers id: ");
        //ToDO написать метод для печати лейблов
        printAllWriter();
        Integer writer = scanner.nextInt();
        List<Label> labels = getLabelsForThisPost();
        if (content != null && writer != null) {
            Post createdPost = controller.savePost(content, writer, labels);
            System.out.println("Saved post: " + createdPost);
        } else {
            System.out.println("Try Again something goes wrong");
            menuChoice();
        }
    }

    private void printAllWriter() {
        List<Writer> writers = writerController.getAllWriters();
        for (Writer writer : writers) {
            System.out.println("Writer id: " + writer.getId()
                               + "Writer name: " + writer.getFirstName()
                               + "Writer lastname: " + writer.getLastName());

        }
    }

    public void findPostById() {
        System.out.println("Enter ID of desired Post: ");
        int lookedId = scanner.nextInt();
        scanner.skip("\n");
        try {
            Post foundedPost = controller.findPostById(lookedId);
            printPost(foundedPost, "You are looked for this post: ");
        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
            menuChoice();
        }
    }

    public void showAllPosts() {
        System.out.println("All posts: ");
        printPostList(controller.getAllPosts());

    }


    public void updatePost() {
        System.out.println("Enter Post id: ");
        int updatedPostID = scanner.nextInt();
        scanner.skip("\n");

        try {

            System.out.println("Desired Post: " + controller.findPostById(updatedPostID));
            System.out.println("Change name of Post: ");
            String updatedPostName = scanner.nextLine();
            if (updatedPostName != null) {

                Post post = controller.update(updatedPostName, updatedPostID);
                printPost(post, "Updated post: ");
            }

        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
            menuChoice();
        }


    }

    public void deletePostByID() {
        System.out.println("Enter Post Id: ");
        int deletedPostID = scanner.nextInt();
        scanner.skip("\n");
        try {
            controller.findPostById(deletedPostID);
            Post post = controller.findPostById(deletedPostID);
            post.setStatus(PostStatus.DELETED);
            controller.deletePostById(deletedPostID);
            printPost(post, "Deleted Post");

        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
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

            }
        } while (chose != 6);


    }


    public void printPostList(List<Post> postList) {

        for (Post post : postList) {
            printPost(post, null);
            System.out.println("------------------------------------");
        }
    }

    public void printPost(Post post, String message) {
        if (message != null) {
            System.out.println(message);
        }
        System.out.print("Post id: " + post.getId() + "    ");
        System.out.println("Post content: " + post.getContent());
        System.out.println("Post created: " + post.getCreated());
        if (post.getUpdated() != null) {
            System.out.println("Post updated:" + post.getUpdated());
        }
        System.out.println("Post status: " + post.getStatus());
        System.out.println("Post created by: " + post.getWriter().getFirstName() + " " + post.getWriter().getLastName());
        LabelView labelView = new LabelView();
        if (post.getLabels().isEmpty()) {
        } else {
            System.out.println("Labels in this post: ");
            labelView.printList(post.getLabels());
        }
    }

}
