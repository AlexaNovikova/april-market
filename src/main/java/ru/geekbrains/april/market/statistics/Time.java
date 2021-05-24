package ru.geekbrains.april.market.statistics;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    private long orderServiceTime;
    private long userServiceTime;
    private long productServiceTime;

    public void addTimeOrderService(long t){
        orderServiceTime+=t;
    }
    public void addTimeProductService(long t){
        productServiceTime+=t;
    }
    public void addTimeUserService(long t){
        userServiceTime+=t;
    }

}
