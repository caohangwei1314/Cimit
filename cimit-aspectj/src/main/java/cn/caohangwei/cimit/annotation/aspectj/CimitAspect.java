package cn.caohangwei.cimit.annotation.aspectj;

import cn.caohangwei.cimit.annotation.Cimit;
import cn.caohangwei.cimit.common.CimitRule;
import cn.caohangwei.cimit.core.LeakyBucketLimiter;
import cn.caohangwei.cimit.core.CimitFactory;
import cn.caohangwei.cimit.exception.OutOfBucketException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Aspect for methods with {@link Cimit} annotation.
 *
 * @author PinuoC
 */
@Aspect
public class CimitAspect {

    private static final Logger logger = LoggerFactory.getLogger(CimitAspect.class);

    @Pointcut("@annotation(cn.caohangwei.cimit.annotation.Cimit)")
    public void limiterAnnotationPointcut() {
    }

    @Around("limiterAnnotationPointcut()")
    public Object invokeLimiterMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clazz = joinPoint.getTarget().getClass();
        Method method = null;
        String downgrade = null;
        try {
            method = clazz.getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            Cimit cimit = method.getAnnotation(Cimit.class);
            downgrade = cimit.downgrade();
            CimitRule rule = new CimitRule(cimit.value(), cimit.capacity(), cimit.rate(), cimit.period(), cimit.timeUnit(),cimit.distributed());
            LeakyBucketLimiter limiter = (LeakyBucketLimiter) CimitFactory.getLeakyBucketLimiter(rule);
            if (cimit.waiting() && !limiter.acquire()) {
                throw new OutOfBucketException("Overflow in the bucket,rejecting the request");
            }
            if (!cimit.waiting() && !limiter.tryAcquire()) {
                throw new OutOfBucketException("Overflow in the bucket,rejecting the request");
            }
            return joinPoint.proceed();
        } catch (NoSuchMethodException e) {
            logger.debug(clazz.getName() + " Exception: " + e.getMessage());
            throw e;
        }catch (OutOfBucketException e){
            if(downgrade != null && !"".equals(downgrade)){
                Method[] methods = clazz.getDeclaredMethods();
                for(Method m : methods){
                    if(m.getName().equals(downgrade)){
                        return m.invoke(joinPoint.getTarget(),joinPoint.getArgs());
                    }
                }
            }
            logger.debug(clazz.getName() + " OutOfBucketException: " + e.getMessage());
            throw e;
        } catch (Throwable e) {
            logger.debug(method.getName() + " Exception: " + e.getMessage());
            throw e;
        }
    }
}
