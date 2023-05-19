package com.ferros.view;

import com.ferros.controller.WriterController;
import com.ferros.exeptions.NoDataInDatabaseException;
import com.ferros.model.Post;
import com.ferros.model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    private final Scanner SCANNER = new Scanner(System.in);
    private final WriterController CONTROLLER = new WriterController();

    private final String CRUDMassage = """
            Chose category in Writer:\s
            1.Create\s
            2.Show all\s
            3.Show by ID\s
            4.Update\s
            5.Delete\s
            6.Exit to previous menu""";

    public void createWriter() {
        SCANNER.nextLine();
        System.out.println("Enter writer First Name: ");
        String firstName = SCANNER.nextLine();



        System.out.println("Enter writer Last Name: ");
        String lastName = SCANNER.nextLine();

        List<Post> posts = new ArrayList<>();
        Writer cratedWriter = CONTROLLER.saveWriter(firstName, lastName, posts);
        printWriter(cratedWriter, "Created writer: ");

    }

    public void findWriterById() {

        System.out.println("Enter ID of desired Writer: ");
        Integer lookedId = SCANNER.nextInt();
        SCANNER.skip("\n");

        try {
            Writer foundWriter = CONTROLLER.findWriterById(lookedId);
            printWriter(foundWriter, "Desired Writer: ");
        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
            menuChoice();
        }


    }

    public void showAllWriter() {
        System.out.println("All Writers: ");
        printWriterList(CONTROLLER.getAllWriters());
    }

    public void updateWriter() {
        System.out.println("Enter Writer id: ");
        Integer updatedWriterId = SCANNER.nextInt();
        SCANNER.skip("\n");


        try {
            System.out.println("Desired Author: " + CONTROLLER.findWriterById(updatedWriterId));
            System.out.println("Enter new writer First Name: ");
            String updatedFirstName = SCANNER.nextLine();

            System.out.println("Enter new writer Last Name: ");
            String updatedLastName = SCANNER.nextLine();
            Writer writer = CONTROLLER.update(updatedFirstName, updatedLastName, updatedWriterId);
            printWriter(writer, "Updated author");
        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
            menuChoice();
        }
    }



    public void deleteWriterById() {
        System.out.println("Enter Writer Id: ");
        Integer deletedWriterID = SCANNER.nextInt();
        SCANNER.skip("\n");

        try{
            Writer writer = CONTROLLER.findWriterById(deletedWriterID);
            System.out.println("You`d like ti delete this Author: " + writer);
            CONTROLLER.deleteWriterById(deletedWriterID);
            printWriter(writer, "Deleted Writer: ");
        } catch (NoDataInDatabaseException e) {
            System.out.println(e.getMessage());
            System.out.println("Try again");
            menuChoice();
        }




    }

    public void showMenuMassage() {
        String line = "****************************************";
        System.out.println(line);
        System.out.println(CRUDMassage);
        System.out.println(line);
    }

    public void menuChoice() {
        int chose;
        do {
            showMenuMassage();
            chose = SCANNER.nextInt();
            switch (chose) {
                case 1 -> createWriter();
                case 2 -> showAllWriter();
                case 3 -> findWriterById();
                case 4 -> updateWriter();
                case 5 -> deleteWriterById();
            }
        } while (chose != 6);
    }


    private void printWriter(Writer writer, String message) {
        if (message != null) {
            System.out.println(message);
        }
        System.out.println("Writer id: " + writer.getId());
        System.out.println("Writer First Name: " + writer.getFirstName());
        System.out.println("Writer Last Name: " + writer.getLastName());
    }

    private void printWriterList(List<Writer> writers) {
        for (Writer writer : writers) {
            printWriter(writer, null);
        }
    }

}

