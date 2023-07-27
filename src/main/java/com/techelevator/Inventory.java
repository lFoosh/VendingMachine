package com.techelevator;



import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Inventory {
    private Map<String, Product> products;

    public Inventory(String filename) {
        this.products = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split(",");
                String slotLocation = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String type = parts[3];
                Product product;
                if (type.equals("Candy")) {
                    product = new Candy(slotLocation, name, price);
                } else if (type.equals("Drink")) {
                    product = new Drink(slotLocation, name, price);
                } else if (type.equals("Gum")) {
                    product = new Gum(slotLocation, name, price);
                } else if (type.equals("Munchy")) {
                    product = new Munchy(slotLocation, name, price);
                } else {
                    throw new IllegalArgumentException("Invalid product type: " + type);
                }
                products.put(slotLocation, product);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
    }

    public Map<String, Product> getProducts() {
        return this.products;
    }


    // Add methods here to get products, buy products, etc.
}

/*public class Inventory {

    public void load(){

    }
    public void display(){

    }
    public void update(){

    }

}*/
