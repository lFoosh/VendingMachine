package com.techelevator;

public class Munchy extends Product{

        public Munchy(String slotLocation, String name, double price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Crunch Crunch, Yum!";
        }
    }

