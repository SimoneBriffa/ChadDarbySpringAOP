package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	/*La dichiarazione Pointcut consiste nel dichiarare una volta una determinata azione che poi
	 * potrà essere applicata a diverse funzioni. In questo modo è anche più facile il riuso
	 * del codice e l'aggiornamento dello stesso, anzichè andare a fare copia e incolla ed eventualmente
	 * dover aggiornare a mano tutte le funzioni a cui è applicato un determinato @Before
	 */
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {		
		System.out.println("\n===========> Executing @Before advice on");		
	}
	
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		System.out.println("\n===========> Performing Api Analytics");
	}
	
	
	
}
