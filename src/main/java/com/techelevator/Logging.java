package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*public class Logging {


        // List to store each transaction in memory
        private List<String> transactions = new ArrayList<>();

        // Variable to track the total money used
        private double moneyUsed = 0.0;



    //Method to log when a purchase is made
    public void purchase(String itemName, double amount){
        // Create a string log of the purchase
        String log = "PURCHASE: " + itemName + " for $" + amount;

        // Add the log to the list of transactions
        transactions.add(log);

        // Update the total money used
        moneyUsed -= amount;

        // Write the log to the file
        writeToFile(log);
        // Method to log when a transaction ends
        public void endTransaction() {
            // Create a string log of the end transaction
            String log = "CHANGE RETURNED: $" + moneyUsed;

            // Add the log to the list of transactions
            transactions.add(log);

            // Reset the total money used for the next transaction
            moneyUsed = 0.0;

            // Write the log to the file
            writeToFile(log);
        }

        // Method to write a string to the log file
        private void writeToFile(String log) {
            try (
                    // Create a FileWriter to write to "Log.txt". The second argument to the FileWriter constructor is "true",
                    // which means that it will append to the file rather than overwriting it.
                    FileWriter fw = new FileWriter("Log.txt", true);

                    // Create a PrintWriter to actually write the text to the file.
                    PrintWriter pw = new PrintWriter(fw)
            ) {
                // Write the log to the file
                pw.println(log);
            } catch (IOException e) {
                // If an error occurs while trying to write to the file, print an error message.
                System.err.println("Unable to write to log file: " + e.getMessage());
            }
        }
    }
}
