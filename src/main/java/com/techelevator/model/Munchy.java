package com.techelevator.model;

import java.math.BigDecimal;

public class Munchy extends Product {

        public Munchy(String slotLocation, String name, BigDecimal price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Crunch Crunch, Yum!";
        }
    }

