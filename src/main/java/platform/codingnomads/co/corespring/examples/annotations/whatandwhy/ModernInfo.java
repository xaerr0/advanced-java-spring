package platform.codingnomads.co.corespring.examples.annotations.whatandwhy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModernInfo {
    String info() default "modern api information return";
}
