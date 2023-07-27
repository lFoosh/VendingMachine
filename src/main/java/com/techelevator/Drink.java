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


    /*public Drink(String location, String name, double price) {
        super(location, name, price, 5, "Glug Glug, Yum!");
    }
}
*/