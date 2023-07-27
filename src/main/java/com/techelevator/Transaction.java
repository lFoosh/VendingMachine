package com.techelevator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Transaction {
    private Product product;
    private double currentMoney;
    private String logFile = "Log.txt";

    public Transaction() {
        this.currentMoney = 0.0;
    }

    public double feedMoney(double moneyInserted) {
        currentMoney += moneyInserted;
        log("FEED MONEY: ", moneyInserted, currentMoney);
        return currentMoney;
    }

    public boolean purchaseItem(Product product) {
        if (product != null && currentMoney >= product.getPrice()) {
            log("PURCHASE: " + product.getName() + " " + product.getSlotLocation(), product.getPrice(), currentMoney - product.getPrice());
            currentMoney -= product.getPrice();
            product.decreaseQuantity();
            return true;
        } else {
            // Insufficient funds or null product
            System.out.println("Unable to complete purchase.");
            return false;
        }
    }


    public void returnChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

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
        System.out.println("Change returned: Quarters: " + quarters + ", Dimes: " + dimes + ", Nickels: " + nickels);
        System.out.println("Total change due: $" + String.format("%.2f", quarters * 0.25 + dimes * 0.10 + nickels * 0.05));
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    private void log(String action, double amount, double newBalance) {
        try (FileWriter fw = new FileWriter(logFile, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(LocalDateTime.now() + " " + action + " " + String.format("$%.2f", amount) + " $" + String.format("%.2f", newBalance));
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}



