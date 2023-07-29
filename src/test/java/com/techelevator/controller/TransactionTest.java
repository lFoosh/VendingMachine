package com.techelevator.controller;

import com.techelevator.model.Drink;
import com.techelevator.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransactionTest {
    private Transaction transaction = new Transaction();
    @Test
    public void testTransactionHappyCases(){
        transaction.feedMoney("10");
        Assert.assertEquals(new BigDecimal("10.0"), transaction.getCurrentMoney());

        Product product = new Drink("A1", "NotPepsi", new BigDecimal("2.25"));
        transaction.purchaseItem(product);
        Assert.assertEquals(new BigDecimal("7.75"), transaction.getCurrentMoney());

        Assert.assertEquals("Change returned: Quarters: 31, Dimes: 0, Nickels: 0" ,( transaction.returnChange()));

    }

    @Test
    public void testFeedingNegativeDollars(){

        transaction.feedMoney("-1");
        Assert.assertEquals(new BigDecimal("0.0"), transaction.getCurrentMoney());


    }

    @Test
    public void testNotEnoughMoney(){

        transaction.feedMoney("1");


        Product product = new Drink("A1", "NotPepsi", new BigDecimal("2.25"));
        transaction.purchaseItem(product);
        Assert.assertEquals(new BigDecimal("1.0"), transaction.getCurrentMoney());

    }

    @Test
    public void testBuyingWithoutFeeding(){

        Product product = new Drink("A1", "NotPepsi", new BigDecimal("2.25"));
        transaction.purchaseItem(product);
        Assert.assertEquals(new BigDecimal("0.0"), transaction.getCurrentMoney());

    }

    @Test
    public void testReturnChange() {
        transaction.feedMoney("2.25");

        Product product = new Drink("A1", "NotPepsi", new BigDecimal("2.25"));
        transaction.purchaseItem(product);


        Assert.assertEquals("Change returned: Quarters: 0, Dimes: 0, Nickels: 0", transaction.returnChange());
    }


}






