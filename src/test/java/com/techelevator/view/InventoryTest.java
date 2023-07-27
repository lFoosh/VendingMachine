package com.techelevator.view;

import com.techelevator.Inventory;
import com.techelevator.Product;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InventoryTest {
    private Inventory inventory;

    @Before
    public void setup() {
        inventory = new Inventory("main.csv");  // replace with actual path
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
    }
}
