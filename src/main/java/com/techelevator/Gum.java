package com.techelevator;

public class Gum extends Product{

        public Gum(String slotLocation, String name, double price) {
            super(slotLocation, name, price);
        }

        @Override
        public String getMessage() {
            return "Chew Chew, Yum!";
        }
    }



    /*public Gum(String location, String name, double price) {
        super(location, name, price, 5, "Chew Chew, Yum!");
    }
}
*/