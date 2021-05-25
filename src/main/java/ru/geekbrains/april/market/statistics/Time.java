package ru.geekbrains.april.market.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    private Long orderServiceTime;
    private Long userServiceTime;
    private Long productServiceTime;

  @PostConstruct
  private void Init(){
      orderServiceTime=0L;
      userServiceTime=0L;
      productServiceTime=0L;
  }

    public void addTimeOrderService(Long t){
        orderServiceTime+=t;
    }
    public void addTimeProductService(Long t){
        productServiceTime+=t;
    }
    public void addTimeUserService(Long t){
        userServiceTime+=t;
    }

}
