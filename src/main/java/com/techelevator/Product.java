package com.techelevator;


public abstract class Product {
    private String slotLocation;
    private String name;
    private double price;
    private int quantity;

    public Product(String slotLocation, String name, double price) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.quantity = 5;  // Initially stocked to the maximum amount
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Abstract method to get the product-specific message
    public abstract String getMessage();

    // Method to decrease the quantity of a product
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--; // Decrease quantity of product by 1
        } else {
            System.out.println("The product is out of stock.");
        }
    }
    @Override
    public String toString() {
        return  getName() + ", " + getPrice() + ", " + getClass().getSimpleName(); //Simple will make sure not to call the package and just the class(Product Types)
    }


}

  /*  public Product(String location, String name, double price, int quantity, String dispenseMsg) {
        this.location = location;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dispenseMsg = dispenseMsg;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDispenseMsg() {
        return dispenseMsg;
    }

    public void setDispenseMsg(String dispenseMsg) {
        this.dispenseMsg = dispenseMsg;
    }
}*/
