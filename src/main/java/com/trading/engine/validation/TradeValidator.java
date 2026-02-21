package com.trading.engine.validation;

import com.trading.engine.model.Trade;

public class TradeValidator {

    public static void validate(Trade trade) {

        if (trade.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be positive");
        }

        if (!trade.getSide().equalsIgnoreCase("BUY") &&
                !trade.getSide().equalsIgnoreCase("SELL")) {
            throw new RuntimeException("Invalid side");
        }
    }
}