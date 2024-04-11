package com.navi.school.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class SchoolLogAspect {

    @Around("execution(* com.navi.school..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info(joinPoint.getSignature() + " started executing at "+ Instant.now());
      Instant start = Instant.now();
      Object obj = joinPoint.proceed();
      Instant end = Instant.now();
      Duration timeElapsed = Duration.between(start, end);
      log.info(joinPoint.getSignature() + " execution ends at "+ Instant.now());
      log.info("Total time taken to execute " + joinPoint.getSignature() + " is " + timeElapsed);
      return obj;
    }

    @AfterThrowing(value = "execution(* com.navi.school..*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error(joinPoint.getSignature() + " an exception due to " + ex.getMessage());
    }
}
