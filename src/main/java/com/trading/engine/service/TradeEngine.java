package com.trading.engine.service;

import com.trading.engine.model.Trade;
import com.trading.engine.repository.TradeRepository;
import com.trading.engine.validation.TradeValidator;

import java.util.concurrent.*;

public class TradeEngine {

    private final BlockingQueue<Trade> queue = new LinkedBlockingQueue<>();
    private final ExecutorService executor;
    private final PortfolioManager portfolioManager;
    private final TradeRepository repository;

    public TradeEngine(int threads,
                       PortfolioManager portfolioManager,
                       TradeRepository repository) {

        this.executor = Executors.newFixedThreadPool(threads);
        this.portfolioManager = portfolioManager;
        this.repository = repository;

        startWorkers(threads);
    }

    private void startWorkers(int threads) {

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {

                while (true) {
                    Trade trade = queue.take();
                    try {
                        TradeValidator.validate(trade);
                        portfolioManager.processTrade(trade);
                        repository.save(trade);

                    } catch (Exception e) {
                        System.err.println("Trade failed: " + trade.getTradeId() +
                                " Reason: " + e.getMessage());
                    }
                }
            });
        }
    }

    public void submitTrade(Trade trade) {
        queue.offer(trade);
    }
}