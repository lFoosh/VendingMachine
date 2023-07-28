package com.techelevator.controller;

import com.techelevator.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private BigDecimal currentMoney;
    private String logFile = "Log.txt";

    private int itemsPurchased = 0;

    public Transaction() {
        this.currentMoney = BigDecimal.valueOf(0.0); // Initialize current money to 0
    }

    // Function to feed money into the vending machine
    public BigDecimal feedMoney(String moneyInserted) {  //TODO Make test to check ~~


        BigDecimal amount = new BigDecimal(moneyInserted);

        if(amount.compareTo(new BigDecimal(0.00)) >= 0) {
            currentMoney = currentMoney.add(amount); // Increase current money by the inserted amount
            log("FEED MONEY: ", amount, currentMoney); // Log the transaction

        } else {

            System.out.println("Invalid amount of money inserted: negative number entered");
        }
        return currentMoney; // Return the new total amount of money
    }

    // Function to purchase a product
    public void purchaseItem(Product product) {
        // Check if the product exists and if the current money is enough to buy it

        if (product != null && (currentMoney.compareTo(product.getPrice())>=0)) {
            this.itemsPurchased ++;
            BigDecimal discount;
            if (this.itemsPurchased % 2 == 0) {
                discount = BigDecimal.ONE;

                System.out.println();
                System.out.println("BOGODO Sale!!");
                System.out.println("You saved $1.00!!");

                System.out.println();
            }else{
                discount = BigDecimal.ZERO;
            }

            //double price = product.getPrice() - discount; // this is applying discount for every 2
            BigDecimal price = product.getPrice().subtract(discount);

            currentMoney = currentMoney.subtract(price); // Decrease current money by the product price

            log("PURCHASE: " + product.getName() + " " + product.getSlotLocation(), price, currentMoney); // Log the transaction

            product.decreaseQuantity(); // Decrease the product quantity

        } else {
            // Print error message if the product does not exist or if there is not enough money
            System.out.println("Unable to complete purchase.");
        }
    }

    // Function to return the change
    public String returnChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        // Determine how many quarters, dimes, and nickels to return
        while (currentMoney.compareTo(new BigDecimal(.25)) >= 0) {
            quarters++;
          currentMoney =   currentMoney.subtract(BigDecimal.valueOf(.25));
        }
        while (currentMoney.compareTo(BigDecimal.valueOf(.10)) >= 0) {
            dimes++;
           currentMoney =  currentMoney.subtract(BigDecimal.valueOf(.10));
        }
        while (currentMoney.compareTo(BigDecimal.valueOf(.05)) >= 0) {
            nickels++;
         currentMoney =    currentMoney.subtract(BigDecimal.valueOf(.05));
        }

        // Log the returned change
        log("GIVE CHANGE: ", currentMoney, BigDecimal.valueOf(0));
        // Print the returned change
        return  "Change returned: Quarters: " + quarters + ", Dimes: " + dimes + ", Nickels: " + nickels;
    }

    // Get the current money
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }


    // Function to log the transactions
    private void log(String action, BigDecimal amount, BigDecimal newBalance) {
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



