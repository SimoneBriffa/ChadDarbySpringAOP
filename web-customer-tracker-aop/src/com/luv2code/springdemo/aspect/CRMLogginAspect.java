package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLogginAspect {
	
	//setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	//con questa scrittura stiamo abilitando QUALSIASI METODO su QUALSIASI classe dentro il package
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forDAOPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
	private void forAppFlow() {}
	
	
	//add @Before advice
	@Before("forAppFlow()") //pointcut
	public void before(JoinPoint joinPoint) {
		
		//display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("======> in @Before: calling method: " + method);
		
		//display arguments to the method
		
		
		//get the arguments
		Object[] args = joinPoint.getArgs();
		
		//loop thru and display them
		for(Object tempArg: args)
			logger.info("=======> argument: " + tempArg);
	}
	
	
	//add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()", returning="result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		
		//display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("======> in @AfterReturning: calling method: " + method);
		
		//display data returned
		logger.info("======> result: " + result);
		
}
	
	
}
