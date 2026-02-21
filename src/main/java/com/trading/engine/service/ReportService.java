package com.trading.engine.service;

import com.trading.engine.model.Position;

public class ReportService {

    public static void generate(PortfolioManager pm) {

        pm.getPortfolios().forEach((account, positions) -> {

            int totalQty = positions.values().stream()
                    .mapToInt(Position::getQuantity)
                    .sum();

            System.out.println("Account: " + account +
                    " Total Quantity: " + totalQty);
        });
    }
}