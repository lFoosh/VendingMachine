package com.techelevator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Transaction {
    private double currentMoney;
    private String logFile = "Log.txt";

    public Transaction() {
        this.currentMoney = 0.0; // Initialize current money to 0
    }

    // Function to feed money into the vending machine
    public double feedMoney(double moneyInserted) {
        currentMoney += moneyInserted; // Increase current money by the inserted amount
        log("FEED MONEY: ", moneyInserted, currentMoney); // Log the transaction
        return currentMoney; // Return the new total amount of money
    }

    // Function to purchase a product
    public void purchaseItem(Product product) {
        // Check if the product exists and if the current money is enough to buy it
        if (product != null && currentMoney >= product.getPrice()) {
            log("PURCHASE: " + product.getName() + " " + product.getSlotLocation(), product.getPrice(), currentMoney - product.getPrice()); // Log the transaction
            currentMoney -= product.getPrice(); // Decrease current money by the product price
            product.decreaseQuantity(); // Decrease the product quantity

        } else {
            // Print error message if the product does not exist or if there is not enough money
            System.out.println("Unable to complete purchase.");
        }
    }

    // Function to return the change
    public void returnChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        // Determine how many quarters, dimes, and nickels to return
        while (currentMoney >= 0.25) {
            quarters++;
            currentMoney -= 0.25;
        }
        while (currentMoney >= 0.10) {
            dimes++;
            currentMoney -= 0.10;
        }
        while (currentMoney >= 0.05) {
            nickels++;
            currentMoney -= 0.05;
        }

        // Log the returned change
        log("GIVE CHANGE: ", currentMoney, 0);
        // Print the returned change
        System.out.println("Change returned: Quarters: " + quarters + ", Dimes: " + dimes + ", Nickels: " + nickels);
    }

    // Get the current money
    public double getCurrentMoney() {
        return currentMoney;
    }

 
    // Function to log the transactions
    private void log(String action, double amount, double newBalance) {
        // We are using try-with-resources statement, which ensures that each resource is closed at the end of the statement.
        // FileWriter is used to write character-oriented data to a file.
        // BufferedWriter writes text to a character-output stream, buffering characters so as to provide for the efficient writing of single characters, arrays, and strings.
        try (FileWriter fw = new FileWriter(logFile, true);  // We set append to true to write at the end of the file, not erase it.
             BufferedWriter bw = new BufferedWriter(fw)) {

            // We write to the file the following information: the current date and time, the action performed, the amount of money involved in the transaction, and the new balance after the transaction.
            // The amounts of money are formatted to have 2 decimal places.
            bw.write(LocalDateTime.now() + " " + action + " " + String.format("$%.2f", amount) + " $" + String.format("%.2f", newBalance));
            // We then write a new line to the file so that the next log entry is on a new line.
            bw.newLine();

            // If an exception happens while writing to the file, it will be caught here and a message will be printed to the console.
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}



