package com.techelevator;

public class Drink extends Product{

        public Drink(String slotLocation, String name, double price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Glug Glug, Yum!";
        }
    }
