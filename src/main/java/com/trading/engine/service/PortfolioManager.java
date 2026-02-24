package com.trading.engine.service;

import com.trading.engine.model.Position;
import com.trading.engine.model.Trade;
import com.trading.engine.model.TradeSide;

import java.util.concurrent.ConcurrentHashMap;

public class PortfolioManager {

    private final ConcurrentHashMap<String,
            ConcurrentHashMap<String, Position>> portfolios = new ConcurrentHashMap<>();

    public void processTrade(Trade trade) {

        int signedQty = trade.getSide() == TradeSide.SELL
                ? -trade.getQuantity()
                : trade.getQuantity();

        portfolios
                .computeIfAbsent(trade.getAccountId(), k -> new ConcurrentHashMap<>())
                .computeIfAbsent(trade.getSymbol(), k -> new Position())
                .update(signedQty, trade.getPrice());
    }

    public ConcurrentHashMap<String,
            ConcurrentHashMap<String, Position>> getPortfolios() {
        return portfolios;
    }
}