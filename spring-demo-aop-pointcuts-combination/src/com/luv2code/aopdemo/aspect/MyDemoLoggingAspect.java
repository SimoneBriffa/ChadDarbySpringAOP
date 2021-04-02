package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	/*Vogliamo fare una combinazione dei pointcut in modo tale che in Before vengano
	 * eseguiti tutti i metodi nel package ad eccezione dei getter e dei setter
	 */
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	//pointcut per getter setter
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	//combinazione ad esclusione
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
	
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {		
		System.out.println("\n===========> Executing @Before advice on");		
	}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
		System.out.println("\n===========> Performing Api Analytics");
	}
	
	
	
}
