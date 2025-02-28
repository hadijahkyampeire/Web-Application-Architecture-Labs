package waa.labs.waalabs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import waa.labs.waalabs.aspect.annotation.ExecutionTime;

@Aspect
@Component
public class ExecutionTimeAspect {
    ExecutionTime executionTime;
    @Pointcut("@annotation(waa.labs.waalabs.aspect.annotation.ExecutionTime)")
    public void executionTimePointcut() {

    }

    @Around("executionTimePointcut()")
    public Object calculateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Execution Aspect triggered for method: " + joinPoint.getSignature().getName());
        Long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time of " + joinPoint.getSignature().getName() + " is " + executionTime + "Milliseconds");
        return result;
    }

}
