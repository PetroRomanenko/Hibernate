package com.ferros.view;

import com.ferros.controller.WriterController;
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

        checkIfNoSuchWriterInDB(lookedId);

        Writer foundWriter = CONTROLLER.findWriterById(lookedId);
        printWriter(foundWriter, "Desired Writer: ");

    }

    public void showAllWriter() {
        System.out.println("All Writers: ");
        printWriterList(CONTROLLER.getAllWriters());
    }

    public void updateWriter() {
        System.out.println("Enter Writer id: ");
        Integer updatedWriterId = SCANNER.nextInt();
        SCANNER.skip("\n");

        System.out.println("Enter new writer First Name: ");
        String updatedFirstName = SCANNER.nextLine();

        System.out.println("Enter new writer Last Name: ");
        String updatedLastName = SCANNER.nextLine();

        System.out.println("Enter number of post written by writer ");
        Integer writtenPostID = SCANNER.nextInt();

        checkIfNoSuchWriterInDB(updatedWriterId);

        Writer updatedWriter = new Writer( updatedFirstName, updatedLastName);
        updatedWriter.setId(updatedWriterId);
        var updatedRetunedWriter = CONTROLLER.update(updatedWriter, writtenPostID);

        printWriter(updatedRetunedWriter, "Updated Writer: ");

        if (updatedRetunedWriter==null){
            System.out.println("NO such writer choose again");
            menuChoice();
        }
    }

    private void checkIfNoSuchWriterInDB(Integer searchedID) {
        Writer writer = CONTROLLER.findWriterById(searchedID);

        if (writer==null){
            System.out.println("NO such writer choose again");
            menuChoice();
        }
    }

    public void deleteWriterById() {
        System.out.println("Enter Writer Id: ");
        Integer deletedWriterID = SCANNER.nextInt();
        SCANNER.skip("\n");
        checkIfNoSuchWriterInDB(deletedWriterID);
        Writer writer = CONTROLLER.findWriterById(deletedWriterID);
            CONTROLLER.deleteWriterById(deletedWriterID);

        printWriter(writer, "Deleted Writer: ");



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
        if(message!=null) {
            System.out.println(message);
        }
        System.out.println("Writer id: "+writer.getId());
        System.out.println("Writer First Name: " + writer.getFirstName());
        System.out.println("Writer Last Name: " + writer.getLastName());
    }

    private void printWriterList(List<Writer> writers){
        for (Writer writer: writers){
            printWriter(writer, null);
        }
    }

}

