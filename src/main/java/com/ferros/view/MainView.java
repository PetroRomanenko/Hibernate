package com.ferros.view;


import java.util.Scanner;

public class MainView {

    private final LabelView labelView = new LabelView();
   /* private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();*/
    private final Scanner scanner = new Scanner(System.in);


    private final String menuMessage = """
            Chose action:\s
            1. Label\s
            2. Post:
            3. Author\s
            4. Exit""";

    private final String line = "****************************************";

    public void showMainMenu() {
        int chose;
        do {
            System.out.println(line);
            System.out.println(menuMessage);
            System.out.println(line);
//
//            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
//                chose = Integer.parseInt(bufferedReader.readLine());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            chose = scanner.nextInt();


            switch (chose) {
                case 1:
                    labelView.menuChoice();
                    break;
                case 2:
//                    postView.menuChoice();
                    break;
                case 3:
//                    writerView.menuChoice();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Wrong choice, enter again: ");
            }
        }while (chose != 4) ;
        System.out.println("Thanks for using our Application");
        System.out.println("See you next time!!!!");


    }



}
