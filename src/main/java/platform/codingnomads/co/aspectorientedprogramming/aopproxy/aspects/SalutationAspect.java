package platform.codingnomads.co.aspectorientedprogramming.aopproxy.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SalutationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalutationAspect.class);

    @AfterReturning(pointcut = "execution(* platform.codingnomads.co.aspectorientedprogramming.aopproxy.services." +
            "PersonService+.getPersonFullName(..))", returning = "fullName")
    public void logFullName(JoinPoint joinPoint, String fullName) {
        LOGGER.info("Full Name in ASPECT: " + fullName);
    }
}
