package cn.caohangwei.cimit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * The annotation indicates a definition Cimit Rule.
 *
 * @author PinuoC
 * @since 0.2.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limiter {

    /**
     * @return Maximum capacity of bucket.
     */
    int capacity() default 1000;

    /**
     * @return Speed of production or reduction per unit time.
     */
    int rate() default 100;

    /**
     * @return Time unit.
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
