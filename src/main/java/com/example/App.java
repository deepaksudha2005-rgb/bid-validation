package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class App {
    private final List<Bid> bids = new ArrayList<>();
    private final double minStartPrice;
    private final double minIncrement;

    public App(double minStartPrice, double minIncrement) {
        this.minStartPrice = minStartPrice;
        this.minIncrement = minIncrement;
    }

    public boolean submitBid(String bidder, double amount) {
        if (isValidBid(amount)) {
            bids.add(new Bid(bidder, amount));
            return true;
        }
        return false;
    }

    private boolean isValidBid(double amount) {
        if (amount < minStartPrice) {
            return false;
        }
        Optional<Bid> highest = getHighestBid();
        if (highest.isPresent()) {
            return amount >= (highest.get().getAmount() + minIncrement);
        }
        return true;
    }

    public Optional<Bid> getHighestBid() {
        return bids.stream().max(Comparator.comparingDouble(Bid::getAmount));
    }

    public List<Bid> getAllBids() {
        return new ArrayList<>(bids);
    }

    public static class Bid {
        private final String bidder;
        private final double amount;

        public Bid(String bidder, double amount) {
            this.bidder = bidder;
            this.amount = amount;
        }

        public String getBidder() { return bidder; }
        public double getAmount() { return amount; }

        @Override
        public String toString() {
            return bidder + ": " + amount;
        }
    }

    public static void main(String[] args) {
        App auction = new App(100.0, 10.0);
        auction.submitBid("Alice", 120.0);
        auction.submitBid("Bob", 115.0);
        auction.submitBid("Charlie", 150.0);
        auction.getHighestBid().ifPresent(b -> System.out.println("Current Highest Bid: " + b));
    }
}
