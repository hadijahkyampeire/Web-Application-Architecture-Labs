package waa.labs.waalabs.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import waa.labs.waalabs.domain.ExceptionLogger;
import waa.labs.waalabs.repo.ExceptionRepo;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Component
public class ExceptionAspect {
    @Autowired
    ExceptionRepo exceptionRepo;

    @Pointcut("execution(* waa.labs.waalabs.controller.*.*(..))")
    public void logExceptionDetails() {}

    @AfterThrowing(pointcut = "logExceptionDetails()", throwing = "ex")
    public void logExceptionDetailsAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("Exception Aspect triggered for method: " + joinPoint.getSignature().getName());
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String principle = "Haddy";
        String operation = joinPoint.getSignature().getName();
        String exceptionType = ex.getMessage();
        ExceptionLogger e = new ExceptionLogger(date, time, principle, operation, exceptionType);
        exceptionRepo.save(e);
    }
}
