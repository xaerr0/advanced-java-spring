package platform.codingnomads.co.aspectorientedprogramming.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut(value = "execution(* platform.codingnomads.co.aspectorientedprogramming.aop.service.StudentService.*(..))")
    private void logAllStudentServiceMethods() { }

    @Before("logAllStudentServiceMethods()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("Before Advice : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* platform.codingnomads.co.aspectorientedprogramming.aop.service.StudentService.fetchAllStudents())")
    public void logFetchAllStudentMethod(JoinPoint joinPoint) {
        // write any custom logic according to business requirement
        LOGGER.info("Before the execution of : " + joinPoint.getSignature());
    }

    @After("logAllStudentServiceMethods()")
    public void logAfterAdvice(JoinPoint joinPoint) {
        LOGGER.info("After Advice: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "logAllStudentServiceMethods()", returning = "students")
    public void afterReturningAdvice(JoinPoint jp, Object students) {
        LOGGER.info("After Returning Advice: " + " Method Name: = " + jp.getSignature().getName());
        LOGGER.info("Result: = " + students);
    }
}
