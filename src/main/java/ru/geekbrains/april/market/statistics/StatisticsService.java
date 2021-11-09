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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class StatisticsService {
    private final Map<String, Long> statistics = new ConcurrentHashMap<>();


    public String getStatistics(){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Long> pair: statistics.entrySet()){
            sb.append(pair.getKey()).append(": ").append(pair.getValue()).append(";");
        }
        return sb.toString();
    }

    @Around("execution(public * ru.geekbrains.april.market.services.*.*(..))")
    public Object methodProfilingOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        String serviceName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        statistics.merge(serviceName, duration, Long::sum);
        return out;
    }

//    @Around("execution(public * ru.geekbrains.april.market.services.ProductService.*(..))")
//    public Object methodProfilingProductService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long begin = System.currentTimeMillis();
//        Object out = proceedingJoinPoint.proceed();
//        long end = System.currentTimeMillis();
//        long duration = end - begin;
//        serviceTime.addTimeProductService(duration);
//        return out;
//    }
//
//    @Around("execution(public * ru.geekbrains.april.market.services.UserService.*(..))")
//    public Object methodProfilingUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        long begin = System.currentTimeMillis();
//        Object out = proceedingJoinPoint.proceed();
//        long end = System.currentTimeMillis();
//        long duration = end - begin;
//        serviceTime.addTimeUserService(duration);
//        return out;
//    }
//
//    public Time getServiceTime(){
//        return serviceTime;
//    }
}
