package cn.caohangwei.cimit.aspectj;

import cn.caohangwei.cimit.annotation.Cimit;
import cn.caohangwei.cimit.common.LimiterRule;
import cn.caohangwei.cimit.core.AbstractLimiter;
import cn.caohangwei.cimit.core.LeakyBucketLimiter;
import cn.caohangwei.cimit.core.LimiterFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class CimitAspect {

    @Pointcut("@annotation(cn.caohangwei.cimit.annotation.Cimit)")
    public void limiterAnnotationPointcut() {
    }

    @Around("limiterAnnotationPointcut()")
    public void invokeLimiterMethod(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class clazz = joinPoint.getTarget().getClass();
        try {
            Method method = clazz.getDeclaredMethod(signature.getName(),signature.getParameterTypes());
            Cimit cimit = method.getAnnotation(Cimit.class);
            LimiterRule rule = new LimiterRule(cimit.value(),cimit.capacity(),cimit.rate(),cimit.period(),cimit.timeUnit());
            LeakyBucketLimiter limiter =(LeakyBucketLimiter) LimiterFactory.getLeakyBucketLimiter(rule);
            if(limiter.acquire()){
//                method.invoke()
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
