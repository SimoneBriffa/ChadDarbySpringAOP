package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
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
	
	
	@AfterThrowing(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing="exception")
	public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception) {
		
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=========> Exectuing @AfterThrowing on " + method);
		
		System.out.println("\n=========> Exception is: " + exception);
		
	} 
	
	@AfterReturning(pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
					returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
		
		//N.B. il nome dato a result deve corrispondere con i parametri dell'annotazione
		
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n=========> Exectuing @AfterReturning on " + method);
		
		System.out.println("\n=========> Result is " + result);
		
		
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
