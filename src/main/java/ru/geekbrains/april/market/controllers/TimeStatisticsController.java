package ru.geekbrains.april.market.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.april.market.dtos.CartDto;
import ru.geekbrains.april.market.statistics.StatisticsService;
import ru.geekbrains.april.market.statistics.Time;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@Slf4j
public class TimeStatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping()
    public Time showTime() {
        return statisticsService.getServiceTime();
    }
}
