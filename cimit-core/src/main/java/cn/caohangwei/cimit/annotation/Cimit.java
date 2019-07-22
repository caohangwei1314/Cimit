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
public @interface Cimit {

    /**
     * @return Limiter name
     */
    String value();

    /**
     * @return Maximum bucket capacity
     */
    int capacity() default 1000;

    /**
     * @return Limiter rate.
     */
    int rate() default 100;

    /**
     * @return Several unit time.
     */
    int period() default 1;

    /**
     * @return Unit of time.
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * @return Whether to wait.
     */
    boolean waiting() default false;

    /**
     * @return Enable distributed or not.
     */
    boolean distributed() default false;

    /**
     * @return Downgrade method name.
     */
    String downgrade() default "";
}
