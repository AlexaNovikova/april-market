package ru.geekbrains.april.market.statistics;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class StatisticsService {
    private final Time serviceTime;

    @Around("execution(public * ru.geekbrains.april.market.services.OrderService.*(..))")
    public Object methodProfilingOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        serviceTime.addTimeOrderService(duration);
        return out;
    }

    @Around("execution(public * ru.geekbrains.april.market.services.ProductService.*(..))")
    public Object methodProfilingProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        serviceTime.addTimeProductService(duration);
        return out;
    }

    @Around("execution(public * ru.geekbrains.april.market.services.UserService.*(..))")
    public Object methodProfilingUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        serviceTime.addTimeUserService(duration);
        return out;
    }

    public Time getServiceTime(){
        return serviceTime;
    }
}
