package com.techelevator.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public Menu(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
    }

    public String getChoiceFromOptions(String[] options) {
        String choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            String userInput = scanner.nextLine();
            if (isValidChoice(userInput, options)) {
                choice = options[Integer.parseInt(userInput) - 1];
            } else {
                System.out.println("Invalid selection, please try again...");
            }
        }
        return choice;
    }

    private void displayMenuOptions(String[] options) {

        System.out.println("\n***** Please choose an option *****\n");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
    }

    private boolean isValidChoice(String choice, String[] options) {
        try {
            int selectedOption = Integer.parseInt(choice);
            if (selectedOption > 0 && selectedOption <= options.length) {
                return true;
            }
        } catch (NumberFormatException e) {
            // Input was not a number
        }
        return false;
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
