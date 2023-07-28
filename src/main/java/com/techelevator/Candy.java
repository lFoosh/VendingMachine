package com.techelevator;

public class Candy extends Product{

        public Candy(String slotLocation, String name, double price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Yummy Yummy, So Sweet!";
        }
    }

