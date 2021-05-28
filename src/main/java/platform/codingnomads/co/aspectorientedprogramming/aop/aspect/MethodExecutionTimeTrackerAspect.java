package platform.codingnomads.co.aspectorientedprogramming.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Aspect
@Component
public class MethodExecutionTimeTrackerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodExecutionTimeTrackerAspect.class);

    @Around("@annotation(TrackMethodExecutionTime)")
    public void around(@Nonnull ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;
        LOGGER.info("Execution time of method {} is {} ms", joinPoint.getSignature().getName(), elapsedTime);

    }
}
