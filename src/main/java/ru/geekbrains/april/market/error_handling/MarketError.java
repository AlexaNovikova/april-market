package ru.geekbrains.april.market.error_handling;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;


@Data
public class MarketError {
    private int status;
    private List<String> message;
    private Date timestamp;

    public MarketError(int status, Collection<String>messages) {
        this.status = status;
        this.message = new ArrayList<>(messages);
        this.timestamp = new Date();
    }
}
