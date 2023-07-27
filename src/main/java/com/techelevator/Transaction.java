package com.techelevator;

public class Transaction {
private Product product;
private double moneyInserted;
private double currentMoney;

private double change;

    public Transaction(Product product, double moneyInserted) {
        this.product = product;
        this.moneyInserted = moneyInserted;
    }

    public double feedMoney( double moneyInserted){

        currentMoney += moneyInserted;

        //TODO log function

        return currentMoney;
    }

    public void purchaseItem(){

        //TODO current money - price of purchased item
        //TODO update quantity of purchased item
        //TODO Log the transaction


    }
    public void returnChange(){
        int quarters = 0;
        int dimes = 0;
        int nickles = 0;

        while (currentMoney >)
            //TODO Figure out how to return change in Quarters > dimes > nickles

    }


    public double getCurrentMoney() {
        return currentMoney;
    }


}


