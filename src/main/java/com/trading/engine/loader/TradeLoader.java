package com.trading.engine.loader;

import com.trading.engine.model.Trade;
import com.trading.engine.service.TradeEngine;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Stream;
import com.trading.engine.model.TradeSide;

public class TradeLoader {

    public static void load(String path, TradeEngine engine) throws Exception {

        try (Stream<String> lines = Files.lines(Paths.get(path))) {

            lines.skip(1)
                    .map(line -> line.split(","))
                    .map(arr -> {
                        try {
                            return new Trade(
                                    Long.parseLong(arr[0].trim()),
                                    arr[1].trim(),
                                    arr[2].trim(),
                                    Integer.parseInt(arr[3].trim()),
                                    Double.parseDouble(arr[4].trim()),
                                    TradeSide.fromString(arr[5].trim()),
                                    LocalDateTime.parse(arr[6].trim())
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