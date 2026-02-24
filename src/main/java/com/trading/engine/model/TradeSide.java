package com.trading.engine.model;

public enum TradeSide {
    BUY,
    SELL;

    public static TradeSide fromString(String value) {
        return TradeSide.valueOf(value.toUpperCase());
    }

    public boolean isBuy() {
        return this == BUY;
    }

    public boolean isSell() {
        return this == SELL;
    }
}