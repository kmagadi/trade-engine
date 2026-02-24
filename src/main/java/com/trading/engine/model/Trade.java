package com.trading.engine.model;

import java.time.LocalDateTime;

public class Trade {

    private final long tradeId;
    private final String accountId;
    private final String symbol;
    private final int quantity;
    private final double price;
    private final TradeSide side;
    private final LocalDateTime timestamp;

    public Trade(long tradeId,
                 String accountId,
                 String symbol,
                 int quantity,
                 double price,
                 TradeSide side,
                 LocalDateTime timestamp) {

        this.tradeId = tradeId;
        this.accountId = accountId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.side = side;
        this.timestamp = timestamp;
    }

    public long getTradeId() { return tradeId; }
    public String getAccountId() { return accountId; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public TradeSide getSide() { return side; }
    public LocalDateTime getTimestamp() { return timestamp; }
}