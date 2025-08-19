package com.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Pointcut for all methods in classes annotated with @Service or @Controller
    @Pointcut("within(@org.springframework.stereotype.Service *) || within(@org.springframework.stereotype.Controller *)")
    public void serviceOrControllerMethods() {
    }

    @Before("serviceOrControllerMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String params = Arrays.toString(joinPoint.getArgs());
        log.info(">> {}.{}({})", className, methodName, params);
    }

    @AfterReturning(pointcut = "serviceOrControllerMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        log.info("<< {}.{} returned: {}", className, methodName, result);
    }

    @AfterThrowing(pointcut = "serviceOrControllerMethods()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        log.error("<< {}.{} threw exception: {}", className, methodName, ex.getMessage(), ex);
    }


//    @Around("execution(* com..service..*(..))")
    /**Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> declaringClass = method.getDeclaringClass();

        Logger logger = LoggerFactory.getLogger(declaringClass);

        // Package + service class name
        String serviceName = declaringClass.getName();

        // Method name
        String methodName = method.getName();

        // Parameters
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        StringBuilder params = new StringBuilder();
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                params.append(paramNames[i]).append("=").append(args.length > i ? args[i] : "null");
                if (i < paramNames.length - 1) params.append(", ");
            }
        }

        // Line number (best effort, uses stack trace)
        int startLine = -1, endLine = -1;
        try {
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            for (StackTraceElement element : stack) {
                if (element.getClassName().equals(declaringClass.getName())
                        && element.getMethodName().equals(methodName)) {
                    startLine = element.getLineNumber();
                    break;
                }
            }
            endLine = startLine + method.getCode().length; // Not always accurate
        } catch (Exception e) {
            // ignore
        }

        logger.info(">> {}.{}({}) [StartLine: {}]", serviceName, methodName, params, startLine);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            logger.error("<< {}.{} threw exception: {}", serviceName, methodName, t.toString());
            throw t;
        }

        logger.info("<< {}.{} [EndLine: {}] returned: {}", serviceName, methodName, endLine, result);
        return result;
    }*/
}
