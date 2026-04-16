package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    @Test
    public void testValidBidSubmission() {
        App auction = new App(100.0, 10.0);
        assertTrue(auction.submitBid("Alice", 100.0));
        assertTrue(auction.submitBid("Bob", 110.0));
    }

    @Test
    public void testInvalidBidBelowMinPrice() {
        App auction = new App(100.0, 10.0);
        assertFalse(auction.submitBid("Alice", 90.0));
    }

    @Test
    public void testInvalidBidBelowIncrement() {
        App auction = new App(100.0, 10.0);
        auction.submitBid("Alice", 100.0);
        assertFalse(auction.submitBid("Bob", 105.0));
    }

    @Test
    public void testHighestBid() {
        App auction = new App(100.0, 10.0);
        auction.submitBid("Alice", 100.0);
        auction.submitBid("Bob", 150.0);
        assertEquals(150.0, auction.getHighestBid().get().getAmount());
    }
}
