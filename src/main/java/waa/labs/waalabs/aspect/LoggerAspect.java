package waa.labs.waalabs.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import waa.labs.waalabs.domain.Logger;
import waa.labs.waalabs.repo.LoggerRepo;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    LoggerRepo loggerRepo;

    @Pointcut("execution(* waa.labs.waalabs.controller.*.*(..))")
    public void logOperationDetails() {

    }

    @After("logOperationDetails()")
    public void logOperationDetailsAfter(JoinPoint joinPoint) {
        System.out.println("Logger Aspect triggered for method: " + joinPoint.getSignature().getName());
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String principle = "Test User";
        String operation = joinPoint.getSignature().getName();
        Logger l =  new Logger(date, time, principle, operation);
        loggerRepo.save(l);
    }

}
