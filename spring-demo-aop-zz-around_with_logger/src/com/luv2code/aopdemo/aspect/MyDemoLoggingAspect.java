package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
	
		String method = proceedingJoinPoint.getSignature().toShortString();
		logger.info("\n=========> Exectuing @Around on " + method);
		
		long begin = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		logger.info("\nDuration: " + duration/1000.00 + " seconds");
		
		return result;
		
		//la durata sarà uguale alla durata del delay scritto sulla funziona a cui si applica, a meno
		//di qualche centesimo, proprio perchè si applica sia all'inizio che alla fine
	}
	
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=========> Exectuing @After on " + method);	
		
	}
	
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="exception")
	public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception) {
		
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=========> Exectuing @AfterThrowing on " + method);
		
		logger.info("\n=========> Exception is: " + exception);
		
	} 
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
					returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		//N.B. il nome dato a result deve corrispondere con i parametri dell'annotazione
		
		String method = joinPoint.getSignature().toShortString();
		logger.info("\n=========> Exectuing @AfterReturning on " + method);
		
		logger.info("\n=========> Result is " + result);
		
		
		//modifica dopo aver ricevuto i risultati (opzionale)
		
		convertAccountNamesToUpperCase(result);
		
		
	}
	
	

	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		for(Account tempAccount: result) {
			tempAccount.setName(tempAccount.getName().toUpperCase());
		}
		
	}



	@Before("com.luv2code.aopdemo.aspect.LuvAopApplication.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint joinPoint) {		
		System.out.println("\n===========> Executing @Before advice on");		
		
		//display the method signature
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		
		System.out.println("Method signature: " + methodSignature.toString());
		
		//display method arguments
		
		
		//get args
		Object[] args = joinPoint.getArgs();
		
		
		//loop through arguments
		
		for(Object tempArg: args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				Account account = (Account) tempArg;
				
				System.out.println("account name: " + account.getName());
				System.out.println("account level: " + account.getLevel());
			}
			
	}
	
	
}
	
	
}
