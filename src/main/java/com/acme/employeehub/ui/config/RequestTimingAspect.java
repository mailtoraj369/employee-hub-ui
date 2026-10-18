package com.acme.employeehub.ui.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestTimingAspect {

    private static final Logger log = LoggerFactory.getLogger(RequestTimingAspect.class);

    @Around("execution(* com.acme.employeehub.ui.controller..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long durationMs = System.currentTimeMillis() - start;
            String endpoint = joinPoint.getSignature().toShortString();
            ApiMetrics.record(endpoint, durationMs);
            log.info("{} completed in {} ms", endpoint, durationMs);
            return result;
        } catch (Throwable ex) {
            long durationMs = System.currentTimeMillis() - start;
            String endpoint = joinPoint.getSignature().toShortString();
            ApiMetrics.record(endpoint + " [error]", durationMs);
            log.error("{} failed in {} ms: {}", endpoint, durationMs, ex.getMessage());
            throw ex;
        }
    }
}
