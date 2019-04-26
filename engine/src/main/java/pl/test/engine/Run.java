package pl.test.engine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author krzysztof.niedzielski
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Run {

    int invocationCount() default 1;
    Class expectedException() default NoExceptionsExpected.class;
}
