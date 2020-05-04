package com.example.SpringBootJPA.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class AOPConfig {
	
	private static Logger logger = LoggerFactory.getLogger(AOPConfig.class);

	@Around("execution(* com.example.*.controller.*.*(..))")
	public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable{
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();
		StopWatch st = new StopWatch();
		st.start();
		Object payload = joinPoint.proceed();
		st.stop();
		logger.info("IN AOP {} class {} method total execution time is  {} ms",
				className, methodName, st.getTotalTimeMillis());
		return payload;
	}

}
