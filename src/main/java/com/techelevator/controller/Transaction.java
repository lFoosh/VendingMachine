package com.techelevator.controller;

import com.techelevator.model.Product;

import java.io.*;
import java.math.BigDecimal;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

public class Transaction {
    private BigDecimal currentMoney;
    private String logFile = "Log.txt";

    private Inventory inventory;

    private int itemsPurchased = 0;

    private BigDecimal totalSales = BigDecimal.ZERO;



    public Transaction() {
        this.currentMoney = BigDecimal.valueOf(0.0); // Initialize current money to 0
    }

    // Function to feed money into the vending machine
    public BigDecimal feedMoney(String moneyInserted) {


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

                product.incrementPurchasedOnSale();
                product.incrementTimesPurchased();




                System.out.println();
            }else{
                discount = BigDecimal.ZERO;
                product.incrementTimesPurchased();
            }

            //double price = product.getPrice() - discount; // this is applying discount for every 2
            BigDecimal price = product.getPrice().subtract(discount);

            totalSales = totalSales.add(price);

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

        BigDecimal startingMoney = currentMoney;

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
        log("GIVE CHANGE: ", startingMoney, currentMoney);
        // Print the returned change
        return  "Change returned: Quarters: " + quarters + ", Dimes: " + dimes + ", Nickels: " + nickels;
    }

    // Get the current money
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }


    // Function to log the transactions
    private void log(String action, BigDecimal amount, BigDecimal newBalance) {



        try (FileWriter log = new FileWriter(logFile, true)){


            //creates the string that will be written to log.txt


            String localDate = String.valueOf(LocalDate.now());

            LocalTime now = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a"); //formats the time so it displays hours, minutes, second, and AM/PM
            String formattedTime = now.format(formatter);


           String writeToFile = localDate + " " + formattedTime + " " + action + " " + String.format("$%.2f", amount) + " $" + String.format("%.2f", newBalance);


           //adds a new line at the end of each added string
            log.write(writeToFile + "\n");





        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    public void salesReport(Map<String, Product> inventory) {

        String localTime = String.valueOf(LocalTime.now());
        String localDate = String.valueOf(LocalDate.now());

        // Get the current date and time, format it as a string and add it to the filename
       String filename = localDate + "_" + localTime.substring(0,2)+ "h" + localTime.substring(3,5) + "m" + localTime.substring(6,8) + "s" +"_SalesReport" + ".txt";

        // Create a new file with the above filename
        File reportFile = new File(filename);

        // Using a FileWriter to write text to the above file. The 'try-with-resources' statement automatically closes the writer
        try (FileWriter writer = new FileWriter(reportFile, false)) {
            // This variable will keep track of the total sales


            // Iterate over all products in the inventory
            for (Product product : inventory.values()) {
                // Prepare a string for each product with its name and the number of times it was purchased
                String line = product.getName() + "|" + product.getTimesPurchased() + "|" + product.getPurchasedOnSale() + "\n";


                // Write this line to the report file
                writer.write(line);

                // Calculate the total sales of this product and add it to the total sales
               // totalSales = totalSales.add(product.getPrice().multiply(BigDecimal.valueOf(product.getTimesPurchased() + product.getPurchasedOnSale())));
            }

            // After all products have been written, write the total sales to the report file
            writer.write("\n**TOTAL SALES** $" + totalSales.setScale(2, RoundingMode.HALF_UP));
        } catch (IOException e) {
            // If there was a problem writing the file, print an error message
            System.out.println("Error writing sales report: " + e.getMessage());
        }
    }



    }







