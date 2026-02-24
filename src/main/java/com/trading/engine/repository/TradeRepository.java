package com.trading.engine.repository;

import com.trading.engine.model.Trade;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TradeRepository {

    private final Connection connection;

    public TradeRepository(DataSource connection) {
        this.connection = connection;
    }

    public void save(Trade trade) throws Exception {

        String sql = """
                INSERT INTO trades(trade_id, account_id, symbol, quantity, price, side, trade_timestamp)
                VALUES(?,?,?,?,?,?,?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, trade.getTradeId());
            ps.setString(2, trade.getAccountId());
            ps.setString(3, trade.getSymbol());
            ps.setInt(4, trade.getQuantity());
            ps.setDouble(5, trade.getPrice());
            ps.setString(6, trade.getSide());
            ps.setTimestamp(7, java.sql.Timestamp.valueOf(trade.getTimestamp()));
            ps.executeUpdate();
        }
    }
}