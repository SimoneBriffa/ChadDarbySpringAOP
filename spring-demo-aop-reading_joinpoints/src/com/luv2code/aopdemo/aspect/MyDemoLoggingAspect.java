package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
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
