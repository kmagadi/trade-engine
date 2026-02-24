package com.trading.engine;

import com.trading.engine.config.DatabaseConfig;
import com.trading.engine.loader.TradeLoader;
import com.trading.engine.repository.TradeRepository;
import com.trading.engine.service.PortfolioManager;
import com.trading.engine.service.ReportService;
import com.trading.engine.service.TradeEngine;

import javax.sql.DataSource;

public class TradingApplication {

    public static void main(String[] args) throws Exception {

        // Get connection pool instead of single connection
        DataSource dataSource = DatabaseConfig.getDataSource();

        PortfolioManager portfolioManager = new PortfolioManager();
        TradeRepository repository = new TradeRepository(dataSource);

        TradeEngine engine = new TradeEngine(4, portfolioManager, repository);

        TradeLoader.load("src/main/resources/trades.csv", engine);

        // Wait for processing to complete (simple approach)
        Thread.sleep(3000);

        ReportService.exportReports(portfolioManager);
    }
}