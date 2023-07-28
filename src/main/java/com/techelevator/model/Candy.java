package com.techelevator.model;

import java.math.BigDecimal;

public class Candy extends Product {

        public Candy(String slotLocation, String name, BigDecimal price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Yummy Yummy, So Sweet!";
        }
    }

