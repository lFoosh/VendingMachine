package com.techelevator.model;

import java.math.BigDecimal;

public class Gum extends Product {

        public Gum(String slotLocation, String name, BigDecimal price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Chew Chew, Yum!";
        }
    }



