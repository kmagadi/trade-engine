package com.trading.engine.service;

import com.trading.engine.model.Position;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ReportService {

    private static final String REPORT_DIR = "reports";

    public static void exportReports(PortfolioManager pm) {
        try {
            createDirectoryIfNotExists();

            exportDetailedReport(pm);
            exportAccountSummary(pm);

        } catch (Exception e) {
            System.err.println("Error generating reports: " + e.getMessage());
        }
    }

    private static void exportDetailedReport(PortfolioManager pm) throws IOException {

        String fileName = "portfolio_detailed_" + timestamp() + ".csv";
        Path filePath = Paths.get(REPORT_DIR, fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            writer.write("account_id,symbol,quantity,value");
            writer.newLine();

            pm.getPortfolios().forEach((account, positions) -> {
                positions.forEach((symbol, position) -> {
                    try {
                        double value = position.getQuantity() * position.getAvgPrice();

                        writer.write(account + "," +
                                symbol + "," +
                                position.getQuantity() + "," +
                                value);

                        writer.newLine();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }

        System.out.println("Detailed report generated: " + filePath);
    }

    private static void exportAccountSummary(PortfolioManager pm) throws IOException {

        String fileName = "portfolio_summary_" + timestamp() + ".csv";
        Path filePath = Paths.get(REPORT_DIR, fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {

            writer.write("account_id,total_quantity");
            writer.newLine();

            pm.getPortfolios().forEach((account, positions) -> {

                int totalQty = positions.values()
                        .stream()
                        .mapToInt(Position::getQuantity)
                        .sum();

                try {
                    writer.write(account + "," + totalQty);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        System.out.println("Summary report generated: " + filePath);
    }

    private static void createDirectoryIfNotExists() throws IOException {
        Path path = Paths.get(REPORT_DIR);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    private static String timestamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
    }
}