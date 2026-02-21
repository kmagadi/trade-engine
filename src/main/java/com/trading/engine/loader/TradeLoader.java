package com.trading.engine.loader;

import com.trading.engine.model.Trade;
import com.trading.engine.service.TradeEngine;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;

public class TradeLoader {

    public static void load(String path, TradeEngine engine) throws Exception {

        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            lines.skip(1)
                    .map(line -> line.split(","))
                    .map(arr -> {
                        try {
                            return new Trade(
                                    Long.parseLong(arr[0].trim()),     // tradeId
                                    arr[1].trim(),                     // accountId
                                    arr[2].trim(),                     // symbol
                                    Integer.parseInt(arr[3].trim()),   // quantity
                                    Double.parseDouble(arr[4].trim()), // price
                                    arr[5].trim(),                     // side
                                    LocalDateTime.parse(arr[6].trim()) // timestamp
                            );
                        } catch (Exception e) {
                            System.err.println("Invalid trade row: " + String.join(",", arr));
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .forEach(engine::submitTrade);
        }
    }
}