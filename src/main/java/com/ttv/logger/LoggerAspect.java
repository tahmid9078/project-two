package com.ttv.logger;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private Logger log;
	
//	@Before("everything()")
//	public void logBefore(JoinPoint jp) {
//		log = Logger.getLogger(jp.getTarget().getClass());
//		log.trace("Method with signature: "+jp.getSignature());
//		log.trace("With Arguments: "+Arrays.toString(jp.getArgs()));
//	}
//	@After("everything()")
//    public void log(JoinPoint jp) {
//		log = Logger.getLogger(jp.getTarget().getClass());
//		log.info(jp.getSignature().getName() + " called...");
//    }
	@Around("everything()")
	public Object logAfter(ProceedingJoinPoint pjp) {
		Object obj = null;
		
		log = Logger.getLogger(pjp.getTarget().getClass());
		log.trace("Method with signature: "+pjp.getSignature());
		log.trace("With Arguments: "+Arrays.toString(pjp.getArgs()));
		try {
			obj = pjp.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage());
			for(StackTraceElement s : e.getStackTrace()) {
				log.warn(s);
			}
		}
		log.info(pjp.getSignature()+" returned: "+obj);
		return obj;
	}
	
	@Pointcut("execution(* com.ttv.*.*.*(..))")
	private void everything() { }
}
