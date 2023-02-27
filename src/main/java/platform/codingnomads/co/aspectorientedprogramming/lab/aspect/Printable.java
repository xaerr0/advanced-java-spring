package platform.codingnomads.co.aspectorientedprogramming.lab.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.*;

@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface Printable {
}