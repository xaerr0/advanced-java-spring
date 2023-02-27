package platform.codingnomads.co.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintableAspect {
    @Pointcut("@annotation(Printable)")
    public void executePrinting() {
    }

    @After("executePrinting()")
    public void printAfter() {
        System.out.println("@Printable after method");
    }

}