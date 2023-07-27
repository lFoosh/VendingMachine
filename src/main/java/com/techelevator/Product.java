package com.techelevator;

public abstract class Product {
    private String location;
    private String name;
    private double price;
    private int quantity;
    private String dispenseMsg;

    public Product(String location, String name, double price, int quantity, String dispenseMsg) {
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
}
