package com.trading.engine.model;

public class Position {

    private int quantity;
    private double avgPrice;

    public synchronized void update(int tradeQty, double tradePrice) {

        int newQty = this.quantity + tradeQty;

        if (newQty < 0) {
            throw new RuntimeException("Negative position not allowed");
        }

        if (newQty != 0) {
            this.avgPrice = ((this.quantity * this.avgPrice) +
                    (tradeQty * tradePrice)) / newQty;
        }

        this.quantity = newQty;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAvgPrice() {
        return avgPrice;
    }
}