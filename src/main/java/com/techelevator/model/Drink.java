package com.techelevator.model;

import java.math.BigDecimal;

public class Drink extends Product {

        public Drink(String slotLocation, String name, BigDecimal price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Glug Glug, Yum!";
        }
    }
