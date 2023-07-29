package com.techelevator.model;


import java.math.BigDecimal;

public abstract class Product {
    private String slotLocation;
    private String name;
    //private double price;
    private BigDecimal price;
    private int quantity;

    private int timesPurchased;
    private int purchasedOnSale;

    public Product(String slotLocation, String name,BigDecimal price) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.quantity = 5;  // Initially stocked to the maximum amount
        this.timesPurchased = 0;
        this.purchasedOnSale = 0;
    }

    // Getters and Setters
    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }


    public int getTimesPurchased() {
        return timesPurchased;
    }

    public void setTimesPurchased(int timesPurchased) {
        this.timesPurchased = timesPurchased;
    }

    public int getPurchasedOnSale() {
        return purchasedOnSale;
    }

    public void setPurchasedOnSale(int purchasedOnSale) {
        this.purchasedOnSale = purchasedOnSale;
    }

    // Abstract method to get the product-specific message
    public abstract String getMessage();

    public void incrementTimesPurchased() {
        this.timesPurchased++;
    }
    public void incrementPurchasedOnSale() {
        this.purchasedOnSale++;
    }

    // Method to decrease the quantity of a product
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--; // Decrease quantity of product by 1
        }
    }
    @Override
    public String toString() {
        return  getName() + ", " + getPrice() + ", " + getClass().getSimpleName(); //Simple will make sure not to call the package and just the class(Product Types)
    }


}

