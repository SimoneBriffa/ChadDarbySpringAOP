package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	
	//il seguente metodo dice di eseguire quanto dichiarato prima di ogni chiamata al metodo addAccount()
	//(a qualsiasi classe appartenga e con qualsiasi tipo di ritorno e/o modificatore di accesso se non diversamente specificato)
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(void add*())") "add"+"qualsiasi cosa"
	@Before("execution(* add*(com.luv2code.aopdemo.Account))")
	//@Before("execution(* add*(com.luv2code.aopdemo.Account, ...))")
	//@Before("execution(* add*(*))")  UN argomento di qualsiasi tipo
	//@Before("execution(* add*(..))") più argomenti di qualsiasi tipo
	//@Before("execution(*com.luv2code.aopdemo.dao.*.*(..))") //QUALSIASI METODO CON QUALSIASI PARAMETRO MA DENTRO LO STESSO PACKAGE!
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n===========> Executing @Before advice on");
		
	}
	
	//CONSIDERARE ANCHE DI SPECIFICARE IL PACCHETTO, COME ALLA RIGA 16
	
	//SE PER ESEMPIO VIENE LANCIATA UN'ECCEZIONE CHE DICE "INVALID ABSOLUTE TYPE NAME" SIGNFIFICA
	//CHE BISOGNA INSERIRE IL NOME COMPLETAMENTE QUALIFICATO, CIOè INCLUSO DI PACCHETTO, COME A RIGA 16
	
}
