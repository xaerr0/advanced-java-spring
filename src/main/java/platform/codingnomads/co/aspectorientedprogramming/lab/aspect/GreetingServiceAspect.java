package platform.codingnomads.co.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceAspect.class);

    @Before("execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.greeting())")
    public void printBefore(JoinPoint jp) {
        System.out.println("Before : " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.greeting())")
    public void printAfterReturning(JoinPoint jp) {
        System.out.println("After Returning : " + jp.getSignature().getName());
    }

    @Pointcut(value = "execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.spanishGreeting())")
    public void SpanishGreetingMethod() {
    }

    @Before("execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.spanishGreeting())")
    public void printBeforeSpanishGreeting(JoinPoint jp) {
        System.out.println("Before Returning : " + jp.getSignature().getName());
    }
}