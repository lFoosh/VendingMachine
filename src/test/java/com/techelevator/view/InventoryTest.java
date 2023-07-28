package com.techelevator.view;

import com.techelevator.controller.Inventory;
import com.techelevator.model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory("alternate.csv");  // use main or alternate .csv
    }

    @Test
    public void inventory_loads_correct_number_of_items() {
        int productCount = inventory.getProducts().size();
        System.out.println("Number of products loaded: " + productCount);

        System.out.println("Products loaded:");
        for (Product product : inventory.getProducts().values()) {
            System.out.println(product.getName());
        }

        assertEquals(16, productCount);

        /*TODO Check a range of values IE
        A1,U-Chews,1.65,Gum
        A2,Ginger Ayle,1.85,Drink
        A3,Snykkers,4.25,Candy*/
    }
}
