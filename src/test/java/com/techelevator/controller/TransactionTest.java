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





}