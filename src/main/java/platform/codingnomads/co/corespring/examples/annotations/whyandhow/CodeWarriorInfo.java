package platform.codingnomads.co.corespring.examples.annotations.whyandhow;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeWarriorInfo {
    String info() default "Spring Expert Code Warrior";
}
